package platon.ru.vsu.cs.database.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import platon.ru.vsu.cs.database.annotations.Entity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Group extends BaseModel {
    protected int num;

    @Override
    public String[] getColumnNames() {
        return new String[]{"num"};
    }

    @Override
    public String[] getColumnValues() {
        return new String[]{Integer.toString(num)};
    }

    @Override
    public void setColumnValues(String[] columnValues) {
        num = Integer.parseInt(columnValues[0]);
    }
}
