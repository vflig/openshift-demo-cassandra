package todolist.dto;

import todolist.entity.TodoEntity;

/**
 * Created by Johannes on 08.12.2014.
 */
public class Todo {
    public int id;
    public String text;

    public Todo() {}

    public Todo(TodoEntity entity) {
        this.id = entity.getId();
        this.text = entity.getText();
    }

    public TodoEntity toEntity() {
        TodoEntity entity = new TodoEntity();
        entity.setId(id);
        entity.setText(text);
        return entity;
    }
}
