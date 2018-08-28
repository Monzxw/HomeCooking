package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import GlideImageLoader.SeachResult_item;
import mon.homecooking.R;

import java.util.ArrayList;
import java.util.List;

public class SeachResultAdater extends BaseAdapter {
    Context context;
    List<SeachResult_item> list = new ArrayList<>();

    public SeachResultAdater(Context context, List<SeachResult_item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewholder holder = null;
        if(view == null)
        {
            holder = new viewholder();
            view = LayoutInflater.from(context).inflate(R.layout.seachresult_item,null,false);
            holder.imageView=view.findViewById(R.id.seachresult_picture);
            holder.title=view.findViewById(R.id.seachresult_title);
            holder.introduction=view.findViewById(R.id.seachresult_introduction);
            holder.people=view.findViewById(R.id.seachresult_peoplenumber);
            view.setTag(holder);
        }
        else
        {
            holder=(viewholder) view.getTag();
        }
        holder.imageView.setImageResource(list.get(i).getImgurl());
        //Bitmap bitmap = null;
        //holder.imageView.setImageBitmap(bitmap);
        holder.title.setText(list.get(i).getTitle());
        holder.introduction.setText(list.get(i).getIntroduction());
        holder.people.setText(("共有"+list.get(i).getPeoplenumber()+"浏览过"));
        return view;

    }
    public class viewholder{
        ImageView imageView;
        TextView title,introduction,people;
    }
}
