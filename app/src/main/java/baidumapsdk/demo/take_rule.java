package baidumapsdk.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/10/19.
 */

public class take_rule extends AppCompatActivity {
    private Spinner spinner;
    private Spinner spinner3;
    private Spinner spinner4;
    private List<String> list;
    private List<String> list3;
    private List<String> list4;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_rule);

        Button buttonfanhui = (Button)findViewById(R.id.button_activity_take_rule_yes);
        buttonfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinner = findViewById(R.id.spinner);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        final TableRow table_shijian1 = findViewById(R.id.table_shijian1);
        final TableRow table_shijian2 = findViewById(R.id.table_shijian2);
        final TableRow table_shijian3 = findViewById(R.id.table_shijian3);
        //设置数据源
        list=new ArrayList<String>();
        list.add("一日一次");
        list.add("一日两次");
        list.add("一日三次");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                TextView text = (TextView) findViewById(R.id.textview_tianjiachenggong);
                table_shijian2.setVisibility(View.VISIBLE);
            }
        }, 1000 * 2);
        list3=new ArrayList<String>();
        list3.add("一");
        list3.add("二");
        list3.add("三");
        list3.add("四");
        list4=new ArrayList<String>();
        list4.add("无");

        //新建Arradapater适配器
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        adapter3=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list3);
        adapter4=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list4);

        //设置adapter的下拉列表的样式（下拉菜单的样式）
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        adapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        //spinner加载适配器
        spinner.setAdapter(adapter);
        spinner3.setAdapter(adapter3);
        spinner4.setAdapter(adapter4);

        //spinner设置监听器
        spinner.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                        String name =adapter.getItem(i);

//                        switch (i){
//                            case 0:{
//                                table_shijian1.setVisibility(View.VISIBLE);
//                                table_shijian2.setVisibility(View.INVISIBLE);
//                                table_shijian3.setVisibility(View.INVISIBLE);
//                            }
//                            case 1:{
//                                table_shijian1.setVisibility(View.VISIBLE);
//                                table_shijian2.setVisibility(View.VISIBLE);
//                                table_shijian3.setVisibility(View.INVISIBLE);
//                            }
//                            case 2:{
//                                table_shijian1.setVisibility(View.VISIBLE);
//                                table_shijian2.setVisibility(View.VISIBLE);
//                                table_shijian3.setVisibility(View.VISIBLE);
//                            }
//                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );
        spinner3.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );
        spinner4.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                }
        );
    }

}
