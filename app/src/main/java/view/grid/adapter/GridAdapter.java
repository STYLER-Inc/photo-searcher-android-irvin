package view.grid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.styler.challenge.R;
import model.Photo;

public class GridAdapter extends BaseAdapter {

    private List<Photo> photoUrls;
    private Context context;
    private ImageClickedListener listener;


    public GridAdapter(Context context, List<Photo> photoUrls, ImageClickedListener listener) {
        this.photoUrls = photoUrls;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return photoUrls.size();
    }


    @Override
    public Object getItem(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listViewHolder;
        if (convertView == null) {
            listViewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);

            listViewHolder.imageView = convertView.findViewById(R.id.grid_item_image);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

        listViewHolder.imageView.setOnClickListener(view -> listener.onImageTap(photoUrls.get(position)));

        Glide.with(context).load(photoUrls.get(position).getThumb()).into(listViewHolder.imageView);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
