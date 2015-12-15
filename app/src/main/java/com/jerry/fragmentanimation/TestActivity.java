package com.jerry.fragmentanimation;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class TestActivity extends Activity {

    private TestService testService=null;
    private final static String TAG = "TEST_ACTIVITY";
    private Button testButton;
    private TestFragment testFragment;
    private CardFrontFragment cardFront;
    private CardBackFragment cardBack;

    private ServiceConnection testConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            testService = ((TestService.TestBinder)service).getService();
            Log.d(TAG, "onServiceConnected called ++ "+testService.random);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            testService = null;
            Log.d(TAG,"onServiceDisconnected called");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        testFragment = (TestFragment)getFragmentManager().findFragmentByTag("test_fragment");
        cardFront = (CardFrontFragment)getFragmentManager().findFragmentByTag("card_front_fragment");
        if(cardFront == null){
            Log.d(TAG, "card front fragment null--");
        }



        if(savedInstanceState == null){
            cardFront = new CardFrontFragment();
            cardBack = new CardBackFragment();
            getFragmentManager().beginTransaction().add(R.id.config_fragment, cardFront, "card_front_fragment").commit();

            Log.d(TAG, "onCreate--before bind ** " + this);


        }




        if(testFragment == null){
            Log.d(TAG, "test fragment null");
            testFragment = new TestFragment();

            getFragmentManager().beginTransaction().add(testFragment, "test_fragment");
            Intent intent = new Intent(getApplicationContext(), TestService.class);
            bindService(intent, testConnection, BIND_AUTO_CREATE);
            Log.d(TAG, "onCreate--after bind ** " + this);

        }

        testButton = (Button)findViewById(R.id.button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileTest();
            }
        });
    }

    private void fileTest(){
        String str = Environment.getExternalStorageDirectory().toString();
        Log.d(TAG, "sdcard dir: " + str);
        ArrayList<String> musicURLList = new ArrayList<String>();
        File file = new File(str);

        if(file.isDirectory()) {
            File list[] = file.listFiles(new MP3MusicFileFilter());
            if(list == null){
                return;
            }
            for (int i = 0; i < list.length; i++) {
                Log.d(TAG,str + "/" + list[i].getName());
            }
        }
    }

    private void rotationTest(){
        if (testService == null) {
            Log.d(TAG, " test service null");
        } else {
            Log.d(TAG, " test service ++ " + testService.random);
        }
        if(cardFront == null){
            Log.d(TAG,"card Front null");
        }else{
            cardFront.printRandom();
        }
        if(cardBack == null){
            Log.d(TAG,"card Back null");
        }else{
            cardBack.printRandom();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume ** " +this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause ** " +this);
    }

    @Override
    protected void onDestroy() {

        if(this.isChangingConfigurations()) {

        }else{
            unbindService(testConnection);
        }
        super.onDestroy();
        Log.d(TAG, "onDestroy ** " +this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart ** " +this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop ** " +this);
    }


}

