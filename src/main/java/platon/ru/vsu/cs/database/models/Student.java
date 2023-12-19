package platon.ru.vsu.cs.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseModel {
    protected Integer groupId;
    protected String firstName;
    protected String lastName;

    @Override
    public String[] getColumnNames() {
        return new String[] {"groupId", "firstName", "lastName"};
    }

    @Override
    public String[] getColumnValues() {
        return new String[] {String.valueOf(groupId), firstName, lastName};
    }

    @Override
    public void setColumnValues(String[] columnValues) {
        groupId = Integer.parseInt(columnValues[0]);
        firstName = columnValues[1];
        lastName = columnValues[2];
        // TODO: update method
    }
}
