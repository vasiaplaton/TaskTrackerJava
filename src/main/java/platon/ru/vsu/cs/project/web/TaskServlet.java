package platon.ru.vsu.cs.project.web;

import platon.ru.vsu.cs.bweb_lib.annotations.PathParam;
import platon.ru.vsu.cs.bweb_lib.annotations.WebMethod;
import platon.ru.vsu.cs.bweb_lib.server.HTTPException;
import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;
import platon.ru.vsu.cs.project.database.models.Group;
import platon.ru.vsu.cs.project.database.models.Task;
import platon.ru.vsu.cs.project.database.repostiories.GroupRepository;
import platon.ru.vsu.cs.project.database.repostiories.TaskRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TaskServlet extends CRUDServlet<Task> {
    private static final String path = "task";
    protected TaskServlet(TaskRepository repo) {
        super(repo, Task.class);
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
