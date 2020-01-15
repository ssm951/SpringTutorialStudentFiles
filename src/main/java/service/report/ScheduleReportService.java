package service.report;

import dao.ClassDAO;
import model.Class;
import service.response.StudentNewClass;

import java.util.List;

public class ScheduleReportService implements ReportService {

    private ClassDAO classDAO;
    private List<Class> classes;

    public ScheduleReportService(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public void printReport() {
        System.out.println("ScheduleReportService invoked. ");
        if (classes == null) {
            System.out.println("No new classes injected. Use setter injection to inject new classes for the semester.");
            return;
        }
        System.out.println("Printing new schedule: ");
        List<StudentNewClass> newClasses = classDAO.getNewClasses();
        classDAO.deleteAllClasses();

        for (Class obj : classes) {
            classDAO.createAClass(obj);
        }

        for (int i = 0; i < newClasses.size(); i++) {
            System.out.println(newClasses.get(i).toString());
            printNewClass(newClasses.get(i));
            for (int j = i + 1; j < newClasses.size(); j++) {
                if (newClasses.get(j).getStudentID() == newClasses.get(i).getStudentID()) {
                    printNewClass(newClasses.get(j));
                    newClasses.remove(j);
                    j--;
                }
            }
        }
    }

    private void printNewClass(StudentNewClass studentNewClass) {
        if (studentNewClass.getGrade().contains("F")) {
            return;
        }
        List<Class> query = classDAO.queryClassesByName(studentNewClass.getPrerequisiteFor());
        if (query.size() == 1) {
            System.out.println("     " + query.get(0).toString());
        }
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public ClassDAO getClassDAO() {
        return classDAO;
    }
}
