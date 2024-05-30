package com.example.sellingmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellingmanagement.Dataset.Debt;
import com.example.sellingmanagement.Dataset.Statistic;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class AddStatistic extends AppCompatActivity {
    // khai báo biển
    private boolean initData;
    private ImageButton buttonBack;
    private TextInputEditText IdStatistic,Revenue,Expense,Tax;
    private Button buttonInsertStatistic;
    private TextView textHeading;
    private String idStatistic;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_statistic);
        Init(); // hàm khởi tạo giá trị
        clickButtonBack(); // hàm xử lí sự kiện click Back
        clickButtonInsertStatistic(); // hàm xử lí xự kiện click insert Statistic
    }

    private void clickButtonInsertStatistic() {
        // vào sửa data trong sql
        if(initData){
            buttonInsertStatistic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // lấy dữ liệu từ ô nhập
                    String revenue = Revenue.getText().toString();
                    String expense = Expense.getText().toString();
                    String tax = Tax.getText().toString();
                    // kiểm tra xem các ô nhập liệu có rỗng
                    if (revenue.isEmpty() || expense.isEmpty() || tax.isEmpty()){
                        Toast.makeText(AddStatistic.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            Statistic.updateList(idStatistic,revenue,expense,tax); // update dữ liệu
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(AddStatistic.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show(); // thông báo
                        changePage(); // hàm intent chuyển giao diện
                    }
                }
            });
        }else{ // ínert data trong sql
            buttonInsertStatistic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // lấy dữ liệu từ ô nhập
                    String idStatistica = IdStatistic.getText().toString();
                    String id = sharedPreferences.getString("ID","not");
                    String revenue = Revenue.getText().toString();
                    String expense = Expense.getText().toString();
                    String tax = Tax.getText().toString();
                    // kiểm tra xem các ô nhập liệu có rỗng
                    if (idStatistica.isEmpty() || revenue.isEmpty() || expense.isEmpty() || tax.isEmpty()){

                        Toast.makeText(AddStatistic.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            Statistic.insertList(idStatistica,id,revenue,expense,tax); // insert đữ liệu
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(AddStatistic.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show(); // thông báo
                        changePage(); // hàm intent chuyển giao diện
                    }
                }
            });
        }
    }

    private void changePage(){
        // code chuyển giao diện về màn hình main
        Intent intent = new Intent(AddStatistic.this,MainActivity.class);
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
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        initData = getIntent().getBooleanExtra("initData",false);
        idStatistic = getIntent().getStringExtra("IdStatistic");
        textHeading = findViewById(R.id.addStatistic_textHeading);
        buttonBack = findViewById(R.id.addStatistic_back);
        IdStatistic = findViewById(R.id.addStatistic_idStatistic);
        Revenue = findViewById(R.id.addStatistic_revenue);
        Expense = findViewById(R.id.addStatistic_expense);
        Tax = findViewById(R.id.addStatistic_tax);
        buttonInsertStatistic = findViewById(R.id.addStatistic_saveData);
        if (initData){
            // chuyển đổi thành màn hình sửa công nợ
            textHeading.setText("Sửa báo cáo thống kê");
            IdStatistic.setText(idStatistic);
            IdStatistic.setEnabled(false);
        }
    }
}