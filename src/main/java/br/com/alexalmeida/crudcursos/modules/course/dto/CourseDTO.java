package br.com.alexalmeida.crudcursos.modules.course.dto;

import br.com.alexalmeida.crudcursos.modules.course.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotBlank(message = "O nome do curso é obrigatório")
    private String name;

    @NotBlank(message = "A categoria do curso é obrigatória")
    private String category;

    private CourseStatus active;

}
