package com.baiyai.android.fuganghomework;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Custom Adapter
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View viewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            viewHolder = inflater.inflate(R.layout.image_list_item, viewGroup, false);

            ImageView imageView = (ImageView) viewHolder.findViewById(R.id.profile_image);
            imageView.setImageResource(mThumbIds[position]);

            TextView textView = (TextView) viewHolder.findViewById(R.id.profile_name);
            textView.setText("John Doe");




        } else {
            viewHolder = convertView;
        }

        return viewHolder;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7
    };
}
