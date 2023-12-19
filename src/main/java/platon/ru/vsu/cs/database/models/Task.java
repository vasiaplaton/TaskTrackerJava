package platon.ru.vsu.cs.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task  extends BaseModel {
    protected String name;
    protected String description;

    @Override
    public String[] getColumnNames() {
        return new String[]{"name", "description"};
    }

    @Override
    public String[] getColumnValues() {
        return new String[]{name, description};
    }

    @Override
    public void setColumnValues(String[] columnValues) {
        name = columnValues[0];
        description = columnValues[1];
    }
}
