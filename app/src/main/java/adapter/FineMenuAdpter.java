package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.mask.PorterCircularImageView;

import java.util.ArrayList;
import java.util.List;

import GlideImageLoader.Finemenuitem;
import bean.GetFineMenu;
import mon.homecooking.R;

public class FineMenuAdpter extends BaseAdapter {
    Context context;
    List<Finemenuitem> list = new ArrayList<>();

    public FineMenuAdpter(Context context, List<Finemenuitem> list) {
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
        viewholder holder =null;
        if(view == null){
            holder = new viewholder();
            view= LayoutInflater.from(context).inflate(R.layout.main_finemenu,null,false);
            holder.porterCircularImageView = view.findViewById(R.id.iv_finemenupicture);
            holder.name = view.findViewById(R.id.tx_name);
            holder.cate = view.findViewById(R.id.tx_cate);
            view.setTag(holder);
        }
        else {
            holder = (viewholder) view.getTag();
        }
        holder.name.setText(list.get(i).getName());
        holder.cate.setText(list.get(i).getCate());
        holder.porterCircularImageView.setImageBitmap(list.get(i).getBitmap());
        return view;
    }
    public class viewholder{
        com.github.siyamed.shapeimageview.mask.PorterShapeImageView porterCircularImageView;
        TextView name,cate;
    }
}
