package br.com.alexalmeida.crudcursos.modules.course.useCases;

import br.com.alexalmeida.crudcursos.modules.course.CourseEntity;
import br.com.alexalmeida.crudcursos.modules.course.CourseRepository;
import br.com.alexalmeida.crudcursos.modules.course.enums.CourseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static br.com.alexalmeida.crudcursos.modules.course.enums.CourseStatus.INATIVO;

@Service
public class ToggleActiveStatusCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id){

        CourseEntity course = courseRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Curso não encontrado")
        );

        course.setActive(course.getActive() == CourseStatus.ATIVO ? CourseStatus.INATIVO : CourseStatus.ATIVO);

        return courseRepository.save(course);
    }
}
