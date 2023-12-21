package platon.ru.vsu.cs.bweb_lib.server;

import platon.ru.vsu.cs.adb_lib.logger.Log;
import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;
import platon.ru.vsu.cs.bweb_lib.server.method.WebMethodDescr;
import platon.ru.vsu.cs.bweb_lib.server.path.PathParser;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MyServlet extends HttpServlet {
    protected final List<WebMethodDescr> methods;
    
    protected final List<WebMethodDescr> getMethodsByType(HTTPType type){
        return methods.stream().filter(e -> e.httpType == type).toList();
    }
     
    public MyServlet(List<WebMethodDescr> methods){
        this.methods = new ArrayList<>(methods);
    }

    protected void invokeMethod(HTTPType type, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {

            for (WebMethodDescr method: getMethodsByType(type)) {

                Object[] params;
                try {
                    params = method.buildParams(req, resp, req.getPathInfo(), req.getParameterMap());
                } catch (WebMethodDescr.CantBuildParamsException e){
                    continue;
                } catch (PathParser.IllegalPath e){
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.msg);
                    return;
                }

                method.method.invoke(method.object, params);
                if(!resp.isCommitted()) {
                    resp.sendError(HttpServletResponse.SC_OK);
                }
                return;

            }
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);

        } catch (IllegalAccessException e ) {
            Log.getI().log(e.toString(), 0);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e){
            Log.getI().log(e.toString(), 0);
            if(e.getCause() instanceof HTTPException httpException){
                resp.sendError(httpException.sc, httpException.msg);
            } else {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        invokeMethod(HTTPType.GET, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        invokeMethod(HTTPType.POST, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        invokeMethod(HTTPType.PUT, req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        invokeMethod(HTTPType.DELETE, req, resp);
    }
}
