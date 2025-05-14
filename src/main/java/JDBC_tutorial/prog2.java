package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class prog2 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String query="INSERT INTO Employees(id,name,job_title,salary) VALUES (3,'Harsh','Backend Engineer',90000.0);";

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
                System.out.println("Insert Successfull "+ rowsAffected+" rows affected");
            }else {
                System.out.println("Insertion Failed");
            }



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
