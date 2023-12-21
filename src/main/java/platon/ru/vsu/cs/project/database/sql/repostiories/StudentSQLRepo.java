package platon.ru.vsu.cs.project.database.sql.repostiories;

import platon.ru.vsu.cs.adb_lib.sql.SQLRepository;
import platon.ru.vsu.cs.adb_lib.sql.conns.PostgreSQLConn;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;

public class StudentSQLRepo extends SQLRepository<Student> implements StudentRepository {
    private StudentSQLRepo() {
        super(Student.class, PostgreSQLConn.getInstance());
    }

    private static StudentSQLRepo INSTANCE;

    public static StudentSQLRepo getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new StudentSQLRepo();
        }
        return INSTANCE;
    }
}
