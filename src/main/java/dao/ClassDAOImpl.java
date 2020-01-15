package dao;

import model.Class;
import service.response.StudentGrade;
import service.response.StudentNewClass;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ClassDAOImpl implements ClassDAO {
    JdbcTemplate jdbcTemplate;

    public ClassDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Class> getAllClasses() {
        return jdbcTemplate.query("SELECT * FROM Classes", new BeanPropertyRowMapper<>(Class.class));
    }


    @Override
    public List<StudentNewClass> getNewClasses() {
        return jdbcTemplate.query("SELECT Students.studentID, Students.name, Students.address, " +
                        "Students.phone, csg.className, csg.grade, cp.className as prerequisiteFor " +
                        "FROM Students, csg, cp "+
                        "WHERE Students.studentID=csg.studentID AND csg.className=cp.prerequisite;",
                new BeanPropertyRowMapper<>(StudentNewClass.class));
    }

    @Override
    public List<StudentGrade>  getStudentGrades() {
        return jdbcTemplate.query("SELECT Students.studentID, Students.name, Students.address, " +
                        "Students.phone, csg.className, Classes.professor, csg.grade " +
                        "FROM Students, csg, Classes "+
                        "WHERE Students.studentID=csg.studentID AND Classes.className=csg.className;",
                new BeanPropertyRowMapper<>(StudentGrade.class));
    }

    @Override
    public List<Class> queryClassesByName(String className) {
        return jdbcTemplate.query("SELECT * FROM Classes WHERE className=?", new Object[]{className},
                new BeanPropertyRowMapper<>(Class.class));
    }

    @Override
    public boolean createAClass(Class aClass) {
        if (aClass == null) {
            return false;
        }
        jdbcTemplate.update("INSERT INTO Classes(classID, className, room, professor) values(?,?,?,?)",
                aClass.getClassID(), aClass.getClassName(), aClass.getRoom(), aClass.getProfessor());
        return true;
    }

    @Override
    public boolean deleteAllClasses() {
        jdbcTemplate.update("DELETE FROM Classes");
        return true;
    }

    @Override
    public boolean deleteClass(Class aClass) {
        if (aClass == null) {
            return false;
        }
        jdbcTemplate.update("DELETE FROM aClass WHERE classID=?", aClass.getClassID());
        return true;
    }

    @Override
    public boolean updateClass(Class aClass) {
        if (aClass == null) {
            return false;
        }
        jdbcTemplate.update("UPDATE Students SET name = ?, address = ?, phone = ? where ClassID = ?",
                aClass.getClassName(), aClass.getRoom(), aClass.getProfessor(), aClass.getClassID());
        return true;
    }
}
