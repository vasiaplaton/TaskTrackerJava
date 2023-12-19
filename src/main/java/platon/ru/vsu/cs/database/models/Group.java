package platon.ru.vsu.cs.database.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import platon.ru.vsu.cs.database.annotations.*;
import platon.ru.vsu.cs.alib.annotations.Column;
import platon.ru.vsu.cs.alib.annotations.Entity;
import platon.ru.vsu.cs.alib.annotations.PrimaryKey;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Groups")
public class Group {
    @PrimaryKey
    protected Integer id;

    @Column(columnName = "num", nullable = false)
    protected Integer num;

}
