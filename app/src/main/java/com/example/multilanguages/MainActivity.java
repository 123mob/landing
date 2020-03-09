package com.example.multilanguages;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.example.multilanguages.utils.LocalManageUtil;
public class MainActivity extends BaseActivity {
    private Button startSettingActivity;
    private EditText accEdt,pwdEdt;
    private String account;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        accEdt = findViewById(R.id.name);
        pwdEdt = findViewById(R.id.password);
        //SharePreference的读取
        //①获取sharepreference对象
        SharedPreferences share = getSharedPreferences("myshare",MODE_PRIVATE);
        //②根据key获取内容
        /*
        （key, 对应key不存在时返回参数2内容作为默认值）
         */
        account = share.getString("account","");
        password = share.getString("password","");
        //③显示到控件
        accEdt.setText(account);
        pwdEdt.setText(password);
        findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1、获取两个输入框的内容
                account = accEdt.getText().toString();
                password = pwdEdt.getText().toString();
                //2、验证(admin 123456)
                if(account.equals("admin")&& password.equals("123456")){
                    //2.1存储信息到SharePreference
                    //①获取sharepreference对象
                    /*
                    （xml文件名称【不加后缀】，操作模式）
                     */
                    SharedPreferences share = getSharedPreferences("myshare",MODE_PRIVATE);
                    //②获取editor对象
                    SharedPreferences.Editor editor = share.edit();
                    //③存储信息
                    editor.putString("account", account);
                    editor.putString("password", password);
                    //④执行提交操作
                    editor.commit();
                    //跳转到下一个界面
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else{ //2.2验证失败，提示用户
                    Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void initView() {
        startSettingActivity = findViewById(R.id.btn_3);
        startSettingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.enter(MainActivity.this);
            }
        });
    }
    public static void reStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
