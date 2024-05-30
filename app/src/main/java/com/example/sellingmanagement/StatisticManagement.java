package com.example.sellingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellingmanagement.AdapterManagement.AdapterDebt;
import com.example.sellingmanagement.AdapterManagement.AdapterStatistic;
import com.example.sellingmanagement.Dataset.Debt;
import com.example.sellingmanagement.Dataset.Statistic;

import java.sql.SQLException;
import java.util.ArrayList;

public class StatisticManagement extends AppCompatActivity {
    // khơi tạo biến
    private ImageButton buttonBack;
    private TextView noData;
    private ListView listView;
    private ArrayList<Statistic> arrayList;
    private AdapterStatistic adapterStatistic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_management);
        Init(); // khởi tạo giá trị
        clickButtonBack(); // hàm xử lí sự kiện click Back
        setNoData();
    }

    // hàm set hiển thị khi size của listview = 0
    private void setNoData() {
        if(arrayList.size() == 0){
            listView.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        }
    }
    private void changePage(){
        // code chuyển giao diện về màn hình main
        Intent intent = new Intent(StatisticManagement.this,MainActivity.class);
        startActivity(intent);
    }
    // hàm xử lí sự kiện click Back
    private void clickButtonBack() {
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePage(); // hàm intent chuyển giao diện
            }
        });
    }
    private void Init() {
        // ánh xạ View
        buttonBack = findViewById(R.id.statisticManagement_back);
        noData = findViewById(R.id.statisticManagement_noData);
        listView = findViewById(R.id.statisticManagement_listView);

        try {
            arrayList = Statistic.getuserlist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adapterStatistic = new AdapterStatistic(this,arrayList);
        listView.setAdapter(adapterStatistic);
    }
}