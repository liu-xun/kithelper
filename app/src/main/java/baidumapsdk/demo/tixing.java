package baidumapsdk.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ASUS on 2018/10/20.
 */

public class tixing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tixing);

        final LinearLayout tixing1 = findViewById(R.id.tixing_med1);
        final LinearLayout tixing2 = findViewById(R.id.tixing_med2);
        final LinearLayout tixing3 = findViewById(R.id.tixing_med3);
        final TextView fuyong1 = findViewById(R.id.qingfuyong_med1);
        final TextView fuyong2 = findViewById(R.id.qingfuyong_med2);
        final TextView fuyong3 = findViewById(R.id.qingfuyong_med3);

        tixing1.setBackgroundColor(Color.parseColor("#ffffff"));
        tixing2.setBackgroundColor(Color.parseColor("#ffffff"));
        tixing3.setBackgroundColor(Color.parseColor("#ffffff"));
        fuyong1.setVisibility(View.INVISIBLE);
        fuyong2.setVisibility(View.INVISIBLE);
        fuyong3.setVisibility(View.INVISIBLE);

        tixing1.setBackgroundColor(Color.parseColor("#F709F7"));
        fuyong1.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                tixing1.setBackgroundColor(Color.parseColor("#ffffff"));
                fuyong1.setText("已服用");
                fuyong1.setTextColor(Color.parseColor("#000000"));
                tixing3.setBackgroundColor(Color.parseColor("#F709F7"));
                fuyong3.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        tixing3.setBackgroundColor(Color.parseColor("#ffffff"));
                        fuyong3.setText("已服用");
                        fuyong3.setTextColor(Color.parseColor("#000000"));
                        tixing2.setBackgroundColor(Color.parseColor("#F709F7"));
                        fuyong2.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                tixing2.setBackgroundColor(Color.parseColor("#ffffff"));
                                fuyong2.setText("已服用");
                                fuyong2.setTextColor(Color.parseColor("#000000"));

                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        finish();

                                    }
                                }, 500);//wait_time_to_finish
                            }
                        }, 1000 * 3);//me3_take_time
                    }
                }, 1000 * 3);//me2_take_time
            }
        }, 1000 * 8);//me1_take_time


    }
}
