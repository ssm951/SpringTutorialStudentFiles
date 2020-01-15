package service.response;

import model.Student;

import java.util.Objects;

public class Report extends Student {
    String className;
    String grade;

    public Report() {
    }

    public Report(int studentID, String name, String address, String phone, String className, String grade) {
        super(studentID, name, address, phone);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Report{");
        sb.append("className='").append(className).append('\'');
        sb.append(", grade='").append(grade).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        if (!super.equals(o)) return false;
        Report report = (Report) o;
        return className.equals(report.className) &&
                grade.equals(report.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), className, grade);
    }
}
