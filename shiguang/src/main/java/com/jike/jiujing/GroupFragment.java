package com.jike.jiujing;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import com.jike.jiujing.Adapter.NameAdapter;
import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.Member;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.service.ApiLoader;
import com.jike.jiujing.common.service.Callback;
import com.jike.jiujing.common.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GroupFragment extends BaseFragment {
    private List<Member> memberList = new ArrayList<>();

    @BindView(R.id.tv_team_name)
    TextView tvTeamName;
    @BindView(R.id.recycle_name)
    RecyclerView recyclerView;

    @BindView(R.id.button_start)
    Button btnStart;

    public static GroupFragment newInstance() {
        return new GroupFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_group;
    }

    @Override
    protected void initView() {
        super.initView();
        CaptainUser captainUser = (CaptainUser)SPUtils.getObjectValue(currentContext, SPUtils.SP_LOGIN_DATA, CaptainUser.class);
        if("0".equals(captainUser.getTeamID())) {
            getActivity().finish();
            startActivity(ContentActivity.class);
        }
        if(!TextUtils.isEmpty(captainUser.getTeamName())){
            tvTeamName.setText(captainUser.getTeamName());
        }
        memberList = captainUser.getMember();
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        NameAdapter nameAdapter = new NameAdapter(memberList);
        recyclerView.setAdapter(nameAdapter);
    }

    @OnClick(R.id.button_start)
    public void onStartClick() {
      final CaptainUser user =  App.getInstance().getUser();
      if(TextUtils.isEmpty(user.getTeamName())) {
          new NameDialog(currentContext)
                  .setOnSubmitClickListener(new NameDialog.OnSubmitClickListener() {
                      @Override
                      public void onSubmitClick(String value) {
                          changeTeamName(user, value);
                      }
                  })
                  .show();
      } else {
          startActivity(ContentActivity.class);
      }
    }

    public void changeTeamName(final CaptainUser user, final String teamName) {
        user.setTeamName(teamName);
        new ApiLoader().changeTeamName(user).subscribe(new Callback<ResultData>(){
            @Override
            public void onSuccess (ResultData result) {
                if(result.isSuccess()) {
                    App.getInstance().setUser(user);
                    tvTeamName.setText(teamName);
                    startActivity(ContentActivity.class);
                }
            }
        });
    }
}
