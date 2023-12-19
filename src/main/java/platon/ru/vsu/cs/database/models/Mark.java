package platon.ru.vsu.cs.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import platon.ru.vsu.cs.alib.annotations.Column;
import platon.ru.vsu.cs.alib.annotations.Entity;
import platon.ru.vsu.cs.alib.annotations.ForeignKey;
import platon.ru.vsu.cs.alib.annotations.PrimaryKey;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Marks")
public class Mark {
    @PrimaryKey
    protected Integer id;

    @Column(columnName = "isDone", nullable = false)
    protected boolean isDone;

    @Column(columnName = "studentId", nullable = false)
    @ForeignKey(table = Student.class, column = "id")
    protected int studentId;

    @Column(columnName = "taskId", nullable = false)
    @ForeignKey(table = Student.class, column = "id")
    protected int taskId;

}
