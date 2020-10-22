package com.jike.jiujing.signin;

import android.content.Context;

import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.Score;
import com.jike.jiujing.common.entry.SignInMember;

import java.util.List;

public class ScoreAdapter extends CommonAdapter<SignInMember> {

    public ScoreAdapter(Context context, List<SignInMember> datas) {
        super(context, datas, R.layout.item_score);
    }

    @Override
    public void convert(ViewHolder holder, SignInMember data, int pos) {
        holder.setText(R.id.tv_name, data.getMemberName())
                .setText(R.id.tv_score, data.getScore());
    }
}
