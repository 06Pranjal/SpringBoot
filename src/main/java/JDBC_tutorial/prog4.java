package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class prog4 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String query="UPDATE Employees\n " +
                "SET job_title='Full Stack Dev', salary= 70000.5\n" +
                "WHERE id= 2;";

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
                System.out.println("Update Successfull "+ rowsAffected+" rows affected");
            }else {
                System.out.println("Updation Failed");
            }

            stmt.close();
            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
