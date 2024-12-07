package br.com.alexalmeida.crudcursos.modules.course.useCases;

import br.com.alexalmeida.crudcursos.modules.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public void execute(UUID id){

        var course = courseRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("Curso não encontrado."));
        courseRepository.delete(course);
    }
}
