package com.example.thematthewpattel.actividad10;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    private final IBinder mBinder = new LocalService();

    @Nullable
    @Override

    public IBinder onBing(Intent intent) {
        return mBinder;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class LocalService extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    public static String sMensaje() {
        return "hola mundo";
    }

}
