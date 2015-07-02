package todolist.repository;

import todolist.entity.TodoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TodoRepository extends PagingAndSortingRepository<TodoEntity, Integer> {
    List<TodoEntity> findAll();
}