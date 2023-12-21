package platon.ru.vsu.cs.project.web;

import platon.ru.vsu.cs.adb_lib.repository.Repository;
import platon.ru.vsu.cs.bweb_lib.annotations.PathParam;
import platon.ru.vsu.cs.bweb_lib.annotations.QueryParam;
import platon.ru.vsu.cs.bweb_lib.annotations.WebMethod;
import platon.ru.vsu.cs.bweb_lib.server.HTTPException;
import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentServlet extends CRUDServlet<Student> {
    private static final String path = "student";
    protected StudentServlet(StudentRepository repo) {
        super(repo, Student.class);
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
                           @QueryParam(name="group_id") Integer groupId) throws IOException, HTTPException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        StudentRepository studentRepository = (StudentRepository) repo;

        objectWriter.writeValue(out, studentRepository.getStudentsByGroup(groupId));
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
