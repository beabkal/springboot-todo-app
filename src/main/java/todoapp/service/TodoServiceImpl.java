package todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoapp.dto.TodoDto;
import todoapp.entity.Todo;
import todoapp.exception.ResourceNotFoundException;
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
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("todo", "id", Long.toString(id)))
                ;
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
    public TodoDto updateTodoById(Long id, TodoDto todoDto) {
//        Get the todoDto object to be updated
        TodoDto todoDtoToUpdate = getTodoById(id);

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
    public TodoDto completeTodoById(Long id) {
//        find todo by id
        TodoDto todoDto = getTodoById(id);
//        set completed status to true
        todoDto.setCompleted(true);
//        convert updated todoDto to todo and save to db and return a todo entity
        Todo todoCompleted = todoRepository.save(AutoToDoMapper.MAPPER.mapToTodo(todoDto));
//        convert to todo to todoDto and return
        return AutoToDoMapper.MAPPER.mapToTodoDto(todoCompleted);
    }

    @Override
    public TodoDto incompleteTodoById(Long id) {

//        find todo by id
        TodoDto todoDto = getTodoById(id);
//        set completed status to false
        todoDto.setCompleted(false);
//        convert updated todoDto and save to db and return a todo entity
        Todo todoIncomplete = todoRepository.save(AutoToDoMapper.MAPPER.mapToTodo(todoDto));
//        convert todo back to todoDto and return
        return AutoToDoMapper.MAPPER.mapToTodoDto(todoIncomplete);
    }

    @Override
    public void deleteTodoById(Long id) {
//        Check if todo with id exists and throw a ResourceNotFound exception if not found
        Todo todoToDelete = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("todo", "id", Long.toString(id)));
        todoRepository.delete(todoToDelete);
    }
}
