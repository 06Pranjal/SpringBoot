package JDBC_tutorial.Projects.HospitalSystem;

import java.sql.*;
import java.util.Scanner;

public class Hospital {
    private static final String url = "jdbc:mysql://localhost:3306/Hospital";
    private static final String username = "root";
    private static final String password = "123456";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            patient patient = new patient(connection, scanner);
            doctors doctors = new doctors(connection);

            while (true) {
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patients");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointments");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        doctors.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        //Book Appointments
                        bookAppointment(patient, doctors, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        exit();
                        return;
                    default:
                        System.out.println("Invalid Choice");
                }

            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bookAppointment(patient patient, doctors doctor, Connection connection, Scanner scanner) {
        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
        String appointment_date = scanner.next();


        if (patient.getPatientById(patientId) && doctor.getDoctorById(doctorId)) {
            if (checkDoctorAvailabilty(doctorId, appointment_date, connection)) {
                String appointmentQuery = "INSERT INTO appointments(patient_id,doctor_id,appointment_date) VALUES(?,?,?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, patientId);
                    preparedStatement.setInt(2, doctorId);
                    preparedStatement.setString(3, appointment_date);
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Appointment Booked");
                    } else {
                        System.out.println("Failed To Book Appointment");
                    }
                } catch (
                        SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Doctor is not available on this date");
            }
        } else {
            System.out.println("Either Patient or Doctor Does not exist!!");
        }
    }

    public static boolean checkDoctorAvailabilty(int doctorId, String appointment_date, Connection connection) {
        String query = "Select Count(*) from appointments where doctor_id=? and appointment_date=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointment_date);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Coming!!!");
    }
}
