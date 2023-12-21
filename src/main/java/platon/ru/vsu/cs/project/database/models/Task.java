package platon.ru.vsu.cs.project.database.models;

import lombok.*;
import platon.ru.vsu.cs.adb_lib.annotations.Column;
import platon.ru.vsu.cs.adb_lib.annotations.Entity;
import platon.ru.vsu.cs.adb_lib.annotations.PrimaryKey;


@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Tasks")
public class Task {
    @PrimaryKey
    @Column(columnName = "id", nullable = false)
    protected Integer id;

    @NonNull
    @Column(columnName = "name", nullable = false)
    protected String name;

    @NonNull
    @Column(columnName = "description", nullable = false)
    protected String description;
}
