package platon.ru.vsu.cs.bweb_lib.annotations;

import platon.ru.vsu.cs.bweb_lib.server.path.ParseMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface PathParam {
}