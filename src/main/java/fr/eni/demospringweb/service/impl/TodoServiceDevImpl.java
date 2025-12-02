package fr.eni.demospringweb.service.impl;

import fr.eni.demospringweb.bo.Todo;
import fr.eni.demospringweb.service.TodoService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation "bouchon" du  ServiceTodoService en attendant d'avoir la base de donnée (on gère la liste de tâches en mémoire)
 */
@Profile("dev")
@Service
public class TodoServiceDevImpl implements TodoService {
    // Dans cette implémentation c'est dans cette liste qu'on gère nos tâches
    List<Todo> listeTodo = new ArrayList<>();

    public TodoServiceDevImpl() {
        listeTodo.add(new Todo(1L, "faire les courses"));
        listeTodo.add(new Todo(2L, "suivre cours ENI"));
    }

    @Override
    public List<Todo> listTodos() {
        return listeTodo;
    }

    @Override
    public List<Todo> searchTodos(String search) {
        List<Todo> listeFiltree = new ArrayList<>();
        for (Todo todo : listeTodo)
        {
            if (todo.getName().contains(search)) {
                listeFiltree.add(todo);
            }
        }
        return listeFiltree;
    }

    @Override
    public Todo addTodo(Todo todo) {

        if (listeTodo.isEmpty()){
            // je définit l'id de ma tâche à 1
            todo.setId(1L);
        }
        else{
            // je définit l'id de ma tâche comme celui de ma dernière tâche + 1
            int lastIndex = listeTodo.size() -1;
            todo.setId(listeTodo.get(lastIndex).getId() + 1);
        }

        listeTodo.add(todo);
        return todo;
    }

    @Override
    public void saveTodo(Todo todo) {
        for (int i = 0; i < listeTodo.size(); i++) {
            if (listeTodo.get(i).getId().equals(todo.getId())) {
                listeTodo.set(i, todo);
            }
        }
    }

    @Override
    public void deleteTodo(Long id) {
        for (int i = 0; i < listeTodo.size(); i++) {
            if (listeTodo.get(i).getId().equals(id)) {
                listeTodo.remove(i);
            }
        }
    }
}