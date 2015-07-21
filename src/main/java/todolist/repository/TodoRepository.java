package todolist.repository;

import org.springframework.data.repository.CrudRepository;
import todolist.entity.TodoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<TodoEntity, Integer> {
    List<TodoEntity> findAll();
}