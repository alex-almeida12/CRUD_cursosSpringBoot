package br.com.alexalmeida.crudcursos.modules.course.useCases;

import br.com.alexalmeida.crudcursos.modules.course.CourseEntity;
import br.com.alexalmeida.crudcursos.modules.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity){
        this.courseRepository.findByNameAndCategory(courseEntity.getName(), courseEntity.getCategory())
                .forEach(existingCourse -> {
                    if (!existingCourse.getId().equals(courseEntity.getId())) {
                        throw new IllegalArgumentException("Já existe um curso com esse nome e categoria");
                    }
                });

        return this.courseRepository.save(courseEntity);
    }

}
