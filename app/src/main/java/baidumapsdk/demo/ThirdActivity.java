package baidumapsdk.demo;

import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
    Button button_2= (Button) findViewById(R.id.button3);

    button_2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent serch= new Intent(Intent.ACTION_VIEW);
            serch.setData(Uri.parse("http://www.baidu.com"));//uri means uniform resource identifier 统一资源表示符 由scheme（访问资源的命名机制） authority(存放资源的主机名) path（资源的名称由路径表示）
            startActivity(serch);
        }
    });
    }
}
