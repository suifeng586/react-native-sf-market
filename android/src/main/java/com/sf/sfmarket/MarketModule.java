package com.sf.sfmarket;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;
import java.util.List;

public class MarketModule extends ReactContextBaseJavaModule {
    Callback callback = null;
    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener(){
        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
            super.onActivityResult(activity, requestCode, resultCode, data);
            if(requestCode == 200){
                if(callback!=null){
                    try {
                        callback.invoke("0","success");
                    }catch (RuntimeException e){

                    }
                }
            }
        }
    };
    public MarketModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(mActivityEventListener);
    }

    @Override
    public String getName() {
        return "SFMarket";
    }

    @ReactMethod
    public void gotoMarket(Callback callback){
        this.callback = callback;
        try {
            Uri uri = Uri.parse("market://details?id=" + getReactApplicationContext().getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getCurrentActivity().startActivityForResult(intent,200);
        }catch (ActivityNotFoundException e){
            callback.invoke("-100","ActivityNotFoundException");
        }

    }
}
