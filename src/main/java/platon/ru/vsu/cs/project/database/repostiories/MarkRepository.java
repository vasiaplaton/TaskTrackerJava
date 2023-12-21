package platon.ru.vsu.cs.project.database.repostiories;

import platon.ru.vsu.cs.adb_lib.repository.Repository;
import platon.ru.vsu.cs.adb_lib.sql.Filter;
import platon.ru.vsu.cs.project.database.models.Mark;

import java.util.List;

public interface MarkRepository extends Repository<Mark> {
    default List<Mark> getMarkByStudent(int studentId){
        return getAll(new Filter[]{new Filter("studentId", studentId)});
    }

    default void setMark(int studentId, int taskId, boolean done){
        List<Mark> marks = getAll(new Filter[]{new Filter("studentId", studentId),
                new Filter("taskId", taskId)});
        Mark mark = null;
        if (marks.size() > 0) {
            mark = marks.get(0);
        }
        if(done){
            if(mark != null){
                delete(mark.getId());
            }
            add(new Mark(true, studentId, taskId));
        }
        else {
            if(mark == null){
                throw new IllegalArgumentException();
            }
            delete(mark.getId());
        }
    }
}
