package com.biotag.gamecock.utiles;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;


public abstract class AsyncProcess {
	private Map<String,Object> outputparameter = new HashMap<String,Object>();
	protected void setCallbackParameter(String key, Object obj){
		this.outputparameter.put(key, obj);
	}
	public abstract int process(Context context, Map<String,Object> inputparameter) throws Exception;
	public Map<String, Object> getOutputparameter() {
		return outputparameter;
	}
	
}
