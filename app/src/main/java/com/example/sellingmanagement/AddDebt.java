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
import com.google.android.material.textfield.TextInputEditText;

import java.sql.SQLException;

public class AddDebt extends AppCompatActivity {
    // khai báo biển
    private boolean initData;
    private ImageButton buttonBack;
    private TextInputEditText IdDebt,NameDebt,Account,Moneys;
    private Button buttonInsertDebt;
    private TextView Credit,Debit,textHeading;
    private String idDebt,type;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_debt);
        Init(); // hàm khởi tạo giá trị
        clickButtonBack(); // hàm xử lí sự kiện click Back
        clickButtonInsertDebt(); // hàm xử lí xự kiện click insert Debt
        clickCreditAndDebit(); // hàm xử lí sự kiện khi nhấn 1 trong 2 nút
    }

    private void clickCreditAndDebit() {
        Credit.setOnClickListener(new View.OnClickListener() { // sự kiện khi click vào Credit
            @Override
            public void onClick(View v) {
                type = "Nợ";
                Credit.setBackgroundColor(getResources().getColor(R.color.primary));
                Debit.setBackgroundColor(getResources().getColor(R.color.button));
            }
        });
        Debit.setOnClickListener(new View.OnClickListener() { // sự kiện khi click vào Debit
            @Override
            public void onClick(View v) {
                type = "Có";
                Credit.setBackgroundColor(getResources().getColor(R.color.button));
                Debit.setBackgroundColor(getResources().getColor(R.color.primary));
            }
        });
    }

    // hàm xử lí xự kiện click insert Debt
    private void clickButtonInsertDebt() {
        // vào sửa data trong sql
        if(initData){
            buttonInsertDebt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // lấy dữ liệu từ ô nhập
                    String nameDebt = NameDebt.getText().toString();
                    String account = Account.getText().toString();
                    String moneys = Moneys.getText().toString();
                    // kiểm tra xem các ô nhập liệu có rỗng
                    if (nameDebt.isEmpty() || account.isEmpty() || moneys.isEmpty()){
                        Toast.makeText(AddDebt.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            Debt.updateList(idDebt,nameDebt,account,type,moneys); // update dữ liệu
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(AddDebt.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show(); // thông báo
                        changePage(); // hàm intent chuyển giao diện
                    }
                }
            });
        }else{ // ínert data trong sql
            buttonInsertDebt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // lấy dữ liệu từ ô nhập
                    String idDebts = IdDebt.getText().toString();
                    String id = sharedPreferences.getString("ID","not");
                    String nameDebt = NameDebt.getText().toString();
                    String account = Account.getText().toString();
                    String moneys = Moneys.getText().toString();
                    // kiểm tra xem các ô nhập liệu có rỗng
                    if (idDebts.isEmpty() || nameDebt.isEmpty() || account.isEmpty() || moneys.isEmpty()){

                        Toast.makeText(AddDebt.this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            Debt.insertList(idDebts,id,nameDebt,account,type,moneys); // insert đữ liệu
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        Toast.makeText(AddDebt.this, "Lưu thông tin thành công", Toast.LENGTH_SHORT).show(); // thông báo
                        changePage(); // hàm intent chuyển giao diện
                    }
                }
            });
        }
    }

    private void changePage(){
        // code chuyển giao diện về màn hình main
        Intent intent = new Intent(AddDebt.this,MainActivity.class);
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

    // hàm khởi tạo giá trị
    private void Init() {
        // ánh xạ View
        sharedPreferences = getSharedPreferences("loginData",MODE_PRIVATE);
        initData = getIntent().getBooleanExtra("initData",false);
        idDebt = getIntent().getStringExtra("IdDebt");
        type = "Nợ";
        textHeading = findViewById(R.id.addDebt_textHeading);
        buttonBack = findViewById(R.id.addDebt_back);
        IdDebt = findViewById(R.id.addDebt_idDebt);
        NameDebt = findViewById(R.id.addDebt_nameDebt);
        Account = findViewById(R.id.addDebt_account);
        Moneys = findViewById(R.id.addDebt_moneys);
        buttonInsertDebt = findViewById(R.id.addDebt_saveData);
        Credit = findViewById(R.id.addDebt_credit);
        Debit = findViewById(R.id.addDebt_debit);
        Credit.setBackgroundColor(getResources().getColor(R.color.primary));
        Debit.setBackgroundColor(getResources().getColor(R.color.button));
        if (initData){
            // chuyển đổi thành màn hình sửa công nợ
            textHeading.setText("Sửa công nợ");
            IdDebt.setText(idDebt);
            IdDebt.setEnabled(false);
        }
    }
}