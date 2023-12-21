package platon.ru.vsu.cs.project.web;

import lombok.Getter;
import platon.ru.vsu.cs.bweb_lib.annotations.PathParam;
import platon.ru.vsu.cs.bweb_lib.annotations.QueryParam;
import platon.ru.vsu.cs.bweb_lib.annotations.WebMethod;
import platon.ru.vsu.cs.bweb_lib.server.HTTPException;
import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;
import platon.ru.vsu.cs.project.database.RepositoryLib;
import platon.ru.vsu.cs.project.database.models.Mark;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.project.database.models.Task;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StudentServlet extends CRUDServlet<Student> {
    private static final String path = "student";
    private final RepositoryLib repositoryLib;

    protected StudentServlet(StudentRepository repo, RepositoryLib repositoryLib) {
        super(repo, Student.class);
        this.repositoryLib = repositoryLib;
    }

    @WebMethod(type = HTTPType.GET, path = path)
    @Override
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.get(request, response);
    }

    @WebMethod(type = HTTPType.GET, path = path)
    @Override
    public void getById(HttpServletRequest request, HttpServletResponse response, @PathParam Integer id) throws IOException, HTTPException {
        super.getById(request, response, id);
    }

    @WebMethod(type = HTTPType.GET, path = path)
    public void getByGroup(HttpServletRequest request, HttpServletResponse response,
                           @QueryParam(name="group_id") Integer groupId) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        StudentRepository studentRepository = (StudentRepository) repo;

        objectWriter.writeValue(out, studentRepository.getStudentsByGroup(groupId));
        out.flush();
    }

    @WebMethod(type = HTTPType.GET, path = "student_done")
    public void getWithMarks(HttpServletRequest request, HttpServletResponse response,
                             @PathParam Integer id) throws IOException, HTTPException {


        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        record StudentTask(Student student, List<Task> tasks){
        }

        Student s = repo.getById(id);
        if(s == null){
            throw new HTTPException(404, "");
        }

        StudentTask studentTask = new StudentTask(s, new ArrayList<>());


        for (Mark m : repositoryLib.getMarkRepository().getMarkByStudent(s.getId())) {
            if(m.getIsDone()){
                studentTask.tasks.add(repositoryLib.getTaskRepository().getById(m.getTaskId()));
            }
        }

        objectWriter.writeValue(out, studentTask);
        out.flush();
    }

    @WebMethod(type = HTTPType.PUT, path = path)
    @Override
    public void put(HttpServletRequest request, HttpServletResponse response, @PathParam Integer id) throws IOException, HTTPException {
        super.put(request, response, id);
    }

    @WebMethod(type = HTTPType.POST, path = path)
    @Override
    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, HTTPException {
        super.post(request, response);
    }

    @WebMethod(type = HTTPType.DELETE, path = path)
    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathParam Integer id) throws IOException, HTTPException {
        super.delete(request, response, id);
    }
}
