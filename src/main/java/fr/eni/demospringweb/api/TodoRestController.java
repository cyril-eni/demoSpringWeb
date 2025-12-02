package fr.eni.demospringweb.api;

import fr.eni.demospringweb.bo.Todo;
import fr.eni.demospringweb.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Documentation d'API ajoutée via les annotations @Tag, @Operation
 * Possibilité de le faire à partir de la JvaDoc : https://springdoc.org/#javadoc-support
 */
@Tag(name = "Todo")
@RestController
@CrossOrigin
@RequestMapping("/todo")
public class TodoRestController {
    @Autowired
    TodoService todoService;

    /**
     * Sur mon Get, j'ajoute l'option d'avoir un filtre de recherche : http://localhost:8080/todo?search=eni
     * Si je veux ajouter un paramètre optionnel , ne pas oublier de mettre @RequestParam(required = false)     *
     */
    @GetMapping
    @Operation(summary = "lister ou chercher parmis les tâches")
    public List<Todo> getTodos(@RequestParam(required = false) String search){
        if (search == null || search.isBlank()){
            return todoService.listTodos();
        }
        else{
            return todoService.searchTodos(search);
        }
    }

    /**
     * @RequestBody : permet d'affecter les attributs de l'argument Todo avec les attributs JSON de la requête HTTP
     * Si on ouble cette annotation, on va avoir un objet Todo vide
     * Si besoin de récupérer un fichier (envoyer avec un input type="file") : @RequestParam("file") MultipartFile file (cf : https://spring.io/guides/gs/uploading-files)
     */
    @PostMapping
    @Operation(summary = "créer une tâche")
    public Todo postTodo(@RequestBody Todo todo){
        return todoService.addTodo(todo);
    }

    /**
     * @PathVariable : permet de récupérer en argument une variable de l'URL de la requête HTTP
     * Ne pas oublier d'ajouter dans l'url de mappin comment retrouver cette variable : ici "/{id}"
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer une tâche")
    public void deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
    }

    /**
     * On peut combiner PathVariable et RequestBody
     */
    @PutMapping("/{id}")
    @Operation(summary = "modifier une tâche")
    public void postTodo(@PathVariable Long id, @RequestBody Todo todo){
        todo.setId(id);
        todoService.saveTodo(todo);
    }
}
