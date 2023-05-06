package todoapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import todoapp.dto.TodoDto;
import todoapp.entity.Todo;

@Mapper
public interface AutoToDoMapper {
//    Provide the implementation for this interface at compile time
    AutoToDoMapper MAPPER = Mappers.getMapper(AutoToDoMapper.class);

//    Map from TodoDto to Todo
    Todo mapToTodo(TodoDto toDoDto);
    TodoDto mapToTodoDto(Todo toDo);

}
