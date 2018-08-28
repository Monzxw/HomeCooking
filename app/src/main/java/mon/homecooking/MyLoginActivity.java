package mon.homecooking;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import bean.LoginBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import util.HttpUrlConnectionNetworkTask;
import util.HttpUrlConnectionNetworkTaskReturnBytes;
import util.NetworkTask;
import util.NetworkTaskReturnBytes;
import util.Networkutil;

public class MyLoginActivity extends Activity {

    @BindView(R.id.iv_icon)
    ImageView iv_icon;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.cb_remember_password)
    CheckBox cb_remember_password;
    @BindView(R.id.cb_remember_me)
    CheckBox cb_remember_me;
    @BindView(R.id.btn_login)
    Button btn_login;
    SharedPreferences userandpasswd;
    Context context;
    String username;
    String password;
    Map<String,String> mp_userandpasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);
        mp_userandpasswd = new HashMap<>();
        getusername();
        getpasswd();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                //Network
                HttpUrlConnectionNetworkTask loginTask = new HttpUrlConnectionNetworkTask(NetworkTask.POST);
                loginTask.execute(Networkutil.URL+"?flag=login&username="+username+"&password="+password);
                loginTask.setResponseListener(new NetworkTask.ResponseListener() {
                    @Override
                    public void onSuccess(String result) {
                        LoginBean loginBean = new Gson().fromJson(result,LoginBean.class);
                        if(loginBean.getMsg().equals("成功")){
                            Toast.makeText(MyLoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                            if(cb_remember_me.isChecked()){
                                saveusername();
                                if(cb_remember_password.isChecked()){
                                    savepasswd();
                                }
                            }
                            finish();
                        }
                        else{
                            Toast.makeText(MyLoginActivity.this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MyLoginActivity.this,"网络连接错误",Toast.LENGTH_SHORT).show();
                    }
                });


                /*HttpUrlConnectionNetworkTaskReturnBytes imageTask = new HttpUrlConnectionNetworkTaskReturnBytes(NetworkTask.GET);
                imageTask.execute("https://www.baidu.com/img/bd_logo1.png");
                imageTask.setResponseListener(new NetworkTaskReturnBytes.ResponseListener() {
                    @Override
                    public void onSuccess(byte[] result) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(result,0,result.length);
                        iv_icon.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(byte[] error) {
                        Toast.makeText(MyLoginActivity.this,"网络连接错误",Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
    }
    private void saveusername(){
        userandpasswd = this.getSharedPreferences("userandpasswd",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userandpasswd.edit();
        editor.putString("username",username);
        editor.commit();

    }
    private void getusername(){
      userandpasswd = this.getSharedPreferences("userandpasswd",Context.MODE_PRIVATE);
      mp_userandpasswd.put("username",userandpasswd.getString("username",""));
      et_username.setText(mp_userandpasswd.get("username"));
    }
    private void savepasswd()
    {
        userandpasswd = this.getSharedPreferences("userandpasswd",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userandpasswd.edit();
        editor.putString("passwd",password);
        editor.commit();
    }
    private void getpasswd(){
        userandpasswd = this.getSharedPreferences("userandpasswd",Context.MODE_PRIVATE);
        mp_userandpasswd.put("passwd",userandpasswd.getString("passwd",""));
        et_password.setText(mp_userandpasswd.get("passwd"));
    }
}
