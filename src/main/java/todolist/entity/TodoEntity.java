package todolist.entity;

import javax.persistence.*;

/**
 * Created by Johannes on 08.12.2014.
 */
@Entity
@Table(name="todo")
public class TodoEntity {
    private int id;
    private String text;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }

        TodoEntity other = (TodoEntity) o;

        if (id != other.id) return false;
        if (text != null ? !text.equals(other.text) : other.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
