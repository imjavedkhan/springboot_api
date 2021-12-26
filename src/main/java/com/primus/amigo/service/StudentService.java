package com.primus.amigo.service;

import com.primus.amigo.model.Student;
import com.primus.amigo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public List<Student> getStudent(){

        return studentRepo.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepo
                .findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepo.existsById(studentId);

        if(!exists){
            throw new IllegalStateException(
                    "Student with id "+studentId+" does not exist");
        }
        studentRepo.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(
                        "Student does not exist"
                ));
        if (name != null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if (email != null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentByEmail = studentRepo
                    .findStudentByEmail(email);
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
