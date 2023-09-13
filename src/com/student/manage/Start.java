package com.student.manage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Student Management System");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Press 1 To Add a Student");
            System.out.println("Press 2 To Delete a Student");
            System.out.println("Press 3 To Display a Student info");
            System.out.println("Press 4 To Update a Student info");
            System.out.println("Press 5 To Exit app");

            int c = Integer.parseInt(br.readLine());

            if (c == 1) {
                // Add Student
                System.out.println("Enter user Name: ");
                String name = br.readLine();

                System.out.println("Enter user PhoneNo: ");
                String phone = br.readLine();

                System.out.println("Enter your Address(street,city,state)");
                String address = br.readLine();

                String[] parts = address.split(",");
                String street = parts[0];
                String city = parts[1];
                String state = parts[2];

                Address addr = new Address(street, city, state);

                Student st = new Student(name, phone, addr);

                System.out.println(st);

                boolean response = StudentDao.insertStudentToDB(st);

                if (response) {
                    System.out.println("Student added Successfully");

                } else {
                    System.out.println("Something went wrong");
                }

            } else if (c == 2) {
                // Delete a student

                System.out.println("Enter StudentId to delete: ");
                int userId = Integer.parseInt(br.readLine());
                boolean response = StudentDao.deleteStudent(userId);
                if (response) {
                    System.out.println("Deleted....");
                } else {
                    System.out.println("Something went wrong");
                }
            } else if (c == 3) {
                // Show all student data
                StudentDao.showAllStudents();
            } else if (c == 4) {
                // Update a student info

                System.out.println("Enter StudentId to update: ");
                int userId = Integer.parseInt(br.readLine());

                if (StudentDao.isStudentExists(userId)) {
                    System.out.println("Enter new user Name: ");
                    String newName = br.readLine();

                    System.out.println("Enter new user PhoneNo: ");
                    String newPhone = br.readLine();

                    System.out.println("Enter new Address(street,city,state): ");
                    String newAddressStr = br.readLine();

                    String[] addressParts = newAddressStr.split(",");
                    String newStreet = addressParts[0];
                    String newCity = addressParts[1];
                    String newState = addressParts[2];

                    Address newAddress = new Address(newStreet, newCity, newState);

                    boolean response = StudentDao.updateStudent(userId, newName, newPhone, newAddress);

                    if (response) {
                        System.out.println("Student info updated successfully");
                    } else {
                        System.out.println("Something went wrong");
                    }
                } else {
                    System.out.println("Student with Id " + userId + " does not exist");
                }
            } else if (c == 5) {
                break; // Exit the loop
            } else {
                System.out.println("Invalid option. Please select a valid option.");
            }
        }
        System.out.println("Goodbye");
    }
}
