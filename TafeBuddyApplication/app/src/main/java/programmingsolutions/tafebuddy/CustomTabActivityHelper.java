package programmingsolutions.tafebuddy;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;


import java.util.List;

import CustomTabs.CustomTabsHelper;

/**
 * This is a helper class to manage the connection to the Custom Tabs Service.
 */

public class CustomTabActivityHelper implements ServiceConnectionCallback{


    private CustomTabsSession mCustomTabsSession;
    private CustomTabsClient mClient;
    private CustomTabsServiceConnection mConnection;
    private ConnectionCallback mConnectionCallback;


    //opens the URL on a Custom Tab if possible ortherwise it will fall back to opening it on a WebView.


    public static void openCustomTab(Activity activity,
                                     CustomTabsIntent customTabsIntent,
                                     Uri uri,
                                     CustomTabFallBack fallBack){

        String packageName = CustomTabsHelper.getPackageNameToUse(activity);

        //if we cant find a package name it means that theres no browser that supports
        //Chrome Custom Tabs installed. So we fallback to using a webview.

        if(packageName == null){
            if(fallBack != null){
                fallBack.openURI(activity,uri);
            }
        }
        //otherwise it will open in a custom tab
        else {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(activity,uri);
        }


    }


    //this will unbind the activity from Custom Tabs Service freeing up resources
    public void unbindCustomTabsService(Activity activity) {
        if (mConnection == null) return;
        activity.unbindService(mConnection);
        mClient = null;
        mCustomTabsSession = null;
        mConnection = null;
    }

    //this will create the session
    public CustomTabsSession getSession() {
        if (mClient == null) {
            mCustomTabsSession = null;
        } else if (mCustomTabsSession == null) {
            mCustomTabsSession = mClient.newSession(null);
        }
        return mCustomTabsSession;
    }

    /*  Register a Callback to be called when connected or disconnected from the Custom Tabs Service*/
    public void setConnectionCallback(ConnectionCallback connectionCallback) {
        this.mConnectionCallback = connectionCallback;
    }

    /* Binds the Activity to the Custom Tabs Service.  activity the activity to be binded to the service. */
    public void bindCustomTabsService(Activity activity) {
        if (mClient != null) return;

        String packageName = CustomTabsHelper.getPackageNameToUse(activity);
        if (packageName == null) return;

        mConnection = new ServiceConnection(this);
        CustomTabsClient.bindCustomTabsService(activity, packageName, mConnection);
    }

    //this will warm up the browser and get faster loading times.
    public boolean mayLaunchUrl(Uri uri, Bundle extras, List<Bundle> otherLikelyBundles) {
        if (mClient == null) return false;

        CustomTabsSession session = getSession();
        if (session == null) return false;

        return session.mayLaunchUrl(uri, extras, otherLikelyBundles);
    }

    @Override
    public void onServiceConnected(CustomTabsClient client) {
        mClient = client;
        mClient.warmup(0L);

    }

    @Override
    public void onServiceDisconnected() {

        mClient = null;
        mCustomTabsSession = null;

    }







    /**
     * A Callback for when the service is connected or disconnected. Use those callbacks to
     * handle UI changes when the service is connected or disconnected.
     */
    public interface ConnectionCallback {
        /**
         * Called when the service is connected.
         */


        /**
         * Called when the service is disconnected.
         */

    }


    //this will be used as a fallback to open urls when custom tabs is not availible. we will use
    //a webview to load the data

    public  interface CustomTabFallBack{


        //passing in the activity that wants to open the URL and the URL to be opened by the fallback
        //webview.

        void openURI(Activity activity, Uri uri);

    }

}
