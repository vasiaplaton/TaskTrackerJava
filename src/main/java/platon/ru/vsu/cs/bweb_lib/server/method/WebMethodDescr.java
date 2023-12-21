package platon.ru.vsu.cs.bweb_lib.server.method;

import platon.ru.vsu.cs.bweb_lib.annotations.PathParam;
import platon.ru.vsu.cs.bweb_lib.annotations.QueryParam;
import platon.ru.vsu.cs.bweb_lib.server.path.ParseMethod;
import platon.ru.vsu.cs.bweb_lib.server.path.PathParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class WebMethodDescr {
    public final Method method;

    public final Object object;

    public final HTTPType httpType;

    protected final ParamDescr[] paramDescrs;

    protected final Set<String> foundedQueryParams = new HashSet<>();

    protected boolean foundPathParam = false;

    public WebMethodDescr(HTTPType httpType, Method method, Object object) {

        Parameter[] parameters = method.getParameters();
        if(parameters[0].getType() != HttpServletRequest.class){
            throw new IllegalArgumentException();
        }
        if(parameters[1].getType() != HttpServletResponse.class){
            throw new IllegalArgumentException();
        }

        paramDescrs = new ParamDescr[parameters.length-2];

        for (int i = 2; i < parameters.length; i++) {
            Parameter p = parameters[i];

            if (p.isAnnotationPresent(PathParam.class)) {
                if(foundPathParam){
                    throw new IllegalArgumentException();
                }

                paramDescrs[i-2] = new ParamDescr(
                        PathParser.getParseMethod(p.getType()),
                        p.getType(),
                        true,
                        null,
                        i-2
                );
                foundPathParam = true;

            }
            else if (p.isAnnotationPresent(QueryParam.class)) {
                QueryParam queryParam = p.getAnnotation(QueryParam.class);
                String name = queryParam.name();
                if(foundedQueryParams.contains(name)){
                    throw new IllegalArgumentException();
                }
                paramDescrs[i-2] = new ParamDescr(
                        PathParser.getParseMethod(p.getType()),
                        p.getType(),
                        false,
                        queryParam.name(),
                        i-2
                );

                foundedQueryParams.add(name);

            }
            else {
                throw new IllegalArgumentException();
            }

        }
        this.httpType = httpType;
        this.method = method;
        this.object = object;
    }

    public static class CantBuildParamsException extends Exception{
    }

    public Object[] buildParams(HttpServletRequest req, HttpServletResponse resp, String path) throws CantBuildParamsException, PathParser.IllegalPath {
        String pathParamGot = PathParser.getPathParam(path);
        Map<String, String> queryParamsGot = PathParser.getQueryParams(path);
        System.out.println(pathParamGot);
        System.out.println(queryParamsGot);

        Object[] res = new Object[paramDescrs.length+2];
        res[0] = req;
        res[1] = resp;

        for (int i = 0; i < paramDescrs.length; i++) {
            ParamDescr param = paramDescrs[i];
            if (param.pathParam) {
                if(pathParamGot == null) {
                    throw new CantBuildParamsException();
                }
                res[i+2] = param.parseMethod.parse(pathParamGot);
                pathParamGot = null;
            }
            else {
                String key = param.name;
                String value = queryParamsGot.get(key);
                if(value == null) {
                    throw new CantBuildParamsException();
                }

                res[i+2] = param.parseMethod.parse(value);
                queryParamsGot.remove(key);
            }
        }

        if(pathParamGot != null || queryParamsGot.keySet().size() != 0){
            throw new CantBuildParamsException();
        }
        return res;
    }

    public boolean equals(WebMethodDescr webMethodDescr){
        return (foundPathParam == webMethodDescr.foundPathParam) &&
                (foundedQueryParams.equals(webMethodDescr.foundedQueryParams)) &&
                (httpType == webMethodDescr.httpType);
    }
}
