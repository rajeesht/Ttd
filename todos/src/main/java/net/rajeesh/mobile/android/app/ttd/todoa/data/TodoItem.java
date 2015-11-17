package net.rajeesh.mobile.android.app.ttd.todoa.data;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by rtripathi on 11/16/15.
 */
@Table(name = "todo_item")
public class TodoItem extends Model {

    @Column(name = "todo_id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name = "priority")
    public byte priority;

    // Make sure to have a default constructor for every ActiveAndroid model
    public TodoItem() {
        super();
    }

    public TodoItem(int id, String name, byte priority) {
        super();
        this.id = id;
        this.name = name;
        this.priority = priority;
    }
}
