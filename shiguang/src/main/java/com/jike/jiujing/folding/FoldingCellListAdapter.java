package com.jike.jiujing.folding;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.entry.Score;
import com.jike.jiujing.signin.ScoreAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Simple example of ListAdapter for using with Folding Cell
 * Adapter holds indexes of unfolded elements for correct work with default reusable views behavior
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class FoldingCellListAdapter extends ArrayAdapter<Item> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();

    private List<Score> scoreList = new ArrayList<>();

    public FoldingCellListAdapter(Context context, List<Item> objects) {
        super(context, 0, objects);
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

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Item item = getItem(position);
        FoldingCell cell = (FoldingCell) convertView;
        ViewHolder viewHolder;
        if (cell == null) {
            viewHolder = new ViewHolder();
            LayoutInflater vi = LayoutInflater.from(getContext());
            cell = (FoldingCell) vi.inflate(R.layout.cell, parent, false);
            // binding view parts to view holder
            viewHolder.ivIcon = cell.findViewById(R.id.iv_icon);
            viewHolder.listView = cell.findViewById(R.id.listView);
            cell.setTag(viewHolder);
        } else {
            // for existing cell set valid valid state(without animation)
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
            viewHolder = (ViewHolder) cell.getTag();
        }
        if (null == item)
            return cell;
        viewHolder.listView.setAdapter(new ScoreAdapter(getContext(), scoreList));
        return cell;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position)) {
            unfoldedIndexes.remove(position);
        }
        else {
            unfoldedIndexes.add(position);
        }
    }

    private static class ViewHolder {
        ImageView ivIcon;
        ListView listView;
    }
}
