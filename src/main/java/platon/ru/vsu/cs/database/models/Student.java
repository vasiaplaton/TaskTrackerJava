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
@Entity(tableName = "Students")
public class Student {
    @PrimaryKey
    protected Integer id;

    @Column(columnName = "firstName", nullable = false)
    protected String firstName;

    @Column(columnName = "lastName", nullable = false)
    protected String lastName;

    @Column(columnName = "groupId", nullable = false)
    @ForeignKey(table = Student.class, column = "id")
    protected Integer groupId;
}
