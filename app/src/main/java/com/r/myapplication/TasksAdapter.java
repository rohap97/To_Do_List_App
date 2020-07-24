package com.r.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksAdapter  extends ArrayAdapter<task> {

    private static class ViewHolder {
        TextView text;
        TextView date;
    }


    public TasksAdapter(Context context, ArrayList<task> users) {
        super(context, R.layout.grid_view_items, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.grid_view_items, parent, false);
            viewHolder.text = (TextView) convertView.findViewById(R.id.text);
            viewHolder.date = (TextView) convertView.findViewById(R.id.date);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.text.setText(task.getTask_desc());
        viewHolder.date.setText(task.getDate());
        // Return the completed view to render on screen
        return convertView;
    }
}
