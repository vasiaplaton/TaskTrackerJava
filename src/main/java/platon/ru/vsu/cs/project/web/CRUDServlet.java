package platon.ru.vsu.cs.project.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import platon.ru.vsu.cs.adb_lib.repository.Repository;
import platon.ru.vsu.cs.bweb_lib.annotations.WebClass;
import platon.ru.vsu.cs.bweb_lib.annotations.WebMethod;
import platon.ru.vsu.cs.bweb_lib.server.HTTPException;
import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;
import platon.ru.vsu.cs.project.database.models.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class CRUDServlet<T> implements WebClass {
    protected final Repository<T> repo;

    protected final Class<T> clazz;
    protected final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected CRUDServlet(Repository<T> repo, Class<T> clazz) {
        this.repo = repo;
        this.clazz = clazz;
    }

    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        objectWriter.writeValue(out, repo.getAll());
        out.flush();
    }

    public void getById(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException, HTTPException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        T s = repo.getById(id);
        if(s == null){
            throw new HTTPException(404, "");
        }
        objectWriter.writeValue(out, repo.getById(id));
        out.flush();
    }

    public void put(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException, HTTPException {
        T got = objectMapper.readValue(request.getReader(), clazz);

       // if() {
       //     throw new HTTPException(400, "error while parsing body");
       // }
        try {
            System.out.println(got);
            repo.update(id, got);
        }
        catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error appending in db" + e.toString());
            return;
        }
        response.sendError(HttpServletResponse.SC_OK);
    }

    public void post(HttpServletRequest request, HttpServletResponse response) throws IOException, HTTPException {
        T got = objectMapper.readValue(request.getReader(), clazz);

        try {
            System.out.println(got);
            repo.add(got);

            response.setContentType("application/json");

            PrintWriter out = response.getWriter();
            objectWriter.writeValue(out, got);
            out.flush();
        }
        catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error appending in db" + e.toString());
            return;
        }
        response.sendError(HttpServletResponse.SC_OK);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException, HTTPException {
        try {
            repo.delete(id);
        }
        catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "error removing from db" + e.toString());
        }
        response.sendError(HttpServletResponse.SC_OK);
    }
}
