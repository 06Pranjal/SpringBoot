package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class prog5 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String query="Select * from Employees where name= ? AND job_title= ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);

            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setString(1,"Hemant");
            preparedStatement.setString(2,"Full Stack Dev");
            ResultSet resultSet =preparedStatement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String job_title=resultSet.getString("job_title");
                double salary=resultSet.getDouble("salary");

                System.out.println("ID="+id);
                System.out.println("Name="+name);
                System.out.println("Job Title="+job_title);
                System.out.println("Salary="+salary);
            }




            con.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}

