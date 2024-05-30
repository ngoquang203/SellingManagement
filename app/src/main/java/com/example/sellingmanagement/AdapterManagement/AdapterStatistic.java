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
import com.example.sellingmanagement.AddStatistic;
import com.example.sellingmanagement.Dataset.Statistic;
import com.example.sellingmanagement.R;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdapterStatistic extends BaseAdapter {
    Context context;
    ArrayList<Statistic> arrayList;
    LayoutInflater layoutInflater;
    public AdapterStatistic(Context context,ArrayList<Statistic> arrayList){
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
        convertView = layoutInflater.inflate(R.layout.item_statistic,null); // ánh xạ layout

        TextView IdStatistic = convertView.findViewById(R.id.itemStatistic_idStatistic);
        TextView Revenue = convertView.findViewById(R.id.itemStatistic_revenue);
        TextView Expense = convertView.findViewById(R.id.itemStatistic_expense);
        TextView Tax = convertView.findViewById(R.id.itemStatistic_tax);
        TextView Edit = convertView.findViewById(R.id.itemStatistic_edit);
        TextView Delete = convertView.findViewById(R.id.itemStatistic_delete);

        Statistic statistic = arrayList.get(position); // lấy dữ liệu trong danh sách

        if(statistic != null){
            // set các giá tri cho view
            IdStatistic.setText(statistic.getIdStatistic());
            Revenue.setText(String.valueOf(statistic.getRevenue()));
            Expense.setText(String.valueOf(statistic.getExpense()));
            Tax.setText(String.valueOf(statistic.getTax()));

            // chuyển đến trang chỉnh sửa
            Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AddStatistic.class);
                    intent.putExtra("initData",true);
                    intent.putExtra("IdStatistic",statistic.getIdStatistic());
                    context.startActivity(intent);
                }
            });

            // xóa thống kê tại vị trí này
            Delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Statistic.deleteList(statistic.getIdStatistic());
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
