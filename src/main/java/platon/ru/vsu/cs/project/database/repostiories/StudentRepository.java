package platon.ru.vsu.cs.project.database.repostiories;

import platon.ru.vsu.cs.adb_lib.repository.Repository;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.adb_lib.sql.Filter;

import java.util.List;

public interface StudentRepository extends Repository<Student> {
    default List<Student> getStudentsByGroup(int groupId){
        return getAll(new Filter[]{new Filter("groupId", groupId)});
    }
}
