package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapter.GridAdapter;
import adapter.ListAdapter;
import bean.ClassifyBean;
import butterknife.BindView;
import mon.homecooking.R;
import util.HttpUrlConnectionNetworkTask;
import util.NetworkTask;
import util.Networkutil;

public class classify_food extends Fragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.lv_list)
    ListView listView;
    @BindView(R.id.lv_grid)
    GridView gridView;
    List<String> listfood_name = new ArrayList();
    List<String> listfood_item = new ArrayList<>();
    ListAdapter listAdapter;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.classify_food,null,false);


        listView = view.findViewById(R.id.food_list);
        gridView = view.findViewById(R.id.food_grid);
        init();

        return view;
    }

    private void init(){
        HttpUrlConnectionNetworkTask classifynetworkTask = new HttpUrlConnectionNetworkTask(NetworkTask.GET);
        classifynetworkTask.execute(Networkutil.URL+"?flag=getallfoodtype");
        classifynetworkTask.setResponseListener(new NetworkTask.ResponseListener() {
            @Override
            public void onSuccess(String result) {
                ClassifyBean classifyBean = new Gson().fromJson(result,ClassifyBean.class);
                if (classifyBean.getMsg().equals("成功"))
                {
                    listfood_name=classifyBean.getData();
                    listAdapter = new ListAdapter(listfood_name,getActivity());
                    listView.setAdapter(listAdapter);
                    ((ListAdapter)listView.getAdapter()).notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getActivity(),"获取网址内容有误",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(),"网络连接错误",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {

        String classifyname=listfood_name.get(i);
        listAdapter.setSelected(i);
        HttpUrlConnectionNetworkTask classify_itemNetworkTask = new HttpUrlConnectionNetworkTask(NetworkTask.GET);
        classify_itemNetworkTask.execute(Networkutil.URL+"?flag=getfoodbytype&food_type="+classifyname);
        classify_itemNetworkTask.setResponseListener(new NetworkTask.ResponseListener() {
            @Override
            public void onSuccess(String result) {
                ClassifyBean classifyBean = new Gson().fromJson(result,ClassifyBean.class);
                if(classifyBean.getMsg().equals("成功")){
                    listfood_item = classifyBean.getData();
                    GridAdapter gridAdapter= new GridAdapter(listfood_item,getActivity());
                    gridView.setAdapter(gridAdapter);
                    //((ListAdapter.viewholder)(view.getTag())).getTextView().setBackgroundColor(0xFFFF0000);
                }
                else {
                    Toast.makeText(getActivity(),"获取网址内容有误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(),"网络连接错误",Toast.LENGTH_SHORT).show();
            }
        });
        //Toast.makeText(getActivity(),"item点击了这个位置"+i,Toast.LENGTH_SHORT).show();
    }
}
