package br.com.alexalmeida.crudcursos.modules.course.useCases;

import br.com.alexalmeida.crudcursos.modules.course.CourseEntity;
import br.com.alexalmeida.crudcursos.modules.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCoursesUseCase {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseEntity> execute(String name, String category) {

        if (name != null && category != null) {
            return courseRepository.findByNameAndCategory(name, category);
        }

        else if (name != null) {
            return courseRepository.findByName(name);
        }

        else if (category != null) {
            return courseRepository.findByCategory(category);
        }

        else {
            return courseRepository.findAll();
        }
    }
}
