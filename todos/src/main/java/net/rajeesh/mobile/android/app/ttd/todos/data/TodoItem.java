package net.rajeesh.mobile.android.app.ttd.todos.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.io.Serializable;

/**
 * Created by rtripathi on 11/16/15.
 */
@Table(name = "todo_item")
public class TodoItem extends Model implements Serializable {

    private static final long serialVersionUID = -4726539595707992280L;

    @Column(name = "todo_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "priority")
    public int priority;

    // Make sure to have a default constructor for every ActiveAndroid model
    public TodoItem() {
        super();
    }

    public TodoItem(int id, String name, int priority) {
        super();
        this.id = id;
        this.name = name;
        this.priority = priority;
    }
}
