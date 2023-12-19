package platon.ru.vsu.cs.database.memory.repositories;

import platon.ru.vsu.cs.database.memory.repository.MemoryRepository;
import platon.ru.vsu.cs.database.models.Mark;
import platon.ru.vsu.cs.database.models.Student;
import platon.ru.vsu.cs.database.repostiories.StudentRepository;

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
