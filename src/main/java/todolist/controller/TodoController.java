package todolist.controller;

import todolist.dto.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todolist.repository.TodoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Johannes on 03.01.2015.
 */
@RestController
@RequestMapping("api/todo")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Todo> list() {
        return todoRepository.findAll().stream().map(Todo::new).collect(Collectors.toList());
    }

    @RequestMapping(value="/{todoId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("todoId") Integer todoId) {
        todoRepository.delete(todoId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Todo create(@RequestBody @Valid Todo todo) {
        return new Todo(todoRepository.save(todo.toEntity()));
    }

}
