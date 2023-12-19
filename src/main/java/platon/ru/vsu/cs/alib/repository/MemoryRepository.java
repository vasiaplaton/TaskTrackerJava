package platon.ru.vsu.cs.alib.repository;

import platon.ru.vsu.cs.database.Repository;
import platon.ru.vsu.cs.database.sql.table.Filter;
import platon.ru.vsu.cs.alib.annotations.ColumnField;
import platon.ru.vsu.cs.alib.annotations.EntityWork;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MemoryRepository<T>  implements Repository<T> {
    protected final List<T> base;
    protected final Class<T> clazz;
    int lastId = 0;

    protected Field[] columns;

    protected final EntityWork<T> entityWork;



    public MemoryRepository(Class<T> clazz){
        base = new ArrayList<>();
        this.clazz = clazz;
        entityWork = new EntityWork<>(clazz);
        List<Class<?>> toCascadeDelete = new ArrayList<>();

        /*for(Class<?> clazz1: new Reflections(clazz.getPackage().toString()).getTypesAnnotatedWith(Entity.class)){
            for(Field f = clazz1.getDeclaredFields()){

            }
        }*/
    }

    @Override
    public List<T> getAll() {
        return base;
    }


    @Override
    public List<T> getAll(Filter[] filters) {
        List<T> all = new ArrayList<>();
        for (T o : base) {
            boolean needed = true;
            for (Filter filter: filters) {

                boolean checked = false;
                for (ColumnField columnField : entityWork.getFields()) {
                    if(!Objects.equals(columnField.columnName, filter.columnName)) continue;
                    try {
                        checked = true;
                        if(!Objects.equals(columnField.field.get(o).toString(), filter.columnValue)) {
                            needed = false;
                            break;
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(!checked) {
                    throw new RuntimeException();
                }

                if(!needed) {
                    break;
                }
            }
            if(needed){
                all.add(o);
            }
        }
        return all;
    }

    @Override
    public T getById(int id) {
        List<T> suits = getAll(new Filter[]{new Filter("id", String.valueOf(id))});
        if(suits.size() != 1){
            return null;
        }
        return suits.get(0);
    }

    @Override
    public T add(T object) {
        try {
            entityWork.getPrimaryKey().field.setAccessible(true);

            Integer id = (Integer) entityWork.getPrimaryKey().field.get(object);
            if(id == null) {
                entityWork.getPrimaryKey().field.set(object, lastId);
                id = lastId;
            }

            if(getById(id) != null) {
                throw new IllegalArgumentException();
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        base.add(object);
        lastId++;
        return object;
    }

    protected void cascadeDelete(){

    }

    @Override
    public void delete(int id) {
        T o = getById(id);
        if (o == null){
            throw new IllegalArgumentException();
        }
        base.remove(getById(id));
    }

    @Override
    public void update(int id, T newer) {
        entityWork.getPrimaryKey().field.setAccessible(true);
        try {
            entityWork.getPrimaryKey().field.set(newer, lastId);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        base.remove(getById(id));
        base.add(newer);
    }
}
