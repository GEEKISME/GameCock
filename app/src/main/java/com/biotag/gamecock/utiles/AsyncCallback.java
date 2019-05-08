package com.biotag.gamecock.utiles;

import android.content.Context;
import android.os.Message;

import java.util.Map;

public abstract class AsyncCallback {
	public abstract void callback(Context context, Map<String,Object> outputparameter, Message m);
}
