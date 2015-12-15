package com.jerry.fragmentanimation;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by test on 12/6/2015.
 */
public class CardFlipActivity extends Activity implements FragmentManager.OnBackStackChangedListener{

    private Handler mHandler = new Handler();
    private boolean mShowingBack = false;
    private CardFrontFragment cff;
    private CardBackFragment cbf;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_card_flip);
        Log.d("duu", "duu");

        cff = (CardFrontFragment)getFragmentManager().findFragmentByTag("cff");
        cbf = (CardBackFragment)getFragmentManager().findFragmentByTag("cbf");

        if(cff == null){
            cff = new CardFrontFragment();
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,cff,"cff")
                    .commit();
        }
        if(cbf == null){
            cbf = new CardBackFragment();
        }
        mShowingBack = (getFragmentManager().getBackStackEntryCount() >0);


        getFragmentManager().addOnBackStackChangedListener(this);
    }



    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        MenuItem item=menu.add(Menu.NONE, R.id.action_flip, Menu.NONE,
                mShowingBack
                        ?R.string.action_photo
                        :R.string.action_info);
        item.setIcon(mShowingBack
                ?R.drawable.ic_action_photo
                :R.drawable.ic_action_info);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, null));
                return true;
            case R.id.action_flip:
                flipCard();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onBackStackChanged(){
        mShowingBack = (getFragmentManager().getBackStackEntryCount() >0);
        Log.d("CARD_","onBackStackChanged()");
        invalidateOptionsMenu();
    }

    private void flipCard(){
        if(mShowingBack){
            getFragmentManager().popBackStack();
            return;
        }

        mShowingBack = true;

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                .replace(R.id.container,cbf,"cbf")
                .addToBackStack(null)
                .commit();
        mHandler.post(new Runnable(){
            public void run(){invalidateOptionsMenu();}
        });
    }


    public void onResume(){
        super.onResume();
    }
}
