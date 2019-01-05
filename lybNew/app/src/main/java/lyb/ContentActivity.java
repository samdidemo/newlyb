package lyb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static lyb.R.*;


public class ContentActivity extends AppCompatActivity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_content);

        Toolbar toolbar = (Toolbar)findViewById(id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(drawable.ic_back);
        }
        /**
         *把webView加进来
         * lyb
         */

        webView = (WebView)findViewById(id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        /**
         * 得到MainActivity传过来的url和title
         */

        String uri = getIntent().getStringExtra("uri");
        String title = getIntent().getStringExtra("title");
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
        /**
         * 根据uri加载页面
         */
        webView.loadUrl(uri);

    }

    /**
     * 点击返回键做了处理
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }
}
