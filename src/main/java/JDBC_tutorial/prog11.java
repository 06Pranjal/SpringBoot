package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class prog11 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String query="INSERT INTO Employees(id,name,job_title,salary) VALUES(?,?,?,?)";



        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            con.setAutoCommit(false);
            Scanner sc=new Scanner(System.in);
            PreparedStatement preparedStatement =con.prepareStatement(query);
            while (true) {
                System.out.println("Enter the Details to need to fill in the Database");
                System.out.print("ID: ");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.print("Name: ");
                String name=sc.nextLine();
                System.out.print("Job Title: ");
                String job_title=sc.nextLine();
                System.out.print("Salary: ");
                double salary=sc.nextDouble();

                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,job_title);
                preparedStatement.setDouble(4,salary);
                preparedStatement.addBatch();

                System.out.print("Want to add more Values(Y/N): ");
                String decision=sc.next();
                if(decision.equalsIgnoreCase("N")){
                    break;
                }
            }
            int[] batchResult=preparedStatement.executeBatch();
            con.commit();
            System.out.println("Batch executed Successfully");
        }

        catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
