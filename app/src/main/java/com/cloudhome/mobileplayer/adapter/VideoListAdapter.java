package com.cloudhome.mobileplayer.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cloudhome.mobileplayer.R;
import com.cloudhome.mobileplayer.bean.VideoItemData;
import com.cloudhome.mobileplayer.utils.Utils;

import java.util.ArrayList;

/**
 * Created by xionghu on 2016/7/6.
 * Email：965705418@qq.com
 */
public class VideoListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater = null;
    private ArrayList<VideoItemData> videoItems;
    public VideoListAdapter(Context context, ArrayList<VideoItemData> videoItems)
    {
        this.context = context;

        this.videoItems= videoItems;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return  videoItems.size();
    }

    @Override
    public Object getItem(int i) {
        return videoItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_videolist,parent,false);
            holder.name= (TextView) view.findViewById(R.id.name);
            holder.duration= (TextView) view.findViewById(R.id.duration);
            holder.size= (TextView) view.findViewById(R.id.size);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        VideoItemData itemData = videoItems.get(position);

        holder.name.setText(itemData.getName());

        holder.size.setText(Formatter.formatFileSize(context,itemData.getSize()));
        holder.duration.setText(Utils.formatLongToTimeStr(itemData.getDuration()));
        return view;
    }

    class ViewHolder{

        private TextView name;
        private TextView duration;
        private TextView size;
    }
}
