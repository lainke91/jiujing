package com.jike.jiujing.signin;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.SignInActivity;
import com.jike.jiujing.folding.FoldingCell;

import java.util.HashSet;
import java.util.List;

public class SigninAdapter extends CommonAdapter<SignInActivity> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    public interface OnSignInClickListener {
        void onSignInClick(int pos);
        void onScoreClick(int activityPos, int memberPos);
    }
    private OnSignInClickListener onSignInClickListener;

    public void setOnSignInClickListener(OnSignInClickListener onSignInClickListener) {
        this.onSignInClickListener = onSignInClickListener;
    }

    public SigninAdapter(Context context, List<SignInActivity> datas) {
        super(context, datas, R.layout.cell);
    }

    @Override
    public void convert(ViewHolder holder, SignInActivity data, final int pos) {
        holder.setText(R.id.tv_title, data.getActivityName())
                .setText(R.id.tv_info, data.getActivityInfo())
                .setImageDrawable(R.id.iv_icon, data.getIcon())
                .setText(R.id.tv_time, data.getTime())
                .setText(R.id.tv_status, data.getActivityStatusCH())
                .setImageDrawable(R.id.tv_content_icon, data.getIcon())
                .setText(R.id.tv_content_title, data.getActivityName())
                .setText(R.id.tv_content_desc, data.getActivityDesc());

        TextView tvSignIn = holder.getView(R.id.tv_content_signin);
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onSignInClickListener != null) {
                    onSignInClickListener.onSignInClick(pos);
                }
            }
        });
        if(data.getJoinSize() >= data.getCount()) {
            tvSignIn.setVisibility(View.GONE);
        } else {
            tvSignIn.setVisibility(View.VISIBLE);
        }
        ListView listView = holder.getView(R.id.listView);
        FoldingCell cell = (FoldingCell)holder.getConvertView();
        listView.setAdapter(new ScoreAdapter(context, datas.get(pos).getMemberlist()));
        if (unfoldedIndexes.contains(pos)) {
            cell.unfold(true);
        } else {
            cell.fold(true);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(onSignInClickListener != null){
                    onSignInClickListener.onScoreClick(pos, position);
                }
            }
        });
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