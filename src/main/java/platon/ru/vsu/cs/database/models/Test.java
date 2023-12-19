package platon.ru.vsu.cs.database.models;

import org.reflections.Reflections;
import platon.ru.vsu.cs.alib.annotations.Column;
import platon.ru.vsu.cs.alib.annotations.Entity;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) {
        Class<Group> clazz = Group.class;
        Entity entityAnnotation = clazz.getAnnotation(Entity.class);
        if (entityAnnotation != null) {
            System.out.println(entityAnnotation.tableName());
        } else {

        }

        for(Field field  : clazz.getDeclaredFields())
        {
            if (field.isAnnotationPresent(Column.class))
            {
                Column column = field.getAnnotation(Column.class);
                System.out.println(field.getName());
                System.out.println(column.columnName());
                //do action
            }
        }

        for(Class<?> clazz1: new Reflections("platon.ru.vsu.cs.database.models").getTypesAnnotatedWith(Entity.class)){
            System.out.println(clazz1.getName());
        }


    }
}
