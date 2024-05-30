package com.example.sellingmanagement.AdapterManagement;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sellingmanagement.AddDebt;
import com.example.sellingmanagement.Dataset.Debt;
import com.example.sellingmanagement.R;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterDebt extends BaseAdapter {

    Context context;
    ArrayList<Debt> arrayList;
    LayoutInflater layoutInflater;
    public AdapterDebt(Context context,ArrayList<Debt> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_debt,null); // ánh xạ layout

        TextView IdDebt = convertView.findViewById(R.id.itemDebt_idDebt);
        TextView NameDebt = convertView.findViewById(R.id.itemDebt_nameDebt);
        TextView Account = convertView.findViewById(R.id.itemDebt_account);
        TextView CreditDebit = convertView.findViewById(R.id.itemDebt_creditDebit);
        TextView Moneys = convertView.findViewById(R.id.itemDebt_moneys);
        TextView Edit = convertView.findViewById(R.id.itemDebt_edit);
        TextView Delete = convertView.findViewById(R.id.itemDebt_delete);

        Debt debt = arrayList.get(position); // lấy dữ liệu trong danh sách
        if(debt != null){
            // set các giá tri cho view
            IdDebt.setText(debt.getIdDebt());
            NameDebt.setText(debt.getNameDebt());
            Account.setText(debt.getAccount());
            CreditDebit.setText(String.valueOf(debt.getCreditDebit()));
            Moneys.setText(String.valueOf(debt.getMoneys()));

            // chuyển đến trang chỉnh sửa Debt
            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddDebt.class);
                    intent.putExtra("initData",true);
                    intent.putExtra("IdDebt",debt.getIdDebt());
                    context.startActivity(intent);
                }
            });

            // xóa công nợ tại vị trí này
            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Debt.deleteList(debt.getIdDebt());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    arrayList.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }
}
