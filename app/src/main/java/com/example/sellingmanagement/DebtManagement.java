package com.example.sellingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sellingmanagement.AdapterManagement.AdapterDebt;
import com.example.sellingmanagement.Dataset.Debt;

import java.sql.SQLException;
import java.util.ArrayList;

public class DebtManagement extends AppCompatActivity {
    // khơi tạo biến
    private ImageButton buttonBack;
    private TextView noData;
    private ListView listView;
    private ArrayList<Debt> arrayList;
    private AdapterDebt adapterDebt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_management);
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
        Intent intent = new Intent(DebtManagement.this,MainActivity.class);
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
        buttonBack = findViewById(R.id.debtManagement_back);
        noData = findViewById(R.id.debtManagement_noData);
        listView = findViewById(R.id.debtManagement_listView);

        try {
            arrayList = Debt.getuserlist();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adapterDebt = new AdapterDebt(this,arrayList);
        listView.setAdapter(adapterDebt);
    }
}