package platon.ru.vsu.cs.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
//import platon.ru.vsu.cs.database.Database;
import platon.ru.vsu.cs.database.models.Student;
//import platon.ru.vsu.cs.database.sql.DatabaseSQL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "students", urlPatterns = {"/students/*"} )
public class StudentServlet extends HttpServlet {
    private final ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
   // private final Database db = DatabaseSQL.getInstance();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметр "id" из URL
        String[] params = request.getPathInfo().split("/");
        Integer id = null;

        if(params.length != 0 && params.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if(params.length == 2) {
            try {
                id = Integer.parseInt(params[1]);
            } catch (NumberFormatException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }

        response.setContentType("application/json");

        Object value;
        if( id != null) {
          //  value = db.getStudent(id);
        }
        else {
          //  value = db.getStudents();
        }

       // if(value == null){
       //     response.sendError(HttpServletResponse.SC_NOT_FOUND);
       //     return;
      //  }
        PrintWriter out = response.getWriter();
       // objectWriter.writeValue(out, value);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         ObjectMapper objectMapper = new ObjectMapper();
        // Получаем параметр "id" из URL
        String[] params = request.getPathInfo().split("/");
        Integer id = null;

        if(params.length != 0 && params.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if(params.length == 2) {
            try {
                id = Integer.parseInt(params[1]);
            } catch (NumberFormatException e){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }

        response.setContentType("application/json");

        request.getReader();
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
        Student s = objectMapper.readValue(request.getReader(), Student.class);
        if(!validate(s)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
          //  Student s1 = db.addStudent(s);
          //  PrintWriter out = response.getWriter();
         //   objectWriter.writeValue(out, s1);
         //   out.flush();
        }
        catch (RuntimeException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected boolean validate(Student s){
        return s.getGroupId() != null && s.getFirstName() != null && s.getLastName() != null;
    }

}