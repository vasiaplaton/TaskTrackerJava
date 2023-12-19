package platon.ru.vsu.cs.database.memory;

import platon.ru.vsu.cs.database.models.BaseModel;
import platon.ru.vsu.cs.database.sql.table.Filter;
import platon.ru.vsu.cs.database.Table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TableMemory<T extends BaseModel> implements Table<T> {
    protected final List<T> base;
    int lastId = 0;
    public TableMemory() {
        base = new ArrayList<>();
    }

    @Override
    public List<T> getAll() {
        return base;
    }


    @Override
    public List<T> getAll(Filter[] filters) {
        List<T> all = new ArrayList<>();
        String[] columnNames = null;
        for (T o : base) {
            if(columnNames == null){
                columnNames = o.getColumnNames();
            }
            boolean needed = true;
            for (Filter filter: filters) {
                int index = Arrays.binarySearch(columnNames, filter.columnName);
                if(index < 0){
                    throw new RuntimeException();
                }
                if(!Objects.equals(o.getColumnValues()[index], filter.columnValue)){
                    needed = false;
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
        object.setId(lastId);
        if(getById(object.getId()) != null) {
            throw new IllegalArgumentException();
        }
        base.add(object);
        lastId++;
        return object;
    }

    @Override
    public void remove(int id) {
        T o = getById(id);
        if (o == null){
            throw new IllegalArgumentException();
        }
        base.remove(getById(id));
    }

    @Override
    public void update(int id, T newer) {
        newer.setId(id);
        base.remove(getById(id));
        base.add(newer);
    }
}
