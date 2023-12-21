package platon.ru.vsu.cs.project.database.models;
import lombok.*;
import platon.ru.vsu.cs.adb_lib.annotations.Column;
import platon.ru.vsu.cs.adb_lib.annotations.Entity;
import platon.ru.vsu.cs.adb_lib.annotations.PrimaryKey;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Groups")
public class Group {
    @PrimaryKey
    @Column(columnName = "id", nullable = false)
    protected Integer id;

    @NonNull
    @Column(columnName = "num", nullable = false)
    protected Integer num;

}
