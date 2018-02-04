package codeutsava.app.codeutsava.com.codeutsava.Graph.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import codeutsava.app.codeutsava.com.codeutsava.R;
import codeutsava.app.codeutsava.com.codeutsava.helper.Urls;

public class GraphActivity extends AppCompatActivity {

    private WebView myWebView;
    private TextView textView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.name);
        myWebView = (WebView) findViewById(R.id.webView);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            url = data.getString("url");
        }
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(Urls.Base_Url + url);
        Log.d("abhi", Urls.Base_Url + url);
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
            }
        });
    }
}
