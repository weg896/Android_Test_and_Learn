package com.jerry.fragmentanimation;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by test on 12/12/2015.
 */
public class OtherAdapterActivity extends ListActivity {

        // private List<String> data = new ArrayList<String>();
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listitem,
                    new String[]{"title","info","img"},
                    new int[]{R.id.title,R.id.info,R.id.img});
            setListAdapter(adapter);
        }

        private List<Map<String, Object>> getData() {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", "G1");
            map.put("info", "google 1");
            map.put("img", R.drawable.ic_action_info);
            list.add(map);

            map = new HashMap<String, Object>();
            map.put("title", "G2");
            map.put("info", "google 2");
            map.put("img", R.drawable.ic_action_photo);
            list.add(map);

            map = new HashMap<String, Object>();
            map.put("title", "G3");
            map.put("info", "google 3");
            map.put("img", R.drawable.ic_action_info);
            list.add(map);

            return list;
        }
    }