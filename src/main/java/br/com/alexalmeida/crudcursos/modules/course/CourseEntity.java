package br.com.alexalmeida.crudcursos.modules.course;

import br.com.alexalmeida.crudcursos.modules.course.enums.CourseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome do curso não pode estar em branco.")
    private String name;

    @NotBlank(message = "Categoria do curso não pode estar em branco.")
    private String category;

    @Enumerated(EnumType.STRING)
    private CourseStatus active = CourseStatus.ATIVO;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
