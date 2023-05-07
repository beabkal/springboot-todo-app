package todoapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todoapp.dto.TodoDto;
import todoapp.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("api/todos")
public class TodoController {

    private TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
//    REST endpoint for creating a new todo

    @PostMapping("/create")
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
        TodoDto newTodo =  todoService.addTodo(todoDto);

        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }
//    REST endpoint for getting a todo
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id){
//        Get todo dto
        TodoDto todoDto = todoService.getTodoById(id);
//        return a success response with the todo dto object
        return new ResponseEntity<>(todoDto, HttpStatus.OK);

    }
//    REST endpoint for getting a list of todos
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
//        Get all todos as dto and return a list
        List<TodoDto> todoDtoList = todoService.getAllTodos();
        return new ResponseEntity<>(todoDtoList, HttpStatus.OK);
    }
//       REST endpoint for Updating a todo
    @PutMapping("{id}/update")
    public ResponseEntity<TodoDto> updateTodoById(
                @PathVariable Long id,
                @RequestBody TodoDto todoDto
        ){
//        Set todoDto id to id in the path variable
        TodoDto updatedDto = todoService.updateTodoById(id, todoDto);

        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
        }
//      REST endpoint for deleting a todo
        @PostMapping("{id}/delete")
        public ResponseEntity<String> deleteTodoById(@PathVariable Long id){
        todoService.deleteTodoById(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
     }
//     REST endpoint for patching "completed" section of todo
     @PatchMapping("{id}/complete")
        public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto completedTodo = todoService.completeTodoById(id);
        return new ResponseEntity<>(completedTodo, HttpStatus.OK);
        }
}
