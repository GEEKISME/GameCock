package com.biotag.gamecock.utiles;

import android.content.Context;
import android.os.Message;

public abstract class AsyncExceptionHandle {
	public abstract void handle(Context context, Exception e, Message m);
}
