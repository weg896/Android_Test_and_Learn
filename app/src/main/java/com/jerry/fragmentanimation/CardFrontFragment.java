package com.jerry.fragmentanimation;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by test on 12/7/2015.
 */
public class CardFrontFragment extends Fragment {
    private String TAG = "TEST_FRONT "+this;
    private double fragRand;
    private View myview=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        Log.d(TAG, "onCreateView");
        myview = inflater.inflate(R.layout.card_image, container, false);
        return myview;
    }
    public void onCreate(Bundle savedInstanceState){
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }
    public void onStart(){
        Log.d(TAG, "onStart");
        super.onStart();
    }
    public void onStop(){
        Log.d(TAG, "onStop");
        super.onStop();
    }
    public void onResume(){
        Log.d(TAG,"onResume");
        super.onResume();
    }
    public void onPause(){
        Log.d(TAG, "onPause");
        super.onPause();
    }
    public void onDestroy(){
        if(myview == null){
            Log.d(TAG,"destroy after view null");
        }
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
    public void onDestroyView(){
        if(myview == null){
            Log.d(TAG,"destroy before view null");
        }
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();

    }
    public void onActivityCreate(Bundle savedInstanceState){
        Log.d(TAG, "onActivityCreate");
        super.onActivityCreated(savedInstanceState);
    }

    ///////////////////////////////////////////////////////
    /*public CardFrontFragment(){
        super();
        fragRand=Math.random();
        Log.d(TAG,"new random -- "+fragRand);
    }*/

    public void printRandom(){
        Log.d(TAG,"random -- "+this);
    }
}
