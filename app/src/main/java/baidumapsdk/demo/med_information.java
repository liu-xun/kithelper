package baidumapsdk.demo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by ASUS on 2018/10/14.
 */

public class med_information extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_information);

        Button buttonCall = (Button)findViewById(R.id.button_addInformation);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(med_information.this,take_rule.class);
                startActivity(intent);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        TextView text = (TextView) findViewById(R.id.textview_tianjiachenggong);
                        text.setVisibility(View.VISIBLE);
                    }
                }, 1000 * 1);
            }
        });

        Button buttonfanhui = (Button)findViewById(R.id.button_activity_med_information_yes);
        buttonfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TimePickerView pvTime = new TimePickerBuilder(med_information.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(med_information.this, getTime(date), Toast.LENGTH_SHORT).show();
            }

            private String getTime(Date date) {//可根据需要自行截取数据显示
                Log.d("getTime()", "choice date millis: " + date.getTime());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.format(date);
            }

        }).build();
    }

}
