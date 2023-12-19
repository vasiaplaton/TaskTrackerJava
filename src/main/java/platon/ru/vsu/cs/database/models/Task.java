package platon.ru.vsu.cs.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import platon.ru.vsu.cs.alib.annotations.Column;
import platon.ru.vsu.cs.alib.annotations.Entity;
import platon.ru.vsu.cs.alib.annotations.PrimaryKey;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Tasks")
public class Task {
    @PrimaryKey
    protected Integer id;

    @Column(columnName = "name", nullable = false)
    protected String name;

    @Column(columnName = "description", nullable = false)
    protected String description;
}
