package com.jike.jiujing.signin;

import android.content.Context;
import android.widget.ListView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.Score;
import com.jike.jiujing.folding.FoldingCell;
import com.jike.jiujing.folding.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SigninAdapter extends CommonAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private List<Score> scoreList = new ArrayList<>();


    public SigninAdapter(Context context, List<Item> datas) {
        super(context, datas, R.layout.cell);
        scoreList.add(new Score("A", 100));
        scoreList.add(new Score("B", 100));
        scoreList.add(new Score("C", 100));
        scoreList.add(new Score("D", 100));
        scoreList.add(new Score("E", 100));
        scoreList.add(new Score("F", 100));
        scoreList.add(new Score("G", 100));
        scoreList.add(new Score("H", 100));
        scoreList.add(new Score("I", 100));
        scoreList.add(new Score("J", 100));
    }

    @Override
    public void convert(ViewHolder holder, Item data, int pos) {
        ListView listView = holder.getView(R.id.listView);
        FoldingCell cell = (FoldingCell)holder.getConvertView();
        listView.setAdapter(new ScoreAdapter(context, scoreList));
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