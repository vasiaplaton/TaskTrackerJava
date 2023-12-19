package platon.ru.vsu.cs.alib.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ForeignKey {
    Class<?> table();
    String column();
}
