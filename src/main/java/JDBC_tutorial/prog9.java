package JDBC_tutorial;

import javax.swing.plaf.nimbus.State;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class prog9 {
    public static void main(String[] args)throws ClassNotFoundException {

        String url="jdbc:mysql://localhost:3306/students";
        String username="root";
        String password= "123456";
        String withdrawQuery="UPDATE accounts SET balance= balance- ? WHERE account_number=?";
        String depositQuery="UPDATE accounts SET balance= balance+ ? WHERE account_number=?";


        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            con.setAutoCommit(false);
            try {
                PreparedStatement withdrawStatment=con.prepareStatement(withdrawQuery);
                PreparedStatement depositStatment=con.prepareStatement(depositQuery);
                withdrawStatment.setDouble(1,500.00);
                withdrawStatment.setString(2,"account456");
                depositStatment.setDouble(1,500);
                depositStatment.setString(2,"account79454");
                int rowsAffectedWithdrawal=withdrawStatment.executeUpdate();
                int rowsAffectedDeposit=depositStatment.executeUpdate();

                if(rowsAffectedWithdrawal>0&&rowsAffectedDeposit>0){
                    con.commit();
                    System.out.println("Transaction Successfull");
                }
                else {
                    con.rollback();
                    System.out.println("Transaction Failed");
                }


            }
            catch (SQLException e){
                System.out.println(e.getMessage());

            }




        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}
