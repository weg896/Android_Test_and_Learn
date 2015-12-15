package com.jerry.fragmentanimation;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by test on 12/8/2015.
 */
public class TestFragment extends Fragment {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
}
