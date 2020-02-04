package service.response;

import model.Student;

import java.util.Objects;

public abstract class Report {
    Student student;
    String className;
    String grade;

    public Report() {
    }

    public Report(int studentID, String name, String address, String phone, String className, String grade) {
        this.student = new Student(studentID, name, address, phone);
        this.className = className;
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public int getStudentID() {
        return student.getStudentID();
    }

    @Override
    public String toString() {
        String sb = student.toString() + "Report{" +
                "className='" + className + '\'' +
                ", grade='" + grade + '\'' +
                '}';
        return sb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return className.equals(report.className) &&
                grade.equals(report.grade) && student.equals(report.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), className, grade);
    }
}
