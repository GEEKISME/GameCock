package com.biotag.gamecock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.biotag.gamecock.R;
import com.biotag.gamecock.common.Constants;
import com.biotag.gamecock.javabean.CockInfoBean;
import com.biotag.gamecock.manager.ThreadManager;
import com.biotag.gamecock.utiles.GetJson;
import com.biotag.gamecock.utiles.OkhttpPlusUtil;

import static com.biotag.gamecock.activity.LoginActivity.GETDATA_FAIL;
import static com.biotag.gamecock.activity.LoginActivity.GETDATA_SUCCESS;


public class CockDetailActivity extends AppCompatActivity {
    private RelativeLayout titlebar_cockdetail,rl_back,rl_cockgameinfo,rl_importerid;
    private RelativeLayout rl_basicInfo;
    private LinearLayout rl_layoutrefresh;
    private TextView refreshbutton;
    private TextView activity_title;
    private ProgressBar progressbar;
    private ImageView iv_cockImageview;
    private String chipNumber, serialNumber, ownerName,cockStatus;
    private Button btn_ok;
    private String cockId;
    private CockInfoBean cib;
    //=============================
    private TextView tv_chipnumber_record,tv_chipnumber_revu,tv_serialnumber_record,tv_serialnumber_revu
            ,tv_species,tv_species_revu,tv_birthday,tv_birthday_revu,tv_associationid,tv_associationid_revu;
    private TextView tv_farmplantid,tv_farmplantid_revu,tv_cockstatus,tv_cockstatus_revu,tv_cockstatusoneoftime,
            tv_cockstatusoneoftime_revu,tv_cockstatustwooftime,tv_cockstatustwooftime_revu,
            tv_cockstatusthreeoftime,tv_cockstatusthreeoftime_revu,tv_isforeigncock,tv_isforeigncock_revu,
            tv_importerid,tv_importerid_revu,tv_cockowner,tv_cockowner_revu;
    public static final String CHIPNUMBER = "chipnumber";
    public static final String SERIALNUMBER = "serialnumer";
    public static final String OWNERNAME = "ownnername";
    public static final String COCKSTATUS = "cockstatus";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GETDATA_SUCCESS:
                    setCockInfo();
                    showLoading(false);
                    break;
                case GETDATA_FAIL:
                    showLoading(false);
                    btn_ok.setVisibility(View.GONE);
                    rl_layoutrefresh.setVisibility(View.VISIBLE);

                    break;
            }
        }
    };

    private void setCockInfo() {
        CockInfoBean.ValuesBean vb = cib.getValues();
        tv_cockowner.setText("ownerName");tv_cockowner_revu.setText(vb.getOwnerName()); ownerName = vb.getOwnerName();
        tv_chipnumber_record.setText("chipCode");tv_chipnumber_revu.setText(vb.getChipCode()); chipNumber = vb.getChipCode();
        tv_serialnumber_record.setText("cockNo");tv_serialnumber_revu.setText(vb.getCockNo()); serialNumber = vb.getCockNo();
        tv_species.setText("breedID");tv_species_revu.setText(String.valueOf(vb.getBreedID()));
        tv_birthday.setText("birth");tv_birthday_revu.setText(vb.getBirth());
        tv_associationid.setText("orgID");tv_associationid_revu.setText(String.valueOf(vb.getOrgID()));
        tv_farmplantid.setText("farmID");tv_farmplantid_revu.setText(String.valueOf(vb.getFarmID()));
        tv_cockstatus.setText("cockStatus");tv_cockstatus_revu.setText(String.valueOf(vb.getCStatus())); cockStatus = String.valueOf(vb.getCStatus());
        tv_cockstatusoneoftime.setText("regDate1");tv_cockstatusoneoftime_revu.setText(vb.getRegDate1());
        tv_cockstatustwooftime.setText("regDate2");tv_cockstatustwooftime_revu.setText(vb.getRegDate2());
        tv_cockstatusthreeoftime.setText("regDate3");tv_cockstatusthreeoftime_revu.setText(vb.getRegDate3());
        tv_isforeigncock.setText("isImport");tv_isforeigncock_revu.setText(String.valueOf(vb.isIsImport()));
        if(!vb.isIsImport()){
            rl_importerid.setVisibility(View.GONE);
        }else {
            rl_importerid.setVisibility(View.VISIBLE);
            tv_importerid.setText("importerID");
            tv_importerid_revu.setText(String.valueOf(vb.getImporterID()));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cock_detail_1);
        Intent intent = getIntent();
        cockId = intent.getStringExtra("cockid");

        initview();
        initdata();
    }

    private void initdata() {
        showLoading(true);
        ThreadManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                String requestUrl = Constants.GET_COCK_INFO_URL;
                requestUrl = GetJson.replace(requestUrl,"{id}",cockId);
                cib = OkhttpPlusUtil.getInstance().get(requestUrl,CockInfoBean.class);
                if(cib!=null){
                    boolean isSuccessful = cib.isIsSuccess();
                    int status = cib.getStatus();
                    if(isSuccessful == true && status == 1){
                        handler.sendEmptyMessage(GETDATA_SUCCESS);
                    }else {
                        handler.sendEmptyMessage(GETDATA_FAIL);
                    }
                }
            }
        });
    }

    private void showLoading(boolean flag) {
        if(flag){
            progressbar.setVisibility(View.VISIBLE);
            iv_cockImageview.setVisibility(View.INVISIBLE);
            rl_basicInfo.setVisibility(View.INVISIBLE);
            btn_ok.setVisibility(View.INVISIBLE);
        }else {
            progressbar.setVisibility(View.INVISIBLE);
            iv_cockImageview.setVisibility(View.VISIBLE);
            rl_basicInfo.setVisibility(View.VISIBLE);
            btn_ok.setVisibility(View.VISIBLE);
        }
    }
    private void initview() {
        rl_layoutrefresh = (LinearLayout)findViewById(R.id.rl_layoutrefresh);
        refreshbutton = (TextView)findViewById(R.id.refreshbutton);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_layoutrefresh.setVisibility(View.GONE);
                initdata();
            }
        });
        rl_importerid  = (RelativeLayout)findViewById(R.id.rl_importerid);//斗鸡的进口人id，若不是进口鸡，则词布局gone。
        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        iv_cockImageview = (ImageView)findViewById(R.id.iv_cockImageview);
        rl_basicInfo = (RelativeLayout)findViewById(R.id.rl_basicInfo);
        titlebar_cockdetail = (RelativeLayout)findViewById(R.id.titlebar_cockdetail);
        rl_back = (RelativeLayout) titlebar_cockdetail.findViewById(R.id.rl_back);
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        activity_title = (TextView) titlebar_cockdetail.findViewById(R.id.activity_title);
        activity_title.setText(getResources().getString(R.string.cockdetailinfo));
        btn_ok = (Button)findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(CHIPNUMBER,chipNumber);
                bundle.putString(SERIALNUMBER,serialNumber);
                bundle.putString(OWNERNAME,ownerName);
                bundle.putString(COCKSTATUS,cockStatus);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        //=====================================================================================
        tv_chipnumber_record = (TextView)findViewById(R.id.tv_chipnumber_record);
        tv_chipnumber_revu = (TextView)findViewById(R.id.tv_chipnumber_revu);
        tv_serialnumber_record = (TextView)findViewById(R.id.tv_serialnumber_record);
        tv_serialnumber_revu = (TextView)findViewById(R.id.tv_serialnumber_revu);
        tv_species = (TextView)findViewById(R.id.tv_species);
        tv_species_revu = (TextView)findViewById(R.id.tv_species_revu);
        tv_birthday = (TextView)findViewById(R.id.tv_birthday);
        tv_birthday_revu = (TextView)findViewById(R.id.tv_birthday_revu);
        tv_associationid = (TextView)findViewById(R.id.tv_associationid);
        tv_associationid_revu = (TextView)findViewById(R.id.tv_associationid_revu);
        tv_farmplantid = (TextView)findViewById(R.id.tv_farmplantid);
        tv_farmplantid_revu = (TextView)findViewById(R.id.tv_farmplantid_revu);
        tv_cockstatus = (TextView)findViewById(R.id.tv_cockstatus);
        tv_cockstatus_revu = (TextView)findViewById(R.id.tv_cockstatus_revu);
        tv_cockstatusoneoftime = (TextView)findViewById(R.id.tv_cockstatusoneoftime);
        tv_cockstatusoneoftime_revu = (TextView)findViewById(R.id.tv_cockstatusoneoftime_revu);
        tv_cockstatustwooftime = (TextView)findViewById(R.id.tv_cockstatustwooftime);
        tv_cockstatustwooftime_revu = (TextView)findViewById(R.id.tv_cockstatustwooftime_revu);
        tv_cockstatusthreeoftime = (TextView)findViewById(R.id.tv_cockstatusthreeoftime);
        tv_cockstatusthreeoftime_revu = (TextView)findViewById(R.id.tv_cockstatusthreeoftime_revu);
        tv_isforeigncock = (TextView)findViewById(R.id.tv_isforeigncock);
        tv_isforeigncock_revu = (TextView)findViewById(R.id.tv_isforeigncock_revu);
        tv_importerid = (TextView)findViewById(R.id.tv_importerid);
        tv_importerid_revu = (TextView)findViewById(R.id.tv_importerid_revu);
        tv_cockowner = (TextView)findViewById(R.id.tv_cockowner);
        tv_cockowner_revu = (TextView)findViewById(R.id.tv_cockowner_revu);


    }
}
