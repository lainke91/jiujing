package com.jike.jiujing.signin;

import android.content.Context;
import android.widget.ListView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.Score;
import com.jike.jiujing.common.entry.SignIn;
import com.jike.jiujing.common.entry.SignInActivity;
import com.jike.jiujing.folding.FoldingCell;
import com.jike.jiujing.folding.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SigninAdapter extends CommonAdapter<SignInActivity> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();



    public SigninAdapter(Context context, List<SignInActivity> datas) {
        super(context, datas, R.layout.cell);
    }

    @Override
    public void convert(ViewHolder holder, SignInActivity data, int pos) {
        holder.setText(R.id.tv_title, data.getActivityName())
                .setText(R.id.tv_info, data.getActivityInfo())
                .setImageDrawable(R.id.iv_icon, data.getIcon())
                .setText(R.id.tv_time, data.getTime())
                .setText(R.id.tv_status, data.getActivityStatusCH())
                .setImageDrawable(R.id.tv_content_icon, data.getIcon())
                .setText(R.id.tv_content_status, "报名")
                .setText(R.id.tv_content_title, data.getActivityName())
                .setText(R.id.tv_content_desc, data.getActivityDesc());

        ListView listView = holder.getView(R.id.listView);
        FoldingCell cell = (FoldingCell)holder.getConvertView();
        listView.setAdapter(new ScoreAdapter(context, datas.get(pos).getMemberlist()));
        if (unfoldedIndexes.contains(pos)) {
            cell.unfold(true);
        } else {
            cell.fold(true);
        }
    }

    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position)) {
            unfoldedIndexes.remove(position);
        }
        else {
            unfoldedIndexes.add(position);
        }
    }
}