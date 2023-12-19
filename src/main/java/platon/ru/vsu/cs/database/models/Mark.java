package platon.ru.vsu.cs.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mark extends BaseModel {
    protected boolean isDone;
    protected int studentId;
    protected int taskId;

    @Override
    public String[] getColumnNames() {
        return new String[] {"isDone", "studentId", "taskId"};
    }

    @Override
    public String[] getColumnValues() {
        return new String[] {isDone ? "1": "0", String.valueOf(studentId), String.valueOf(taskId)};
    }

    @Override
    public void setColumnValues(String[] columnValues) {
        isDone = !Objects.equals(columnValues[0], "0");
        studentId = Integer.parseInt(columnValues[1]);
        taskId = Integer.parseInt(columnValues[2]);
    }
}
