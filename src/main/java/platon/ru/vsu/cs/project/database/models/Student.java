package platon.ru.vsu.cs.project.database.models;

import lombok.*;
import platon.ru.vsu.cs.adb_lib.annotations.Column;
import platon.ru.vsu.cs.adb_lib.annotations.Entity;
import platon.ru.vsu.cs.adb_lib.annotations.ForeignKey;
import platon.ru.vsu.cs.adb_lib.annotations.PrimaryKey;


@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Students")
public class Student {
    @PrimaryKey
    @Column(columnName = "id", nullable = false)
    protected Integer id;

    @NonNull
    @Column(columnName = "firstName", nullable = false)
    protected String firstName;

    @NonNull
    @Column(columnName = "lastName", nullable = false)
    protected String lastName;

    @NonNull
    @Column(columnName = "groupId", nullable = false)
    @ForeignKey(table = Group.class, column = "id")
    protected Integer groupId;
}
