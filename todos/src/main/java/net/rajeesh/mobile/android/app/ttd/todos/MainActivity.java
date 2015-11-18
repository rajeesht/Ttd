package net.rajeesh.mobile.android.app.ttd.todos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import net.rajeesh.mobile.android.app.ttd.adapter.TodoItemAdapter;
import net.rajeesh.mobile.android.app.ttd.todos.data.TodoItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<TodoItem> todoItems;
    ArrayAdapter<TodoItem> aTodoAdapter;
    ListView lvItems;

    final int REQUEST_CODE_NEW = 10;
    final int REQUEST_CODE_EDIT = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aTodoAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoItems.remove(position);
                aTodoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                i.putExtra("listItem", todoItems.get(position));
                i.putExtra("itemPosition", position);
                startActivityForResult(i, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            TodoItem item = (TodoItem) data.getSerializableExtra("listItem");
            if (item != null) {
                if (requestCode == REQUEST_CODE_NEW) {
                    todoItems.add(item);
                } else if (requestCode == REQUEST_CODE_EDIT) {
                    int position = data.getIntExtra("itemPosition", 0);
                    todoItems.set(position, item);
                }
            }
            aTodoAdapter.notifyDataSetChanged();
            writeItems();
        }
    }

    public void populateArrayItems() {
        todoItems = new ArrayList<TodoItem>();
        readItems();
        aTodoAdapter = new TodoItemAdapter(this, todoItems);
    }

    public void onAddItem(View view) {
        Intent i = new Intent(MainActivity.this, AddItemActivity.class);
        startActivityForResult(i, REQUEST_CODE_NEW);
    }

    private void readItems() {
        try {
            todoItems = (ArrayList) new Select().distinct().from(TodoItem.class).orderBy("todo_id").execute();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void writeItems() {
        ActiveAndroid.beginTransaction();
        try {
            new Delete().from(TodoItem.class).execute();
            if (!todoItems.isEmpty()) {
                for (TodoItem todoItem : todoItems) {
                    TodoItem newTodoItem = new TodoItem();
                    newTodoItem.id = todoItems.indexOf(todoItem);
                    newTodoItem.name = todoItem.name;
                    newTodoItem.priority = todoItem.priority;
                    newTodoItem.save();
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            ActiveAndroid.endTransaction();
        }
    }
}
