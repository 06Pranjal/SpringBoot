package JDBC_tutorial.Projects.HospitalSystem;

import com.mysql.cj.util.DnsSrv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class doctors {
    private Connection connection;


    public doctors(Connection connection, Scanner scanner){
        this.connection=connection;
    }



    public void viewDoctors(){
        String query="Select * from doctors";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("Doctors: ");
                System.out.println("+------------+--------------------+------------------------+");
                System.out.println("| Doctors Id | Name               | Specs                  |");
                System.out.println("+------------+--------------------+------------------------+");
                while (resultSet.next()){
                    int id=resultSet.getInt("id");
                    String name=resultSet.getString("name");
                    String specs=resultSet.getString("specs");
                    System.out.printf("|%-13s|%-21s|%-25s|\n",id,name,specs);
                    System.out.println("+------------+--------------------+------------------------+");
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    public Boolean checkDoctorsById(int id){
        String query="Select * from doctors where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
