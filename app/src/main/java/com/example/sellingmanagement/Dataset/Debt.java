package com.example.sellingmanagement.Dataset;

import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Debt {
    private String IdDebt;
    private String ID;
    private String NameDebt;
    private String Account;
    private int CreditDebit;
    private long Moneys;

    public Debt(String idDebt, String ID, String nameDebt, String account, int creditDebit, long moneys) {
        IdDebt = idDebt;
        this.ID = ID;
        NameDebt = nameDebt;
        Account = account;
        CreditDebit = creditDebit;
        Moneys = moneys;
    }

    // hàm in ra danh sách công nợ
    public static ArrayList<Debt> getuserlist() throws SQLException { // Hàm lấy dữ liệu
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL server
        ArrayList<Debt> list = new ArrayList<>(); // Tạo list để lưu dữ liệu
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from Debt"; // Câu lênh truy vấn SQL Server lấy ra dữ liệu trong bảng
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            list.add(new Debt(
                    rs.getString("1").trim(), // Lấy dữ liệu
                    rs.getString("2").trim(),
                    rs.getString("3"),
                    rs.getString("4"),
                    rs.getInt(5),
                    rs.getLong(5)));// Đọc dữ liệu từ ResultSet
        }
        statement.close(); // Đóng đối tương statement
        connection.close();// Đóng kết nối
        return list; // Trả về list
    }

    // hàm ínsert công nợ
    public static void insertList(String idDebt,String id,String nameDebt,String account,int creditDebit, long moneys) throws SQLException{
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "insert into Debt(IdDebt,ID,NameDebt,Account,CreditDebit,Moneys) values('" + idDebt + "','" +
                id + "','" + nameDebt + "','" + account + "','" + creditDebit + "'," + moneys +  ")"; // Câu lênh SQL Server thêm hàng mới trong bảng Debt
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tượng Statement
        connection.close(); // Đóng kết nối
    }

    // hàm update công nợ
    public static void updateList(String idDebt,String nameDebt,String account,int creditDebit, long moneys) throws SQLException{
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "update Debt set NameDebt = '" + nameDebt + "', Account = '" + account + "',CreditDebit = " + creditDebit + ",Moneys = " + moneys + " where IdDebt = '" + idDebt + "'"; // Câu lênh SQL Server sửa đổi thông tin công nợ
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tượng Statement
        connection.close(); // Đóng kết nối

    }

    // hàm xóa công nợ thông qua idDebt
    public static void deleteList(String idDebt) throws SQLException{
        Connection connection = SQLServerHelper.connectionSQLSever(); // Kết nối với SQL Server
        Statement statement = connection.createStatement(); // Tạo đối tượng Statement.
        String sql = "delete from Debt where IdDebt = '" + idDebt + "'"; // Câu lênh SQL Server xóa hàng có Cột idDebt trung với dữ liệu truyền vào
        statement.execute(sql); // Thực thi câu lệnh
        statement.close(); // Đóng đối tương Statament
        connection.close(); // Đóng kết nối
    }


    public String getIdDebt() {
        return IdDebt;
    }

    public void setIdDebt(String idDebt) {
        IdDebt = idDebt;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNameDebt() {
        return NameDebt;
    }

    public void setNameDebt(String nameDebt) {
        NameDebt = nameDebt;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public int getCreditDebit() {
        return CreditDebit;
    }

    public void setCreditDebit(int creditDebit) {
        CreditDebit = creditDebit;
    }

    public long getMoneys() {
        return Moneys;
    }

    public void setMoneys(long moneys) {
        Moneys = moneys;
    }
}
