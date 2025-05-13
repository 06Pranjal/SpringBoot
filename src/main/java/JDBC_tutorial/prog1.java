package JDBC_tutorial;
import java.sql.*;

public class prog1 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";


        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Succesfully");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

    try{
        Connection con =DriverManager.getConnection(url,username,password);
        System.out.println("Connection etablished Successfully");
    } catch (SQLException e) {
        System.out.println(e.getMessage());;
    }

    }
}
