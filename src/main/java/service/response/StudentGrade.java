package service.response;

public class StudentGrade extends Report {
    private String professor;

    public StudentGrade() {
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String gradeToString() {
        return "       {" + "className='" + className + '\'' +
                ", professor='" + professor + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
