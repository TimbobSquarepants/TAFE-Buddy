package programmingsolutions.tafebuddy;

/**
 * Created by timot on 10/6/2016.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import programmingsolutions.tafebuddy.R;


public class WebviewActivity extends AppCompatActivity {

    //variable to hold URL
    public static final String EXTRA_URL = "extra.url";


    //created the webview with the variable passed from the webviewfallback


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        //retrievin the URL from the extra intent we sent through the webview fallback
        String url = getIntent().getStringExtra(EXTRA_URL);

        WebView webView = (WebView)findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //setting the title of the page url for now
        setTitle(url);
        getSupportActionBar();
        //setting the actionbar to show home button arrow

        webView.loadUrl(url);


    }


    //this will respong when the user selects the home button on the action bar
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

}
