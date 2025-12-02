package fr.eni.demospringweb.service;

import fr.eni.demospringweb.bo.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> listTodos();
    List<Todo> searchTodos(String search);
    Todo addTodo(Todo todo);
    void saveTodo(Todo todo);
    void deleteTodo(Long id);
}