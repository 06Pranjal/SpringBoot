package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.io.*;
import java.sql.*;

public class prog8 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String folder_path="C:\\Users\\ASUS\\Downloads\\";
        String query="SELECT image_data from image_table where image_id=(?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement=con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet =preparedStatement.executeQuery();

            if(resultSet.next()){
                byte[] image_data=resultSet.getBytes("image_data");
                String image_path=folder_path+"extractedImage.jpg";
                OutputStream outputStream=new FileOutputStream(image_path);
                outputStream.write(image_data);
                System.out.println("Image Inserted into the Folder");
            }else
                System.out.println("Image not found");


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
