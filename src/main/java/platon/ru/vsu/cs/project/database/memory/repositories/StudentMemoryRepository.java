package platon.ru.vsu.cs.project.database.memory.repositories;

import platon.ru.vsu.cs.adb_lib.memory.MemoryRepo;
import platon.ru.vsu.cs.adb_lib.memory.MemoryRepository;
import platon.ru.vsu.cs.project.database.models.Student;
import platon.ru.vsu.cs.project.database.repostiories.StudentRepository;

@MemoryRepo(clazz = Student.class)
public class StudentMemoryRepository extends MemoryRepository<Student> implements StudentRepository {
    private StudentMemoryRepository() {
        super(Student.class);
    }

    private static StudentMemoryRepository INSTANCE;

    public static StudentMemoryRepository getINSTANCE(){
        if(INSTANCE == null){
            INSTANCE = new StudentMemoryRepository();
        }
        return INSTANCE;
    }
}
