package com.student.manage;

public class Student {
    private int studentId;
    private String studentName;
    private String studentPhone;
    private Address studentAddress; 

    public Student(int studentId, String studentName, String studentPhone, Address studentAddress) {
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
    }

    public Student( String studentName, String studentPhone, Address studentAddress) {
        super();
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public Address getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Address studentAddress) {
        this.studentAddress = studentAddress;
    }

    public Student() {
        super();
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentPhone=" + studentPhone
                + ", studentAddress=" + studentAddress + "]";
    }
}