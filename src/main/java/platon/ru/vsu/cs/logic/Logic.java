package platon.ru.vsu.cs.logic;

import platon.ru.vsu.cs.database.Database;
import platon.ru.vsu.cs.database.models.Mark;
import platon.ru.vsu.cs.database.models.Student;
import platon.ru.vsu.cs.database.sql.DatabaseSQL;
import platon.ru.vsu.cs.database.sql.table.Filter;

import java.util.List;

public class Logic {
    protected final Database db;
    private Logic(){
        this.db = DatabaseSQL.getInstance();
    }

    private static Logic INSTANCE;

    public static Logic getInstance() {
        if(INSTANCE == null){
            INSTANCE = new Logic();
        }
        return INSTANCE;
    }

    public List<Student> getStudentsByGroup(int groupId) {
        return db.getStudents(new Filter[]{new Filter("groupId", groupId)});
    }

    public List<Mark> getMarksByStudent(int studentId){
        return db.getMarks(new Filter[]{new Filter("studentId", studentId)});
    }

    public void setMark(int studentId, int taskId, boolean done){
        List<Mark> marks = db.getMarks(new Filter[]{new Filter("studentId", studentId),
                new Filter("taskId", taskId)});

        Mark mark = null;
        if (marks.size() > 0) {
            mark = marks.get(0);
        }

        if(done){
            if(mark != null){
                db.removeMark(mark.getId());
            }
            db.addMark(new Mark(true, studentId, taskId));
        }
        else {
            if(mark == null){
                throw new IllegalArgumentException();
            }
            db.removeMark(mark.getId());
        }
    }
}
