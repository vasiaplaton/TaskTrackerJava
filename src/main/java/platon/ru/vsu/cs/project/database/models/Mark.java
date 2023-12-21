package platon.ru.vsu.cs.project.database.models;

import lombok.*;
import platon.ru.vsu.cs.adb_lib.annotations.Column;
import platon.ru.vsu.cs.adb_lib.annotations.Entity;
import platon.ru.vsu.cs.adb_lib.annotations.ForeignKey;
import platon.ru.vsu.cs.adb_lib.annotations.PrimaryKey;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Marks")
public class Mark {
    @PrimaryKey
    @Column(columnName = "id", nullable = false)
    protected Integer id;

    @NonNull
    @Column(columnName = "isDone", nullable = false, methodCustomParse = "parse", methodCustomToString = "cast")
    protected Boolean isDone;

    @NonNull
    @Column(columnName = "studentId", nullable = false)
    @ForeignKey(table = Student.class, column = "id")
    protected Integer studentId;

    @NonNull
    @Column(columnName = "taskId", nullable = false)
    @ForeignKey(table = Task.class, column = "id")
    protected Integer taskId;


    public static Boolean parse(Object i){
        return (Integer) i == 1;
    }

    public static String cast(Object i){
        Boolean b = (Boolean) i;
        if(b){
            return "1";
        }
        else {
            return "0";
        }
    }

}
