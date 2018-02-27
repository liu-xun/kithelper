package baidumapsdk.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈薄文 on 2018/2/10.
 */

public class MainActivity extends AppCompatActivity {
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);
        initFruits(); // 初始化水果数据
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);

                if (position==0){
                    Intent zhixi =new Intent(MainActivity.this,Zhixi.class);
                    startActivity(zhixi);}
                if (position==1){
                    Intent shangkou =new Intent(MainActivity.this,Shangkou.class);
                    startActivity(shangkou);}
                if (position==2){
                    Intent yunjue =new Intent(MainActivity.this,Yunjue.class);
                    startActivity(yunjue);}
                if (position==3){
                    Intent Tangshang =new Intent(MainActivity.this,Tangshang.class);
                    startActivity(Tangshang);}
                if (position==4){
                    Intent Dongshang =new Intent(MainActivity.this,Dongshang.class);
                    startActivity(Dongshang);}
                if (position==5){
                    Intent Tuojiu =new Intent(MainActivity.this,Tuojiu.class);
                    startActivity(Tuojiu);}
                if (position==6){
                    Intent Jiangwen =new Intent(MainActivity.this,Jiangwen.class);
                    startActivity(Jiangwen);}
                if (position==7){
                    Intent Gengse =new Intent(MainActivity.this,Gengse.class);
                    startActivity(Gengse);}
                if (position==8){
                    Intent Rengong =new Intent(MainActivity.this,Rengong.class);
                    startActivity(Rengong);}
                if (position==9){
                    Intent Zhitong =new Intent(MainActivity.this,Zhitong.class);
                    startActivity(Zhitong);}
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();

            };
        });}


    private void initFruits() {
        Fruit apple = new Fruit("窒息处理");
        fruitList.add(apple);
        Fruit banana = new Fruit("伤口处理");
        fruitList.add(banana);
        Fruit orange = new Fruit("晕厥处理");
        fruitList.add(orange);
        Fruit watermelon = new Fruit("烫伤处理");
        fruitList.add(watermelon);
        Fruit pear = new Fruit("冻伤处理");
        fruitList.add(pear);
        Fruit grape = new Fruit("脱臼处理");
        fruitList.add(grape);
        Fruit pineapple = new Fruit("降温处理");
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("梗塞处理");
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("人工呼吸办法");
        fruitList.add(cherry);
        Fruit mango = new Fruit("止痛处理");
        fruitList.add(mango);

    }

    }
