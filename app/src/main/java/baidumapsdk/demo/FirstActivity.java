package baidumapsdk.demo;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import baidumapsdk.demo.search.PoiSearchDemo;

public class FirstActivity extends AppCompatActivity {
    private Button startSystemClockButton = null;


    private List<PackageInfo> allPackageInfos;//系统安装所有软件
    private List<PackageInfo> clockPackageInfos;//系统时钟软件

    private static final int HANDLE_MESSAGE_KEY = 1001;
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null) {
                switch (msg.what) {
                    case HANDLE_MESSAGE_KEY:
                        Toast.makeText(getApplicationContext(), "--app scan over--", Toast.LENGTH_SHORT).show();
                        Log.d("CXC", "--app scan over--");
                        break;

                    default:
                        break;

                }

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);//onCreate method is a symbol of startting a android activity
        setContentView(R.layout.first_layout);//this method shows which layout should the activity use
       // Button button1=findViewById(R.id.button_1);//if you put a button in the xml and you want to use this button you need to use this method to find out the name of the button so you can use
        //button1.setOnClickListener(new View.OnClickListener() {
            //@Override
          //  public void onClick(View view) {

             //  Intent alarms = new Intent(AlarmClock.ACTION_SET_ALARM);
             //   startActivity(alarms);
           // }
      //  });
        Button button2=(Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentWeb =new Intent(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intentWeb);
            }
        });
        Button buttonCall =(Button)  findViewById(R.id.button4);
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:120"));
                startActivity(call);
            }
        });
      /*  Button buttonTime =(Button)  findViewById(R.id.button888);
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(FirstActivity.this,ListActivity.class);
                startActivity(intent2);
            }
        });*/
        Button map=(Button)findViewById(R.id.t1);
        map.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent intentmap=new Intent(FirstActivity.this,PoiSearchDemo.class);
                                       startActivity(intentmap);
                                   }
                               });
        initViews();
        initData();

        //begin scan installed applications;
        //// TODO: 16/1/15
        new Thread(new ScanInstalledAppsRunnable()).start();
    }

    private void initViews() {
        startSystemClockButton = (Button) findViewById(R.id.button_1);
        startSystemClockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 16/1/15
                startSystemClock();
            }
        });
    }

    private void initData() {
        clockPackageInfos = new ArrayList<PackageInfo>();
    }

    private boolean isClockApplication(String packageName) {
        boolean isClockApp = false;
        if (packageName != null && packageName.contains("clock") && !packageName.contains("widget")) {
            isClockApp = true;
        }
        return isClockApp;
    }

    class ScanInstalledAppsRunnable implements Runnable {
        @Override
        public void run() {
            allPackageInfos = getPackageManager()
                    .getInstalledPackages(0);

            if (allPackageInfos == null || allPackageInfos.size() == 0) {
                //// TODO: 16/1/15 todo nothing
                return;
            }

            if (clockPackageInfos == null) {
                clockPackageInfos = new ArrayList<PackageInfo>();
            }

            PackageInfo tempPackageInfo = null;
            for (int i = 0; i < allPackageInfos.size(); i++) {
                tempPackageInfo = allPackageInfos.get(i);
                if (tempPackageInfo != null) {

                    if (isSystemApplication(tempPackageInfo.applicationInfo) &&
                            isClockApplication(tempPackageInfo.packageName)) {
                        clockPackageInfos.add(tempPackageInfo);
                    }

                }
            }


            Message message = myHandler.obtainMessage();
            message.what = FirstActivity.HANDLE_MESSAGE_KEY;
            myHandler.sendMessage(message);
        }
    }

    private boolean isSystemApplication(ApplicationInfo applicationInfo) {
        boolean isSystemApp = false;
        if (applicationInfo != null) {
            if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
                    || (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                isSystemApp = true;

            }
        }
        return isSystemApp;
    }
    private void startSystemClock() {
        if (clockPackageInfos == null || clockPackageInfos.size() == 0) {
            Toast.makeText(getApplicationContext(), "--启动系统闹钟失败1--", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("CXC", "---clock package size :" + clockPackageInfos.size());
        Log.d("CXC", "---clock package [0] :" + clockPackageInfos.get(0).packageName);
        try {
            Intent startSysClockIntent = getPackageManager().getLaunchIntentForPackage(clockPackageInfos.get(0).packageName);
            startActivity(startSysClockIntent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "--启动系统闹钟失败2--", Toast.LENGTH_SHORT).show();
        }}




}
