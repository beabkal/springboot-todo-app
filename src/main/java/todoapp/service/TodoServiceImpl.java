package todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoapp.dto.TodoDto;
import todoapp.entity.Todo;
import todoapp.mapper.AutoToDoMapper;
import todoapp.repository.TodoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    private TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
//        convert todoDto into todo Jpa entity
        Todo newTodo = AutoToDoMapper.MAPPER.mapToTodo(todoDto);
        System.out.println(todoDto);
//        save todo entity in db
        Todo savedTodo = todoRepository.save(newTodo);
//        convert saved todo entity to todoDto object
        TodoDto savedTodoDto = AutoToDoMapper.MAPPER.mapToTodoDto(savedTodo);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).get();
        TodoDto todoDto = AutoToDoMapper.MAPPER.mapToTodoDto(todo);
        return todoDto;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todoList = todoRepository.findAll();
        List<TodoDto> todoDtoList = new ArrayList<>();
//        Convert each todo into a todoDto and add to todoDto list and return
        todoList.forEach((todo) -> todoDtoList.add(AutoToDoMapper.MAPPER.mapToTodoDto(todo)));
        return todoDtoList;
    }

    @Override
    public TodoDto updateTodoById(TodoDto todoDto) {
//        Get the todoDto object to be updated
        TodoDto todoDtoToUpdate = getTodoById(todoDto.getId());

//        Set the updated values to todo dto
        todoDtoToUpdate.setTitle(todoDto.getTitle());
        todoDtoToUpdate.setDescription(todoDto.getDescription());
        todoDtoToUpdate.setDueDate(todoDto.getDueDate());
        todoDtoToUpdate.setCompleted(todoDto.isCompleted());

//        convert the updated todo dto to dto entity and save the new updated todo
        Todo todoUpdated = todoRepository.save(AutoToDoMapper.MAPPER.mapToTodo(todoDtoToUpdate));
//        converted the saved todo entity to todo entity and return
        return AutoToDoMapper.MAPPER.mapToTodoDto(todoUpdated);

    }

    @Override
    public void deleteTodoById(Long id) {
        Todo todoToDelete = todoRepository.findById(id).get();
        todoRepository.delete(todoToDelete);
    }
}
