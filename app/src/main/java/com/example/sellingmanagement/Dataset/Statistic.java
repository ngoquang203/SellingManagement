package com.example.sellingmanagement.Dataset;

import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Statistic {
    private String IdStatistic;
    private String ID;
    private long Revenue;
    private long Expense;
    private long Tax;

    public Statistic(String idStatistic, String ID, long revenue, long expense, long tax) {
        IdStatistic = idStatistic;
        this.ID = ID;
        Revenue = revenue;
        Expense = expense;
        Tax = tax;
    }

    // hàm in ra danh sách báo cáo thống kê
    public static ArrayList<Statistic> getuserlist() throws SQLException { // Hàm lấy dữ liệu
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL server
        ArrayList<Statistic> list = new ArrayList<>(); // Tạo list để lưu dữ liệu
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from Statistic"; // Câu lênh truy vấn SQL Server lấy ra dữ liệu trong bảng
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Statistic(
                    rs.getString("1").trim(), // Lấy dữ liệu
                    rs.getString("2").trim(),
                    rs.getLong("3"),
                    rs.getLong("4"),
                    rs.getLong("5")));// Đọc dữ liệu từ ResultSet
        }
        statement.close(); // Đóng đối tương statement
        connection.close();// Đóng kết nối
        return list; // Trả về list
    }

    // hàm ínsert báo cáo thống kê
    public static void insertList(String idStatistic,String id,long revenue,long expense,long tax) throws SQLException{
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "insert into Statistic(IdStatistic,ID,Revenue,Expense,Tax) values ('" + idStatistic + "','" + id +
                "'," + revenue + "," + expense + "," + tax + ")"; // Câu lênh SQL Server thêm hàng mới trong bảng báo cáo thống kê
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tượng Statement
        connection.close(); // Đóng kết nối
    }

    // hàm update báo cáo thống kê
    public static void updateList(String idStatistic,long revenue,long expense,long tax) throws SQLException{
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "update Statistic set Revenue = " + revenue + ",Expense = " + expense + ",Tax = " + tax + "= where IdStatistic = '" + idStatistic + "'"; // Câu lênh SQL Server sửa đổi thông tin báo cáo thống kê
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tượng Statement
        connection.close(); // Đóng kết nối

    }

    // hàm xóa báo cáo thống kê thông qua idStatistic
    public static void deleteList(String idStatistic) throws SQLException{
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "delete from Statistic where IdStatistic = '" + idStatistic + "'"; // Câu lênh SQL Server xóa hàng có Cột idStatistic trung với dữ liệu truyền vào
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tương Statament
        connection.close(); // Đóng kết nối
    }

    public String getIdStatistic() {
        return IdStatistic;
    }

    public void setIdStatistic(String idStatistic) {
        IdStatistic = idStatistic;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public long getRevenue() {
        return Revenue;
    }

    public void setRevenue(long revenue) {
        Revenue = revenue;
    }

    public long getExpense() {
        return Expense;
    }

    public void setExpense(long expense) {
        Expense = expense;
    }

    public long getTax() {
        return Tax;
    }

    public void setTax(long tax) {
        Tax = tax;
    }
}
