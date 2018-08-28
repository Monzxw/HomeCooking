package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mon.homecooking.R;

public class GridAdapter extends BaseAdapter{

    List<String> list = new ArrayList<>();
    Context context;

    public GridAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
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
        viewholder holder = null ;
        if(view == null)
        {
            holder = new viewholder();
            view = LayoutInflater.from(context).inflate(R.layout.list_item,null,false);
            holder.textView = view.findViewById(R.id.list_item_name);
            view.setTag(holder);
        }
        else{
            holder = (viewholder) view.getTag() ;
        }
        holder.textView.setText(list.get(i));
        return view;
    }


    public class viewholder{
        TextView textView;
    }

}
