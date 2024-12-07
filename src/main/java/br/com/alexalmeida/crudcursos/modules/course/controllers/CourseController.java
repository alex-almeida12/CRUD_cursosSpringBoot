package br.com.alexalmeida.crudcursos.modules.course.controllers;

import br.com.alexalmeida.crudcursos.modules.course.CourseEntity;
import br.com.alexalmeida.crudcursos.modules.course.useCases.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private ListCoursesUseCase listCoursesUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @Autowired
    private UpdateCourseUseCase updateCourseUseCase;

    @Autowired
    private ToggleActiveStatusCourseUseCase toggleActiveStatusCourseUseCase;


    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity){
        try{
            var result = createCourseUseCase.execute(courseEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> list(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String category){

        try {
            var courses = listCoursesUseCase.execute(name, category);
            if (courses.isEmpty()) {
                return ResponseEntity.status(404).body("Nenhum curso encontrado.");
            }
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar cursos: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try {
            deleteCourseUseCase.execute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro interno no servidor.");
    }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id,@RequestBody @Valid CourseEntity courseEntity) {
        if (courseEntity.getName() == null && courseEntity.getCategory() == null) {
            return ResponseEntity.badRequest().body("Pelo menos um campo (nome ou categoria) deve ser enviado.");
        }

        try {
            var updatedCourse = updateCourseUseCase.execute(id, courseEntity);
            return ResponseEntity.ok(updatedCourse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Para fins de depuração
            return ResponseEntity.status(500).body("Erro interno no servidor.");
        }
    }

    @PatchMapping("/{id}/active")

    public ResponseEntity<Object> toggleActiveStatus(@PathVariable UUID id){
        try {
            var toggleUpdateStatus = toggleActiveStatusCourseUseCase.execute(id);
            return ResponseEntity.ok(toggleUpdateStatus);
        } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro interno no servidor.");
    }
    }
}
