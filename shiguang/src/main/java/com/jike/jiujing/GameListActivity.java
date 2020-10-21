package com.jike.jiujing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.jike.jiujing.R;
import com.jike.jiujing.euclid.EuclidActivity;
import com.jike.jiujing.euclid.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameListActivity extends EuclidActivity {

    public static final String PARAM_TITLE = "title";

    public static void go(Context context, String title) {
        Intent intent = new Intent(context, GameListActivity.class);
        intent.putExtra(PARAM_TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseAdapter getAdapter() {
        Map<String, Object> profileMap;
        List<Map<String, Object>> profilesList = new ArrayList<>();

        int[] avatars = {
                R.drawable.game1,
                R.drawable.game2,
                R.drawable.game3,
                R.drawable.game4,
                R.drawable.game5,
                R.drawable.game6,
                R.drawable.game7,
              };
        String[] names = getResources().getStringArray(R.array.array_names);
        String[] shorts=getResources().getStringArray(R.array.array_shorts);
        String[] longs=getResources().getStringArray(R.array.array_longs);

        for (int i = 0; i < avatars.length; i++) {
            profileMap = new HashMap<>();
            profileMap.put(EuclidListAdapter.KEY_AVATAR, avatars[i]);
            profileMap.put(EuclidListAdapter.KEY_NAME, names[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_SHORT, shorts[i]);
            profileMap.put(EuclidListAdapter.KEY_DESCRIPTION_FULL, longs[i]);
            profilesList.add(profileMap);
        }

        return new EuclidListAdapter(this, R.layout.euclid_list_item, profilesList);
    }

}

