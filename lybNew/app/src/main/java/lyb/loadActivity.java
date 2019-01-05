package lyb;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lyb.com.example.LoginActivity1;

import static lyb.R.*;


public class loadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_load);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(loadActivity.this,LoginActivity1.class);
                loadActivity.this.startActivity(mainIntent);
                loadActivity.this.finish();
            }
        },1300);

    }
}
