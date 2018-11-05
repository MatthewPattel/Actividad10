package com.example.thematthewpattel.actividad10;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.midi.MidiDevice;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvTextView;
    MyService msMyservice;
    boolean isBind = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTextView = (TextView) findViewById(R.id.iTextView);
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, Mconnection, Context.BIND_AUTO_CREATE);
    }

    public void mensaje(View view) {
        String message;
        message = MyService.sMensaje();
        tvTextView.setText(message);
    }

    private ServiceConnection Mconnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            msMyservice.LocalService localService = (MyService.LocalService)service;
            msMyservice = localService.getService();
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
        }
    };

    protected void onStop() {
        super.onStop();
        if (isBind) {
            unbindService(Mconnection);
            isBind = false;
        }
    }

}
