package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import mon.homecooking.R;

public class SearchHistoryListAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public SearchHistoryListAdapter(List<String> list, Context context) {
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
        ViewHolder holder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,null,false);
            holder = new ViewHolder();
            holder.list_item_name = view.findViewById(R.id.list_item_name);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        holder.list_item_name.setText(list.get(i));
        return view;
    }

    public class ViewHolder{
        TextView list_item_name;
    }
}
