package todoapp.service;

import todoapp.dto.TodoDto;

import java.util.List;

public interface TodoService{
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodoById(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodoById(Long id, TodoDto todoDto);
    TodoDto completeTodoById(Long id);
    void deleteTodoById(Long id);
}
