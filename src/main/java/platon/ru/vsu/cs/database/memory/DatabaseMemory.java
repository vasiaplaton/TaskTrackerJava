package platon.ru.vsu.cs.database.memory;

import platon.ru.vsu.cs.database.Database;
import platon.ru.vsu.cs.database.models.*;

import java.util.ArrayList;
import java.util.List;


public class DatabaseMemory extends Database {
    // TODO refactor
    private DatabaseMemory() {
        groupTable = new TableMemory<>();
        studentTable = new TableMemory<>();
        taskTable = new TableMemory<>();
        markTable = new TableMemory<>();
    }

    private static DatabaseMemory INSTANCE;

    public static DatabaseMemory getInstance() {
        if(INSTANCE == null){
            INSTANCE = new DatabaseMemory();
        }
        return INSTANCE;
    }

    // TODO add checks

    protected void checkStudent(Student student){
        if(getGroup(student.getGroupId()) == null) {
            throw new IllegalArgumentException("illegal group id");
        }
    }

    protected void checkMark(Mark mark){
        if(getStudent(mark.getStudentId()) == null) {
            throw new IllegalArgumentException("illegal student id");
        }
        if(getTasks(mark.getTaskId()) == null) {
            throw new IllegalArgumentException("illegal task id");
        }
    }

    @Override
    public Student addStudent(Student s) {
        checkStudent(s);
        return super.addStudent(s);
    }

    @Override
    public void updateStudent(int id, Student s) {
        checkStudent(s);
        super.updateStudent(id, s);
    }

    @Override
    public Mark addMark(Mark mark) {
        checkMark(mark);
        return super.addMark(mark);
    }

    @Override
    public void updateMark(int id, Mark mark) {
        checkMark(mark);
        super.updateMark(id, mark);
    }


    @Override
    public void removeGroup(int id) {
        List<Student> students = new ArrayList<>(studentTable.getAll());
        for (Student s : students) {
            if(s.getGroupId() == id) {
                removeStudent(s.getId());
            }
        }
        super.removeGroup(id);
    }

    @Override
    public void removeStudent(int id) {
        List<Mark> marks = new ArrayList<>(markTable.getAll());
        for (Mark m : marks) {
            if(m.getStudentId() == id) {
                removeMark(m.getId());
            }
        }
        super.removeStudent(id);
    }

    @Override
    public void removeTask(int id) {
        List<Mark> marks = new ArrayList<>(markTable.getAll());
        for (Mark m : marks) {
            if(m.getTaskId() == id) {
                removeMark(m.getId());
            }
        }
        super.removeTask(id);
    }
}
