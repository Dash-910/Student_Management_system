package com.student.manage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;

public class StudentDao {

    public static boolean insertStudentToDB(Student st) {
        boolean flag = false;
        try {
            Connection con = CP.createC();
            String query = "INSERT INTO students (sname, sphone, address) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, st.getStudentName());
            pstmt.setString(2, st.getStudentPhone());

            // Converting the address object to JSON
            String addressJson = convertAddressToJson(st.getStudentAddress());
            pstmt.setString(3, addressJson);

            // Execute the query
            pstmt.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Delete A Student
    public static boolean deleteStudent(int studentId) {
        boolean flag = false;
        try {
            Connection con = CP.createC();
            String query = "DELETE FROM students WHERE sid=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, studentId);

            pstmt.executeUpdate();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Show all Students
    public static void showAllStudents() {

        try {
            Connection con = CP.createC();
            String query = "SELECT * FROM students";

            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);

            while (set.next()) {
                int id = set.getInt("sid"); // Change "id" to "sid"
                String name = set.getString("sname");
                String phone = set.getString("sphone");
                String addressJson = set.getString("address");

                Address address = parseAddressFromJson(addressJson);

                System.out.println("Id: " + id);
                System.out.println("Name: " + name);
                System.out.println("Phone: " + phone);
                System.out.println("Address: " + address.getStreet() + ", " + address.getCity() + ", " + address.getState());
                System.out.println("**************************");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Added a method to parse the address JSON into an Address object
    private static Address parseAddressFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Address.class);
    }

    // Update The student info
    public static boolean updateStudent(int studentId, String newName, String newPhone, Address newAddress) {
        boolean flag = false;
        try {
            Connection con = CP.createC();
            String query = "UPDATE students SET sname=?, sphone=?, address=? WHERE sid=?";
            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, newName);
            pstmt.setString(2, newPhone);

            String addressJson = convertAddressToJson(newAddress);
            pstmt.setString(3, addressJson);

            pstmt.setInt(4, studentId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                flag = true;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean isStudentExists(int studentId) {
        boolean exists = false;

        try {
            Connection con = CP.createC();

            String query = "SELECT COUNT(*) FROM students WHERE sid=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, studentId);

            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();

            int count = resultSet.getInt(1);

            if (count > 0) {
                exists = true;
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    private static String convertAddressToJson(Address address) {
        Gson gson = new Gson();
        return gson.toJson(address);
    }
}
