package fr.eni.demospringweb.bo;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    Long id;
    /**
     * Bean Validation (standard implementé par Spring Boot Validation)
     * Ajout des contraintes suivantes :
     * - Le nom de ma tâche doit être nom vide (@NotEmpty) et faire entre 5 et 30 caractères @Size(min=5, max=30)
     * - La date de finition de ma tâche doit être dans le futur (@Future)
     */
    @NotEmpty
    @Size(min=5, max=30, message = "le nom de la tâche doit avoir entre 5 et 30 caractères")
    String name;
    @Future(message = "une tâche doit être dans le futur")  // message personnalisé
    LocalDate dueDate;
}