package platon.ru.vsu.cs.project.web;

import platon.ru.vsu.cs.bweb_lib.annotations.PathParam;
import platon.ru.vsu.cs.bweb_lib.annotations.QueryParam;
import platon.ru.vsu.cs.bweb_lib.annotations.WebMethod;
import platon.ru.vsu.cs.bweb_lib.server.HTTPException;
import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;
import platon.ru.vsu.cs.project.database.models.Mark;
import platon.ru.vsu.cs.project.database.repostiories.MarkRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MarkServlet extends CRUDServlet<Mark> {
    private static final String path = "mark";
    protected MarkServlet(MarkRepository repo) {
        super(repo, Mark.class);
    }

    @WebMethod(type = HTTPType.GET, path = path)
    @Override
    public void get(HttpServletRequest request,
                    HttpServletResponse response) throws IOException {

        super.get(request, response);
    }

    @WebMethod(type = HTTPType.GET, path = path)
    @Override
    public void getById(HttpServletRequest request,
                        HttpServletResponse response,
                        @PathParam Integer id) throws IOException, HTTPException {

        super.getById(request, response, id);
    }

    @WebMethod(type = HTTPType.GET, path = path)
    public void getByStudentId(HttpServletRequest request,
                               HttpServletResponse response,
                               @QueryParam(name = "student_id") Integer student_id) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        MarkRepository markRepository = (MarkRepository) repo;

        objectWriter.writeValue(out, markRepository.getMarkByStudent(student_id));
        out.flush();
    }


    @WebMethod(type = HTTPType.PUT, path = "setmark/")
    public void setMark(HttpServletRequest request, HttpServletResponse response,
                        @QueryParam(name = "student_id") Integer student_id,
                        @QueryParam(name = "task_id") Integer task_id,
                        @QueryParam(name = "done") Boolean done) throws HTTPException {

        MarkRepository markRepository = (MarkRepository) repo;
        try {
            markRepository.setMark(student_id, task_id, done);
        } catch (IllegalArgumentException ignored){
            throw new HTTPException(400, "cant set mark");
        }
    }

}
