package com.project.mahima.happyshopping;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mahima on 23/4/17.
 */
public class CustomList extends ArrayAdapter<String> {
    private String[] username;
    private String[] email;
    private String[] message;
    private Activity context;

    public CustomList(Activity context, String[] username, String[] email , String[] message ) {
        super(context, R.layout.list_view_layout, username);
        this.context = context;
        this.username = username;
        this.email = email;
        this.message = message;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewMessage = (TextView) listViewItem.findViewById(R.id.textViewMessage);

        textViewName.setText(username[position]);
        textViewEmail.setText(email[position]);
        textViewMessage.setText(message[position]);

        return listViewItem;
    }
}
