package com.biotag.gamecock.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.biotag.gamecock.R;
import com.biotag.gamecock.common.Constants;
import com.biotag.gamecock.javabean.LogininfoBean;
import com.biotag.gamecock.manager.ThreadManager;
import com.biotag.gamecock.utiles.GetJson;
import com.biotag.gamecock.utiles.OkhttpPlusUtil;
import com.biotag.gamecock.utiles.SharedPreferencesUtils;
import com.biotag.gamecock.utiles.TcUtiles;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int  GETDATA_SUCCESS = 1;
    public static final int  GETDATA_FAIL = 0;
    private LogininfoBean logininfoBean;
    private Button btn_login;
    private AutoCompleteTextView et_username,et_password;
    private Map<String,String> loginInfoMap = new HashMap();
    private ProgressBar mProgressbar;
    private Context context = this;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GETDATA_SUCCESS:
                    showLoading(false);
                    saveLoginInfo();
                    Toast.makeText(context, "信息已经写入成功", Toast.LENGTH_SHORT).show();
                    openScanningActvity();
                    finish();

                    break;
                case GETDATA_FAIL:
                    showLoading(false);
                    Toast.makeText(LoginActivity.this, "用户名密码错误", Toast.LENGTH_SHORT).show();

            }
            super.handleMessage(msg);

        }
    };

    private void saveLoginInfo() {
        LogininfoBean.ValuesBean  vb = logininfoBean.getValues();
        SharedPreferencesUtils.saveInt(context,"id",vb.getId());
        SharedPreferencesUtils.saveString(context,"ownerName",vb.getOwnerName());
        SharedPreferencesUtils.saveInt(context,"oTitle",vb.getOTitle());
        SharedPreferencesUtils.saveString(context,"oAddress",vb.getOAddress());
        SharedPreferencesUtils.saveString(context,"oTel",vb.getOTel());
        SharedPreferencesUtils.saveString(context,"oIDNo",vb.getOIDNo());
        SharedPreferencesUtils.saveString(context,"ownDate",vb.getOwnDate());
        SharedPreferencesUtils.saveBoolean(context,"isPersonal",vb.isIsPersonal());
        SharedPreferencesUtils.saveString(context,"username",vb.getUsername());
        SharedPreferencesUtils.saveString(context,"password",vb.getPassword());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        et_username = (AutoCompleteTextView)findViewById(R.id.et_username);
        et_password = (AutoCompleteTextView)findViewById(R.id.et_password);
        
        mProgressbar = (ProgressBar)findViewById(R.id.progressbar);
        testSharePreferenceExist();
    }

    private void testSharePreferenceExist() {
        String username  = SharedPreferencesUtils.getString(LoginActivity.this,"username","");
        String password = SharedPreferencesUtils.getString(LoginActivity.this,"password","");
        if (username!=null&&username.trim().length()!=0&&password!=null&&password.trim().length()!=0){
            et_username.setText(username);
            et_password.setText(password);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                showLoading(true);
                loginbypassword(et_username.getText().toString(),et_password.getText().toString());
                break;
        }
    }

    private void showLoading(boolean flag) {
        if(flag){
            mProgressbar.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.INVISIBLE);
            et_username.setEnabled(false);
            et_password.setEnabled(false);
        }else {
            mProgressbar.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
            et_username.setEnabled(true);
            et_password.setEnabled(true);
        }
    }


    private void loginbypassword(String username,String password) {
        if(username ==null || username.trim().length()==0||password ==null||password.trim().length()==0){
            Toast.makeText(this, "用户名密码不可以为空", Toast.LENGTH_SHORT).show();
            showLoading(false);
            return;
        }
        loginInfoMap.put("username",username);
        loginInfoMap.put("password",password);
        if (TcUtiles.isNetworkAvailable(this)) {
            showLoading(true);
            loginOperation(loginInfoMap);
        }else {
            Toast.makeText(this, "当前无网络连接", Toast.LENGTH_SHORT).show();
            showLoading(false);
        }
    }

    private void loginOperation(final Map<String,String> map) {
        ThreadManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                String login_url = Constants.USR_LOGIN_URL;
                login_url = GetJson.replace(login_url,"{username}",map.get("username"));
                login_url = GetJson.replace(login_url,"{password}",map.get("password"));
                logininfoBean = OkhttpPlusUtil.getInstance().get(login_url,LogininfoBean.class);
                boolean isSuccessful = logininfoBean.isIsSuccess();
                int status = logininfoBean.getStatus();
                if(isSuccessful==true && status == 1){
                    handler.sendEmptyMessage(GETDATA_SUCCESS);
                }else {
                    handler.sendEmptyMessage(GETDATA_FAIL);
                }
            }
        });

//        openScanningActvity();
    }

    private void openScanningActvity() {
        Intent intent = new Intent(this,ScanningActivity.class);
        startActivity(intent);
    }
}
