package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class prog10 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";



        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            con.setAutoCommit(false);
            Statement statement =con.createStatement();
            statement.addBatch("INSERT INTO Employees(id,name,job_title,salary) VALUES(6,'Vashu','HR Manager',65000.0);");
            statement.addBatch("INSERT INTO Employees(id,name,job_title,salary) VALUES(7,'Karan','Devops Engineer',65078.0);");
            statement.addBatch("INSERT INTO Employees(id,name,job_title,salary) VALUES(8,'Vipul','AWS Expert',69000.0);");
            int[] batchResult=statement.executeBatch();
            con.commit();
            System.out.println("Batch executed Successfully");




            }

        catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
