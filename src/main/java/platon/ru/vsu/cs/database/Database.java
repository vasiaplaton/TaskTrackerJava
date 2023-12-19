package platon.ru.vsu.cs.database;

import platon.ru.vsu.cs.database.models.Group;
import platon.ru.vsu.cs.database.models.Mark;
import platon.ru.vsu.cs.database.models.Student;
import platon.ru.vsu.cs.database.models.Task;
import platon.ru.vsu.cs.database.sql.table.Filter;

import java.util.List;

public abstract class Database {
    protected Table<Group> groupTable;
    protected Table<Mark> markTable;
    protected Table<Student> studentTable;

    protected Table<Task> taskTable;

    public Group addGroup(Group g){
        groupTable.add(g);
        return g;
    }

    public List<Group> getGroups(){
        return groupTable.getAll();
    }

    public List<Group> getGroups(Filter[] filters){
        return groupTable.getAll(filters);
    }

    public Group getGroup(int id) {
        return groupTable.getById(id);
    }

    public void removeGroup(int id) {
        groupTable.remove(id);
    }

    public void updateGroup(int id, Group group) {
        groupTable.update(id, group);
    }

    public Student addStudent(Student s){;
        studentTable.add(s);
        return s;
    }

    public List<Student> getStudents() {
        return studentTable.getAll();
    }

    public List<Student> getStudents(Filter[] filters) {
        return studentTable.getAll(filters);
    }

    public Student getStudent(int id) {
        return studentTable.getById(id);
    }

    public void removeStudent(int id) {
        studentTable.remove(id);
    }

    public void updateStudent(int id, Student s) {
        studentTable.update(id, s);
    }

    //
    public Task addTask(Task t){
        taskTable.add(t);
        return t;
    }
    public List<Task> getTasks(Filter[] filters) {
        return taskTable.getAll(filters);
    }

    public List<Task> getTasks() {
        return taskTable.getAll();
    }

    public Task getTasks(int id) {
        return taskTable.getById(id);
    }

    public void removeTask(int id) {
        taskTable.remove(id);
    }

    public void updateTask(int id, Task t) {
        taskTable.update(id, t);
    }

    public Mark addMark(Mark mark){
        markTable.add(mark);
        return mark;
    }

    public List<Mark> getMarks() {
        return markTable.getAll();
    }

    public List<Mark> getMarks(Filter[] filters) {
        return markTable.getAll(filters);
    }

    public Mark getMarks(int id) {
        return markTable.getById(id);
    }

    public void removeMark(int id) {
        markTable.remove(id);
    }

    public void updateMark(int id, Mark mark) {
        markTable.update(id, mark);
    }
}
