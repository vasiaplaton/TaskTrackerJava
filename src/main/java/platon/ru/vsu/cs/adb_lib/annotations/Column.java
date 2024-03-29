package platon.ru.vsu.cs.adb_lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {
    String columnName();
    boolean nullable();

    String methodCustomParse() default "";

    String methodCustomToString() default "";
}
