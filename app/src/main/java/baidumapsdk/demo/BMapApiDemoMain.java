/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package baidumapsdk.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.VersionInfo;

import java.util.ArrayList;
import java.util.List;

import baidumapsdk.demo.map.LocationDemo;
import baidumapsdk.demo.map.LayersDemo;
import baidumapsdk.demo.map.HeatMapDemo;
import baidumapsdk.demo.map.BaseMapDemo;
import baidumapsdk.demo.map.FavoriteDemo;
import baidumapsdk.demo.map.IndoorMapDemo;
import baidumapsdk.demo.map.GeometryDemo;
import baidumapsdk.demo.map.MapControlDemo;
import baidumapsdk.demo.map.MapFragmentDemo;
import baidumapsdk.demo.map.MarkerClusterDemo;
import baidumapsdk.demo.map.MultiMapViewDemo;
import baidumapsdk.demo.map.OfflineDemo;
import baidumapsdk.demo.map.OpenglDemo;
import baidumapsdk.demo.map.OverlayDemo;
import baidumapsdk.demo.map.TextureMapViewDemo;
import baidumapsdk.demo.map.TileOverlayDemo;
import baidumapsdk.demo.map.TrackShowDemo;
import baidumapsdk.demo.map.UISettingDemo;
import baidumapsdk.demo.search.RoutePlanDemo;
import baidumapsdk.demo.search.BusLineSearchDemo;
import baidumapsdk.demo.search.DistrictSearchDemo;
import baidumapsdk.demo.search.GeoCoderDemo;
import baidumapsdk.demo.search.PoiSearchDemo;
import baidumapsdk.demo.search.ShareDemo;
import baidumapsdk.demo.search.IndoorSearchDemo;
import baidumapsdk.demo.cloud.CloudSearchDemo;
import baidumapsdk.demo.radar.RadarDemo;
import baidumapsdk.demo.util.OpenBaiduMap;


public class BMapApiDemoMain extends Activity {
    private static final String LTAG = BMapApiDemoMain.class.getSimpleName();

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            Log.d(LTAG, "action: " + s);
            TextView text = (TextView) findViewById(R.id.text_Info);
            text.setTextColor(Color.RED);
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
                text.setText("key 验证出错! 错误码 :" + intent.getIntExtra
                        (SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, 0)
                        +  " ; 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
                text.setText("key 验证成功! 功能可以正常使用");
                text.setTextColor(Color.YELLOW);
            } else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
                text.setText("网络出错");
            }
        }
    }

    private SDKReceiver mReceiver;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        TextView text = (TextView) findViewById(R.id.text_Info);
        text.setTextColor(Color.YELLOW);
        text.setText("欢迎使用Kit Helper Android SDK v" + VersionInfo.getApiVersion());
        setTitle(getTitle() + " v" + VersionInfo.getApiVersion());
        // 添加ListItem，设置事件响应
        Button button2=(Button) findViewById(R.id.button);//网页查询
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 =new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://app2.sfda.gov.cn/datasearchp/gzcxSearch.do?searchcx=%E8%8D%AF%E5%93%81%E6%9F%A5%E8%AF%A2&optionType=V1&formRender=cx"));
                startActivity(intent1);
            }
        });
        Button buttonCall = (Button)findViewById(R.id.button4);//急救电话
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:120"));
                startActivity(call);
            }
        });
        Button buttonTime = (Button)findViewById(R.id.button74);//医药常识合集
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(BMapApiDemoMain.this,MainActivity.class);
                startActivity(intent2);
            }
        });
        Button map=(Button)findViewById(R.id.t1);//附近药店
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentmap=new Intent(BMapApiDemoMain.this,PoiSearchDemo.class);
                startActivity(intentmap);
            }
        });


        // 注册 SDK 广播监听者
        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);
        initViews();
        initData();

        //begin scan installed applications;
        //// TODO: 16/1/15
        new Thread(new ScanInstalledAppsRunnable()).start();


        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(BMapApiDemoMain.this,tixing.class);
                startActivity(intent);

            }
        }, 1000 * 8);//wait_time_to_start_tixing
    }
    private void initViews() {
        startSystemClockButton = (Button) findViewById(R.id.button_1);
        startSystemClockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 16/1/15
                //startSystemClock();
//                Intent intent = new Intent(BMapApiDemoMain.this,med_information.class);//添加药品信息
                Intent intent = new Intent(BMapApiDemoMain.this,tixing.class);
                startActivity(intent);
            }
        });
    } private void initData() {
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
            message.what = BMapApiDemoMain.HANDLE_MESSAGE_KEY;
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
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消监听 SDK 广播
        unregisterReceiver(mReceiver);
    }



}