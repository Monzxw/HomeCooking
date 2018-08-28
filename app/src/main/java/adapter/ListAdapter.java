package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import mon.homecooking.R;


public class ListAdapter extends BaseAdapter{

    List<String> list = new ArrayList<>();
    Context context;
    LayoutInflater mInflater;
    public int selected;

    public ListAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.mInflater = mInflater;
        mInflater = LayoutInflater.from(context);
        selected=-1;
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
            view = mInflater.from(context).inflate(R.layout.list_item,null,false);
            holder.textView = view.findViewById(R.id.list_item_name);
            view.setTag(holder);
        }
        else{
            holder = (viewholder) view.getTag() ;
        }
        holder.textView.setText(list.get(i));
        if(selected == i){
            holder.textView.setBackgroundColor(0xFFFFFFFF);
        }
        else {
            holder.textView.setBackgroundColor(0xFFCCCCCC);
        }
        return view;
    }

    public class viewholder{
        public TextView getTextView() {
            return textView;
        }

        public void setTextView(TextView textView) {
            this.textView = textView;
        }

        TextView textView;
    }
    public void setSelected(int position){
        selected = position;
        notifyDataSetChanged();
    }
}
