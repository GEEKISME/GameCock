package com.biotag.gamecock.utiles;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.biotag.gamecock.R;

import java.util.HashMap;
import java.util.Map;

public class Asynchronous {
    private Context context;
    private String loadingMsg;
    private LoadDialog loadDialog;
    private long validDataThreadId = -1;
    private AsyncProcess         asyncProcess;
    private AsyncCallback        asyncCallback;
    private AsyncExceptionHandle asyncExceptionHandle;
    private Map<String, Object> inputparameter = new HashMap<String, Object>();

    public Asynchronous(Context context) {
        this.context = context;
    }

    private Dialog dialog;

    public void setLoadingMsg(String loadingMsg) {
        this.loadingMsg = loadingMsg;
    }

    public void setInputParameter(String key, Object obj) {
        this.inputparameter.put(key, obj);
    }

    public void asyncExceptionHandle(AsyncExceptionHandle asyncExceptionHandle) {
        if (asyncExceptionHandle == null) {
            return;
        }
        this.asyncExceptionHandle = asyncExceptionHandle;
    }

    public void asyncProcess(AsyncProcess asyncProcess) {
        if (asyncProcess == null) {
            return;
        }
        this.asyncProcess = asyncProcess;
    }

    public void asyncCallback(AsyncCallback asyncCallback) {
        if (asyncCallback == null) {
            return;
        }
        this.asyncCallback = asyncCallback;
    }

    public void start() {
        if (this.asyncCallback == null || this.asyncProcess == null) {
            return;
        }
        if (this.loadingMsg != null) {

            if (this.context instanceof Activity) {
                Activity activity = (Activity) this.context;
                dialog = new Dialog(activity);
                dialog.setContentView(R.layout.progress_dialog);
                TextView progresstitle = (TextView) dialog.findViewById(R.id.progresstitle);
                progresstitle.setText(loadingMsg);

                if (!activity.isFinishing()) dialog.show();

            }

        }
        Thread t = new Thread() {
            @Override
            public void run() {

                if (validDataThreadId != getId()) {
                    return;
                }
                Map<String, Object> outputparameter = null;
                int what = 0;
                try {
                    Activity activity = null;
                    if (context instanceof Activity) {
                        activity = (Activity) context;
                    }
                    what = asyncProcess.process(activity, inputparameter);
                    outputparameter = asyncProcess.getOutputparameter();
                } catch (Exception e) {
                    e.printStackTrace();
                    Message m = Message.obtain(exceptionHandler);
                    m.obj = e;
                    m.what = what;
                    m.sendToTarget();
                    return;
                }
                Message m = Message.obtain(commoneHandler);
                m.obj = outputparameter;
                m.what = what;
                m.sendToTarget();
            }
        };
        validDataThreadId = t.getId();
        t.start();
    }

    Handler exceptionHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (loadingMsg != null) {
                dialog.dismiss();
            }
            Exception e = (Exception) msg.obj;
            if (asyncExceptionHandle == null) {
                return;
            }
            try {
                Activity activity = null;
                if (context instanceof Activity) {
                    activity = (Activity) context;
                }
                asyncExceptionHandle.handle(activity, e, msg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };
    Handler commoneHandler   = new Handler() {
        public void handleMessage(Message msg) {
            try {
                if (loadingMsg != null && null != dialog && dialog.isShowing()) {
                    dialog.dismiss();
                }
                Map<String, Object> outputparameter = (Map<String, Object>) msg.obj;
                if (asyncCallback == null || asyncProcess == null) {
                    return;
                }
                Activity activity = null;
                if (context instanceof Activity) {
                    activity = (Activity) context;
                }
                asyncCallback.callback(activity, outputparameter, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.handleMessage(msg);
        }
    };
}
