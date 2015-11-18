package net.rajeesh.mobile.android.app.ttd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import net.rajeesh.mobile.android.app.ttd.todos.R;
import net.rajeesh.mobile.android.app.ttd.todos.data.TodoItem;

import java.util.ArrayList;

/**
 * Created by rtripathi on 11/17/15.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {

    public TodoItemAdapter(Context context, ArrayList<TodoItem> todoItems) {
        super(context, 0, todoItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem todoItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_item, parent, false);
        }

        TextView tvPriority = (TextView) convertView.findViewById(R.id.tvPriority);
        TextView tvTodoItem = (TextView) convertView.findViewById(R.id.tvTodoItem);

        switch (todoItem.priority) {
            // Low
            case -1:
                tvPriority.setText("\u2B07");
                break;
            // Normal
            case 0:
                tvPriority.setText("\u2B0C");
                break;
            // High
            case 1:
                tvPriority.setText("\u2B06");
                break;
            default:
                tvPriority.setText("\u2B0C");
                break;
        }
        tvTodoItem.setText(todoItem.name);

        return convertView;
    }

}
