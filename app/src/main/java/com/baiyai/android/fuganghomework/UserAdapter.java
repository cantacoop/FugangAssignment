package com.baiyai.android.fuganghomework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        // Get the data item for this position
        User user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_list_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameView.setText(user.getName());
        Glide.with(getContext()).load(user.getPhoto()).into(viewHolder.photoView);

        // Return the completed view to render on screen
        return convertView;
    }

    private class ViewHolder {

        public TextView nameView;
        public ImageView photoView;

        public ViewHolder(View convertView) {
            nameView = convertView.findViewById(R.id.list_name);
            photoView = convertView.findViewById(R.id.list_photo);
        }
    }
}
