package mon.homecooking;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GlideImageLoader.SeachResult_item;
import adapter.GridAdapter;
import adapter.ListAdapter;
import adapter.SeachResultAdater;
import adapter.SearchHistoryListAdapter;
import bean.SeachResult;
import util.HttpUrlConnectionNetworkTask;
import util.NetworkTask;
import util.Networkutil;

public class Seach extends AppCompatActivity {

    ListView listView;
    GridView gridView;
    ListView resultView;
    EditText editText;
    LinearLayout startpage,result;
    TextView seachcancel;
    TextView clearhistory;
    List<String> hotseachlist = new ArrayList<>();
    List<SeachResult_item> itemslist = new ArrayList<>();
    List<String> historylist;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seach_page);
        Log.e("DAKAI","打开一次");
        init();
    }
    private void init(){
        listView = findViewById(R.id.seach_histroy);
        gridView = findViewById(R.id.seach_hotlist);
        resultView = findViewById(R.id.seach_result);
        editText =findViewById(R.id.seach_seachcontent);
        startpage = findViewById(R.id.seach_page);
        result = findViewById(R.id.seach_resultpage);
        seachcancel = findViewById(R.id.tx_seachcancel);
        clearhistory = findViewById(R.id.btn_clearhistory);
        historylist=new ArrayList<>();

        sharedPreferences = this.getSharedPreferences("history", Context.MODE_PRIVATE);
        String json1 = sharedPreferences.getString("historylist",null);
        setlistandgrid();

        if(json1 != null)
        {
            Log.e("TAG111", json1 );
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>(){}.getType();
            List<String> _list = gson.fromJson(json1,type);
            historylist.addAll(_list);
            Log.e("TAG222",historylist.get(0));
            ((SearchHistoryListAdapter)listView.getAdapter()).notifyDataSetChanged();
            //((FineMenuAdpter)lv_findmenu.getAdapter()).notifyDataSetChanged();
        }

        seteditlisteren();
        /*seachcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

    }
    private void setlistandgrid(){
        String[] hotlist = {"热门1","热门2","热门3","热门4","热门5","热门6","热门7","热门8"};
        hotseachlist.addAll(Arrays.asList(hotlist));
        GridAdapter gridAdapter = new GridAdapter(hotseachlist,this);
        gridView.setAdapter(gridAdapter);

        SearchHistoryListAdapter arrayAdapter = new SearchHistoryListAdapter(historylist,this);
        listView.setAdapter(arrayAdapter);


        SeachResultAdater seachResultAdater=new SeachResultAdater(this,itemslist);
        resultView.setAdapter(seachResultAdater);

    }
    public void onclick(View view){
        switch (view.getId()){
            case R.id.tx_seachcancel:
                finish();
                break;
            case  R.id.btn_clearhistory:
                SharedPreferences sptemp = getSharedPreferences("history", MODE_PRIVATE);
                if ( sptemp != null){
                    sptemp.edit().clear().commit();
                    ((SearchHistoryListAdapter)listView.getAdapter()).notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"清空成功",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private void seteditlisteren(){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                //Log.e("TA","哪里");
                if (i == EditorInfo.IME_ACTION_SEARCH || i == EditorInfo.IME_ACTION_DONE) {
                    startpage.setVisibility(View.GONE);
                    result.setVisibility(View.VISIBLE);
                    historylist.add(editText.getText().toString());
                    SharedPreferences.Editor editor = getSharedPreferences("history", MODE_PRIVATE).edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(historylist);
                    editor.putString("historylist",json);
                    editor.commit();
                    ((SearchHistoryListAdapter)listView.getAdapter()).notifyDataSetChanged();
                    Log.e("TAG",json );
                    itemslist.clear();
                    ((SeachResultAdater)resultView.getAdapter()).notifyDataSetChanged();
                    HttpUrlConnectionNetworkTask networkTask = new HttpUrlConnectionNetworkTask(NetworkTask.GET);
                    networkTask.execute(Networkutil.URL+"?flag=searchbyname&search_name="+editText.getText().toString());
                    networkTask.setResponseListener(new NetworkTask.ResponseListener() {
                        @Override
                        public void onSuccess(String result) {
                            SeachResult seachResult = new Gson().fromJson(result,SeachResult.class);
                            Log.e("getMsg",result);
                            if(seachResult.getMsg().equals("1")){
                                for(int j = 0;j<seachResult.getData().size(); j++)
                                    itemslist.add(new SeachResult_item(R.drawable.loginbackground,seachResult.getData().get(j),"小龙虾",10000));
                                ((SeachResultAdater)resultView.getAdapter()).notifyDataSetChanged();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"无此搜索项",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(String error) {
                            Toast.makeText(getApplicationContext(),"网络连接错误:"+error,Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return false;
            }
        });
    }
}
