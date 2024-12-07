package br.com.alexalmeida.crudcursos.modules.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

    List<CourseEntity> findByName(String name);
    List<CourseEntity> findByNameAndCategory(String name, String category);
    List<CourseEntity> findByCategory(String category);

}
