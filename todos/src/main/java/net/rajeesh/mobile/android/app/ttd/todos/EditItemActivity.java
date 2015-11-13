package net.rajeesh.mobile.android.app.ttd.todos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class EditItemActivity extends AppCompatActivity {

    EditText edEditText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        edEditText = (EditText) findViewById(R.id.edEditText);
        edEditText.setText(getIntent().getStringExtra("listItem"));
        position = getIntent().getIntExtra("itemPosition", 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_item, menu);
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

    public void saveItem(View view) {
        Intent data = new Intent();
        data.putExtra("listItem", edEditText.getText().toString());
        data.putExtra("itemPosition", position);
        setResult(RESULT_OK, data);
        this.finish();
    }
}
