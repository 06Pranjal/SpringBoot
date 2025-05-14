package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class prog3 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String query="DELETE FROM Employees WHERE id=3;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);

            Statement stmt=con.createStatement();
            int rowsAffected=stmt.executeUpdate(query);

            if(rowsAffected>0){
                System.out.println("Delete Successfull "+ rowsAffected+" rows affected");
            }else {
                System.out.println("Deletion Failed");
            }

            stmt.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
