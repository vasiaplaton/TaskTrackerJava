package platon.ru.vsu.cs.bweb_lib.server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.Context;
import platon.ru.vsu.cs.bweb_lib.annotations.WebMethod;
import platon.ru.vsu.cs.bweb_lib.annotations.WebClass;
import platon.ru.vsu.cs.bweb_lib.server.method.WebMethodDescr;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    protected final Map<String, List<WebMethodDescr>> methodMap = new HashMap<>();

    public Server(List<WebClass> classes) {
        for(Object o: classes){
            Class<?> clazz = o.getClass();
            for(Method method: clazz.getMethods()){
                if(!method.isAnnotationPresent(WebMethod.class)) continue;
                WebMethod webMethod = method.getAnnotation(WebMethod.class);

                String path = webMethod.path();
                if(path.charAt(0) == '/') {
                    path = path.substring(1);
                }
                if(path.charAt(path.length()-1) == '/') {
                    path = path.substring(0, path.length()-1);
                }
                // TODO add check on method amount
                methodMap.putIfAbsent(path, new ArrayList<>());

                methodMap.get(path).add(new WebMethodDescr(webMethod.type(), method, o));
            }
        }

        for (List<WebMethodDescr> list : methodMap.values()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i+1; j < list.size(); j++) {
                    if(list.get(i).equals(list.get(j))) {
                        throw new IllegalArgumentException("two same methods with one path");
                    }
                }
            }
        }


    }


    public void runServer(){
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        Context context = tomcat.addContext("", null);

        for (String path : methodMap.keySet()) {
            Tomcat.addServlet(context, path, new MyServlet(methodMap.get(path)));
            context.addServletMappingDecoded("/" + path + "/*", path);

        }

        tomcat.getConnector();
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
        tomcat.getServer().await();
    }
}
