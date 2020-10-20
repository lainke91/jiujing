package com.jike.jiujing;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jike.jiujing.Adapter.NameAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupFragment extends Fragment {
    private List<String> nameList = new ArrayList<>();
    private Unbinder unbinder;

    @BindView(R.id.recycle_name)
    RecyclerView recyclerView;

    @BindView(R.id.button_start)
    Button btnStart;

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        unbinder = ButterKnife.bind(this, view);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),ContentActivity.class);
                getContext().startActivity(intent);
            }
        });
        initTeamMember();
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        NameAdapter nameAdapter = new NameAdapter(nameList);
        recyclerView.setAdapter(nameAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initTeamMember() {
        nameList.add("张三");
        nameList.add("李四");
        nameList.add("王老五");
        nameList.add("陈小花");
        nameList.add("赵老狗");
        nameList.add("何丢");
        nameList.add("谢草草");
        nameList.add("丁卯");


    }
}
