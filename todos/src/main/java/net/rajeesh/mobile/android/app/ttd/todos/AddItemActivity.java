package net.rajeesh.mobile.android.app.ttd.todos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import net.rajeesh.mobile.android.app.ttd.todos.data.TodoItem;
import net.rajeesh.mobile.android.app.ttd.todos.enums.PriorityEnum;


public class AddItemActivity extends AppCompatActivity {

    EditText edEditText;
    PriorityEnum priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        priority = PriorityEnum.NORMAL;
        edEditText = (EditText) findViewById(R.id.edEditText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbHigh:
                if (checked)
                    priority = PriorityEnum.HIGH;
                    break;
            case R.id.rbNormal:
                if (checked)
                    priority = PriorityEnum.NORMAL;
                    break;
            case R.id.rbLow:
                if (checked)
                    priority = PriorityEnum.LOW;
                break;
            default:
                priority = PriorityEnum.NORMAL;
                break;
        }
    }

    public void saveItem(View view) {
        Intent data = new Intent();
        TodoItem todoItem = new TodoItem();
        todoItem.name = edEditText.getText().toString();
        todoItem.priority = priority.getPriorityValue();
        data.putExtra("listItem", todoItem);
        setResult(RESULT_OK, data);
        this.finish();
    }
}
