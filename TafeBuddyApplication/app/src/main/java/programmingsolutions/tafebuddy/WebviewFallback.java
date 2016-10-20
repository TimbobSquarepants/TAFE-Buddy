package programmingsolutions.tafebuddy;

/**
 * Created by timot on 10/6/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;


import programmingsolutions.tafebuddy.CustomTabActivityHelper.CustomTabFallBack;

//this will open a webview when custom tabs is not availible


public class WebviewFallback implements CustomTabFallBack {




    @Override

    public void openURI(Activity activity, Uri uri) {
        //sending an intent to the webViewActivity class letting it know we want to use it
        Intent intent = new Intent(activity, WebviewActivity.class);
        //sending the webview activity our url so it can display it
        intent.putExtra(WebviewActivity.EXTRA_URL,uri.toString());

        activity.startActivity(intent);
    }
}
