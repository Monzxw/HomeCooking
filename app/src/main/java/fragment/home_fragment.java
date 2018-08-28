package fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import GlideImageLoader.Finemenuitem;
import GlideImageLoader.GlideImageLoader;
import adapter.FineMenuAdpter;
import bean.GetFineMenu;
import butterknife.BindView;
import butterknife.ButterKnife;
import mon.homecooking.MainActivity;
import mon.homecooking.MyLoginActivity;
import mon.homecooking.R;
import mon.homecooking.Seach;
import util.HttpUrlConnectionNetworkTask;
import util.HttpUrlConnectionNetworkTaskReturnBytes;
import util.NetworkTask;
import util.NetworkTaskReturnBytes;
import util.Networkutil;

public class home_fragment extends Fragment {
    @BindView(R.id.main_banner)
    Banner banner;
    ImageView iv_userlogin;
    ImageView iv_classify;
    Button seachedit;
    ListView lv_findmenu;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home,null,false);
        ButterKnife.bind(getActivity());
        init(view);
        return view;
    }
    private void init(View view){
        banner = view.findViewById(R.id.main_banner);
        seachedit = view.findViewById(R.id.main_seach);
        iv_classify = view.findViewById(R.id.iv_classify);
        iv_userlogin = view.findViewById(R.id.iv_userloginin);
        lv_findmenu = view.findViewById(R.id.lv_finemenu);
        indexbanner();
        getFineMenu();
        seachedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(getActivity(), Seach.class);
                Toast.makeText(getActivity(),"点击事件",Toast.LENGTH_SHORT).show();
                startActivity(it1);
            }
        });
        iv_userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(getActivity(), MyLoginActivity.class);
                startActivity(it1);
            }
        });
        iv_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it2 = new Intent(getActivity(), MainActivity.class);
                it2.putExtra("fragmentnumber",1);
                startActivity(it2);
            }
        });
    }
    /*public void onclick(View view){
        Log.e("TAB","点击事件");
        switch (view.getId()){
            case R.id.iv_userloginin:
                Intent it1 = new Intent(getActivity(), MyLoginActivity.class);
                startActivity(it1);
                break;
            case R.id.iv_classify:
                Intent it2 = new Intent(getActivity(),classify_fragment.class);
                startActivity(it2);
                break;

        }
    }*/
    private void indexbanner(){
        ArrayList<Integer> images = new ArrayList<Integer>();
        for (int position = 0; position < 5; position++)
            images.add(getResId("ic_test_" + position, R.drawable.class));
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.start();
    }
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    private void getFineMenu()
    {
        final List<Finemenuitem> Finemenuitem_list = new ArrayList<>();
        FineMenuAdpter fineMenuAdpter = new FineMenuAdpter(getActivity(),Finemenuitem_list);
        lv_findmenu.setAdapter(fineMenuAdpter);
        final HttpUrlConnectionNetworkTask getfinemenu = new HttpUrlConnectionNetworkTask(NetworkTask.GET);
        getfinemenu.execute(Networkutil.URL+"?flag=getfoodmemus");
        getfinemenu.setResponseListener(new NetworkTask.ResponseListener() {
            @Override
            public void onSuccess(String result) {
                /*List<GetFineMenu> itemlist = new ArrayList<>();
                itemlist = new Gson().fromJson(result,new TypeToken<List<GetFineMenu>>(){}.getType());
                for (int i = 0 ;i< itemlist.size();i++)
                    Log.e("TAG",itemlist.get(i).getData())*/
                final GetFineMenu getFineMenu = new Gson().fromJson(result,GetFineMenu.class);
                if(getFineMenu.getMsg().equals("成功"))
                {

                    for(int i = 0; i < getFineMenu.getData().size() ; i++){
                        final String cate = getFineMenu.getData().get(i).getCate();
                        final String name = getFineMenu.getData().get(i).getName();
                        String imageurl = getFineMenu.getData().get(i).getImgurl();
                        Log.e("TAG",cate );
                        final Bitmap[] bitmap = new Bitmap[1];
                        HttpUrlConnectionNetworkTaskReturnBytes imageTask = new HttpUrlConnectionNetworkTaskReturnBytes(NetworkTask.GET);
                        imageTask.execute(imageurl);
                        imageTask.setResponseListener(new NetworkTaskReturnBytes.ResponseListener() {
                            @Override
                            public void onSuccess(byte[] result) {
                                bitmap[0] = BitmapFactory.decodeByteArray(result,0,result.length);
                                Finemenuitem_list.add(new Finemenuitem(bitmap[0],cate,name));
                                Log.e("TAB","请求图片");
                                ((FineMenuAdpter)lv_findmenu.getAdapter()).notifyDataSetChanged();

                            }

                            @Override
                            public void onError(byte[] error) {
                                Toast.makeText(getActivity(),"网络连接错误,请求图片失败",Toast.LENGTH_SHORT).show();
                            }
                        });


                    }


                }
                else{
                    Toast.makeText(getActivity(),"网址出错",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getActivity(),"网络连接错误:"+error,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
