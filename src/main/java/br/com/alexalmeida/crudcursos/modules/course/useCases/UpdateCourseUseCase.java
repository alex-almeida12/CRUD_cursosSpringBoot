package br.com.alexalmeida.crudcursos.modules.course.useCases;


import br.com.alexalmeida.crudcursos.modules.course.CourseEntity;
import br.com.alexalmeida.crudcursos.modules.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(UUID id, CourseEntity updatedCourseEntity) {

        this.courseRepository.findByNameAndCategory(updatedCourseEntity.getName(), updatedCourseEntity.getCategory())
                .forEach(existingCourse -> {
                    if (!existingCourse.getId().equals(id)) {
                        throw new IllegalArgumentException("Já existe um curso com esse nome e categoria");
                    }
                });

        var course = courseRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Curso não existe")
        );

        if(updatedCourseEntity.getName() != null) {
            course.setName(updatedCourseEntity.getName());
        }

        if(updatedCourseEntity.getCategory() != null) {
            course.setCategory(updatedCourseEntity.getCategory());
        }

        return this.courseRepository.save(course);
    }
}

