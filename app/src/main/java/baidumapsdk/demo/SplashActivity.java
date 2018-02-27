package baidumapsdk.demo;

/**
 * Created by 陈薄文 on 2018/2/5.
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            public void run() {
				Intent it = new Intent();
				it.setClass(SplashActivity.this, BMapApiDemoMain.class);
				startActivity(it);
                finish();
            }
        }, 1000 * 2);
    }

}