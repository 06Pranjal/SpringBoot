package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class prog7 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String image_path="C:\\Users\\ASUS\\Downloads\\sunset-silhouettes-trees-mountains-generative-ai.jpg";
        String query="INSERT INTO image_table(image_data) VALUES(?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);

            FileInputStream fileInputStream =new FileInputStream(image_path);
            byte[] imageData=new byte[fileInputStream.available()];
            fileInputStream.read(imageData);

            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int affectedRows=preparedStatement.executeUpdate();
            if(affectedRows>0)
                System.out.println("Image Inserted");
            else
                System.out.println("Failed");


        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
