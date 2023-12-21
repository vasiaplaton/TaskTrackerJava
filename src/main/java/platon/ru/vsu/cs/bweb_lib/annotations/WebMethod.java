package platon.ru.vsu.cs.bweb_lib.annotations;

import platon.ru.vsu.cs.bweb_lib.server.method.HTTPType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WebMethod {
        HTTPType type();
        String path();

       // QueryParam[] queryParams() default {};
     //   String[] queryParams();
}
