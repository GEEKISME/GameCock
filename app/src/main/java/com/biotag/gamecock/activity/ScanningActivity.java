package com.biotag.gamecock.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.biotag.gamecock.R;
import com.biotag.gamecock.manager.ThreadManager;
import com.biotag.gamecock.utiles.CheckPermissionUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.biotag.gamecock.activity.CockDetailActivity.CHIPNUMBER;
import static com.biotag.gamecock.activity.CockDetailActivity.COCKSTATUS;
import static com.biotag.gamecock.activity.CockDetailActivity.OWNERNAME;
import static com.biotag.gamecock.activity.CockDetailActivity.SERIALNUMBER;

public class ScanningActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {


    /**
     * 扫描跳转Activity RequestCode  请求码
     */
    public static final int REQUEST_CODE = 111;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    /**
     *用来存储将要扫描的是哪只斗鸡的二维码
     */
    /**
     * 请求码，表示是哪只斗鸡的请求码
     */
    public static final int REQUEST_COCK_A = 102;
    public static final int REQUEST_COCK_B = 103;
    public static final String COCKBOTH = "cockboth";

    private RelativeLayout rl_cockabarcode,rl_cockbbarcode;
    private RelativeLayout titlebar_cockgame,rl_back,rl_nextcompetition;
    private TextView activity_title,tv_nextcompetition;
    private Button startornot;
    private String chipNumber_cocka, serialNumber_cocka, ownerName_cocka, cockStatus_cocka;
    private String chipNumber_cockb, serialNumber_cockb, ownerName_cockb, cockStatus_cockb;
    private RelativeLayout cockainfo,cockbinfo;
    private ImageView cockaimg,cockbimg;
    private TextView tv_cna, tv_cna_value,tv_sna,tv_sna_value,tv_ona,tv_ona_value,tv_csa,tv_csa_value;
    private TextView tv_cnb, tv_cnb_value,tv_snb,tv_snb_value,tv_onb,tv_onb_value,tv_csb,tv_csb_value;

    public static final int POST_GAMEINFO_SUCCESS = 1;
    public static final int POST_GAMEINFO_FAIL    = 0;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case POST_GAMEINFO_SUCCESS:
                    Toast.makeText(ScanningActivity.this, getResources().getString(R.string.uds), Toast.LENGTH_SHORT).show();
                    rl_nextcompetition.setVisibility(View.VISIBLE);
                    break;
                case POST_GAMEINFO_FAIL:
                    break;
            }
        }
    };
    /**
     * EsayPermissions接管权限处理逻辑
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            onClick(viewId);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    private void onClick(int viewId) {
        if(viewId==R.id.rl_cockabarcode){
            Intent intent = new Intent(getApplication(),SecondActivity.class );//打开二维码扫描页面
            intent.putExtra(COCKBOTH,"a");
            startActivityForResult(intent, REQUEST_CODE);
        }
        if(viewId==R.id.rl_cockbbarcode){
            Intent intent = new Intent(getApplication(),SecondActivity.class );//打开二维码扫描页面
            intent.putExtra(COCKBOTH,"b");
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE){
            if(data!=null){
                Bundle bundle = data.getExtras();
                if(bundle==null){
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_SUCCESS) {
                    String cockSide = bundle.getString(COCKBOTH);
                    if(cockSide.equals("a")){
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "解析的结果："+result, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ScanningActivity.this,CockDetailActivity.class);
                        intent.putExtra("cockid",result);
                        startActivityForResult(intent,REQUEST_COCK_A);
                    }else if(cockSide.equals("b")){
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(this, "解析的结果："+result, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ScanningActivity.this,CockDetailActivity.class);
                        intent.putExtra("cockid",result);
                        startActivityForResult(intent,REQUEST_COCK_B);
                    }

                }else if(bundle.getInt(CodeUtils.RESULT_TYPE)==CodeUtils.RESULT_FAILED){
                    Toast.makeText(this, "解析二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(requestCode==REQUEST_CAMERA_PERM){
            Toast.makeText(this, "从设置页面返回...", Toast.LENGTH_SHORT).show();
        }
        //===================================================================================
        if(requestCode == REQUEST_COCK_A && resultCode == RESULT_OK){
            if(data!=null){
                Bundle bundle = data.getExtras();
                if(bundle==null){
                    return;
                }
                chipNumber_cocka = bundle.getString(CHIPNUMBER);
                serialNumber_cocka = bundle.getString(SERIALNUMBER);
                ownerName_cocka = bundle.getString(OWNERNAME);
                cockStatus_cocka = bundle.getString(COCKSTATUS);
                cockainfo.setVisibility(View.VISIBLE);
                tv_cna_value.setText(chipNumber_cocka);
                tv_sna_value.setText(serialNumber_cocka);
                tv_ona_value.setText(ownerName_cocka);
                if(cockStatus_cocka.equals("1")){
                    tv_csa_value.setText(getResources().getString(R.string.normal));
                }else if(cockStatus_cocka.equals("2")){
                    tv_csa_value.setText(getResources().getString(R.string.elimination));
                }else {
                    tv_csa_value.setText(getResources().getString(R.string.missing));
                }
            }
        }
        if(requestCode == REQUEST_COCK_B && resultCode == RESULT_OK){
            if(data!=null){
                Bundle bundle = data.getExtras();
                if(bundle==null){
                    return;
                }
                chipNumber_cockb = bundle.getString(CHIPNUMBER);
                serialNumber_cockb = bundle.getString(SERIALNUMBER);
                ownerName_cockb = bundle.getString(OWNERNAME);
                cockStatus_cockb= bundle.getString(COCKSTATUS);
                cockbinfo.setVisibility(View.VISIBLE);
                tv_cnb_value.setText(chipNumber_cockb);
                tv_snb_value.setText(serialNumber_cockb);
                tv_onb_value.setText(ownerName_cockb);
                if(cockStatus_cockb.equals("1")){
                    tv_csb_value.setText(getResources().getString(R.string.normal));
                }else if(cockStatus_cockb.equals("2")){
                    tv_csb_value.setText(getResources().getString(R.string.elimination));
                }else {
                    tv_csb_value.setText(getResources().getString(R.string.missing));
                }
            }
        }
    }

    //=========================================================================================
    /*  下面这两个方法是EasyPermission.permissioncallback 将会回调的方法*/
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
                    .setTitle("权限申请")
                    .setPositiveButton("确认")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(REQUEST_CAMERA_PERM)
                    .build()
                    .show();
        }
    }

    //=========================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);

        initView();
        initPermission();
    }

    private void initPermission() {
        String[] permissions  = CheckPermissionUtils.checkPermission(this);
        if(permissions.length==0){

        }else {
            ActivityCompat.requestPermissions(this,permissions,100);
        }
    }

    private void initView() {
        //=====================================================titlebar
        titlebar_cockgame = (RelativeLayout)findViewById(R.id.titlebar_cockgome);
        rl_back = (RelativeLayout)titlebar_cockgame.findViewById(R.id.rl_back);
        rl_nextcompetition = (RelativeLayout)titlebar_cockgame.findViewById(R.id.rl_nextcompetition);
        rl_nextcompetition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cockainfo.setVisibility(View.GONE);
                cockbinfo.setVisibility(View.GONE);
            }
        });
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activity_title = (TextView)titlebar_cockgame.findViewById(R.id.activity_title);
        activity_title.setText(getResources().getString(R.string.cockavscockb));

        //====================================================================两个二维码
        rl_cockabarcode = (RelativeLayout)findViewById(R.id.rl_cockabarcode);
        rl_cockbbarcode = (RelativeLayout)findViewById(R.id.rl_cockbbarcode);

        rl_cockabarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraTask(v.getId());
            }
        });
        rl_cockbbarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraTask(v.getId());
            }
        });
        //===================红色的start 按钮
        startornot = (Button)findViewById(R.id.startornot);
        startornot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过检测cockainfo与cockbinfo 同时处于Visible状态，则开始上传比赛信息
                if(cockainfo.getVisibility()==View.VISIBLE&&cockbinfo.getVisibility()==View.VISIBLE){
                    if(!cockStatus_cocka.equals("1") || !cockStatus_cockb.equals("1")){
                        Toast.makeText(ScanningActivity.this, getResources().getString(R.string.toastsomecockstatusiswrong),
                                Toast.LENGTH_SHORT).show();
                        rl_nextcompetition.setVisibility(View.VISIBLE);
                    }else {
                        Toast.makeText(ScanningActivity.this, "开始上传比赛数据", Toast.LENGTH_SHORT).show();
                        ThreadManager.getInstance().execute(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                }else {
                    Toast.makeText(ScanningActivity.this, "还有斗鸡没有录入", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        //=====================
        cockainfo = (RelativeLayout)findViewById(R.id.cockainfo);
        cockbinfo = (RelativeLayout)findViewById(R.id.cockbinfo);
        cockaimg = (ImageView)findViewById(R.id.cockaimg);
        cockbimg = (ImageView)findViewById(R.id.cockbimg);
        tv_cna = (TextView)findViewById(R.id.tv_cna);
        tv_cna_value = (TextView)findViewById(R.id.tv_cna_value);
        tv_sna = (TextView)findViewById(R.id.tv_sna);
        tv_sna_value = (TextView)findViewById(R.id.tv_sna_value);
        tv_ona = (TextView)findViewById(R.id.tv_cna);
        tv_ona_value = (TextView)findViewById(R.id.tv_ona_value);
        tv_cnb = (TextView)findViewById(R.id.tv_cnb);
        tv_cnb_value = (TextView)findViewById(R.id.tv_cnb_value);
        tv_snb = (TextView)findViewById(R.id.tv_snb);
        tv_snb_value = (TextView)findViewById(R.id.tv_snb_value);
        tv_onb = (TextView)findViewById(R.id.tv_onb);
        tv_onb_value = (TextView)findViewById(R.id.tv_onb_value);
        tv_csa = (TextView)findViewById(R.id.tv_csa);
        tv_csa_value = (TextView)findViewById(R.id.tv_csa_value);
        tv_csb = (TextView)findViewById(R.id.tv_csb);
        tv_csb_value = (TextView)findViewById(R.id.tv_csb_value);
    }





}
