package service.report;

import dao.ClassDAO;
import service.response.StudentGrade;

import java.util.List;

public class GradesReportService implements ReportService {
    private ClassDAO classDAO;

    public GradesReportService(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public void printReport() {
        System.out.println("GradesReportService invoked. ");
        System.out.println("Printing Student Grades: ");
        List<StudentGrade> grades = classDAO.getStudentGrades();

        // Just printing the query prettier. Could also chose to store the information in better model classes but
        // that requires custom row mapping.
        for(int i = 0; i < grades.size(); i++) {
            System.out.println(grades.get(i).toString());
            System.out.println(grades.get(i).gradeToString());
            for (int j = i+1; j< grades.size(); j++) {
                if (grades.get(j).getStudentID() == grades.get(i).getStudentID()) {
                    System.out.println(grades.get(j).gradeToString());
                    grades.remove(j);
                    j--;
                }
            }
        }
    }
}
