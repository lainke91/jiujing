package com.jike.jiujing.signin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jike.jiujing.Adapter.TaskAdapter;
import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.Score;

import java.util.List;

public class ScoreAdapter extends CommonAdapter<Score> {

    public ScoreAdapter(Context context, List<Score> datas) {
        super(context, datas, R.layout.item_score);
    }

    @Override
    public void convert(ViewHolder holder, Score score, int pos) {
        holder.setText(R.id.tv_name, score.getName())
                .setText(R.id.tv_score, String.valueOf(score.getScore()));
    }
}
