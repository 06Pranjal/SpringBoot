package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class prog6 {
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

            Scanner sc=new Scanner(System.in);
            System.out.print("Enter new id to be filled: ");
            int id=sc.nextInt();
            sc.nextLine();
            System.out.print("Enter name to be filled: ");
            String name=sc.nextLine();
            System.out.print("Enter Job Title: ");
            String job_title=sc.nextLine();
            System.out.print("Enter salary: ");
            double salary= sc.nextDouble();

            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,job_title);
            preparedStatement.setDouble(4,salary);
            int rowsAffected=preparedStatement.executeUpdate();

            if(rowsAffected>0){
                System.out.println("Insert Successfull "+ rowsAffected+" rows affected");
            }else {
                System.out.println("Insertion Failed");
            }
//




            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}


