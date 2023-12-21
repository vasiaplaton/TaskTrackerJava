package platon.ru.vsu.cs.adb_lib.memory;

import org.reflections.Reflections;
import platon.ru.vsu.cs.adb_lib.annotations.ForeignKey;
import platon.ru.vsu.cs.adb_lib.repository.Repository;
import platon.ru.vsu.cs.adb_lib.annotations.ColumnField;
import platon.ru.vsu.cs.adb_lib.annotations.EntityWork;
import platon.ru.vsu.cs.adb_lib.sql.Filter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class MemoryRepository<T>  implements Repository<T> {
    protected final List<T> base;
    protected final Class<T> clazz;
    protected List<CascadeDelete> toCascadeDelete = new ArrayList<>();
    int lastId = 0;

    protected Field[] columns;

    protected final EntityWork<T> entityWork;

    protected class CascadeDelete{
        public final Class<?> repo;
        public final Field fk;
        public final Class<?> ent;

        protected CascadeDelete(Class<?> repo, Field fk, Class<?> ent) {
            this.repo = repo;
            this.fk = fk;
            this.ent = ent;
        }
    }



    public MemoryRepository(Class<T> clazz){
        base = new ArrayList<>();
        this.clazz = clazz;
        entityWork = new EntityWork<>(clazz);


        System.out.println(getClass());
        System.out.println("-");
        for(Class<?> anotherRepo: new Reflections(getClass().getPackage().getName()).getTypesAnnotatedWith(MemoryRepo.class)){
            if(anotherRepo == getClass()) continue;
            Class<?> clazz1 = anotherRepo.getAnnotation(MemoryRepo.class).clazz();

            for(Field f: clazz1.getDeclaredFields()){
                if(!f.isAnnotationPresent(ForeignKey.class)) continue;

                ForeignKey fk = f.getAnnotation(ForeignKey.class);
                if(fk.table() == clazz){
                    toCascadeDelete.add(new CascadeDelete(anotherRepo, f, clazz1));
                    System.out.println(anotherRepo.getName());
                    break;
                }
            }
        }

        System.out.println("---");
        System.out.println("---");
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
                    columnField.field.setAccessible(true);
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
        entityWork.getPrimaryKey().field.setAccessible(true);
        for (T o : base) {
            try {
                int id_test = (int) entityWork.getPrimaryKey().field.get(o);
                if(id_test == id){
                    return o;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
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

    @Override
    public void delete(int id) {
        for(CascadeDelete cascadeDelete: toCascadeDelete){
            try {
                Class<?> ent = cascadeDelete.ent;
                EntityWork<?> entityWork1 = new EntityWork<>(ent);
                MemoryRepository<?> repo1 = (MemoryRepository<?>) cascadeDelete.repo.getDeclaredMethod("getINSTANCE").invoke(null);
                List<?> toDelete = repo1.getAll(new Filter[]{new Filter(cascadeDelete.fk.getName(), id)});
                for (Object o : toDelete) {
                    entityWork1.getPrimaryKey().field.setAccessible(true);
                    Integer id_to_del = (Integer) entityWork1.getPrimaryKey().field.get(o);
                    repo1.delete(id_to_del);
                }

            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

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
