package com.biotag.gamecock.utiles;

import android.app.Activity;
import android.app.ProgressDialog;

import com.biotag.gamecock.R;

public class LoadDialog {
    private ProgressDialog loadingdialog;
    private String title;
    private Activity activity;

    public LoadDialog(String title, Activity activity) {
        this.title = title;
        this.activity = activity;
    }

    public void showProgressDialog() {
        try {
            if (this.activity != null) {
                loadingdialog = new ProgressDialog(this.activity);
                loadingdialog.setContentView(R.layout.progress_dialog);
                loadingdialog.setMessage(this.title);
                loadingdialog.setIndeterminate(true);
                loadingdialog.setCancelable(true);
                loadingdialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void closeProgressDialog() {
        try {
            if (loadingdialog != null) {
//			loadingdialog.hide();
                loadingdialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
