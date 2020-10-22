package com.jike.jiujing.signin;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jike.jiujing.App;
import com.jike.jiujing.R;
import com.jike.jiujing.base.BaseFragment;
import com.jike.jiujing.common.entry.CaptainUser;
import com.jike.jiujing.common.entry.Member;
import com.jike.jiujing.common.entry.ResultData;
import com.jike.jiujing.common.entry.SignInActivity;
import com.jike.jiujing.common.entry.SignInActivityList;
import com.jike.jiujing.common.entry.SignInMember;
import com.jike.jiujing.common.event.EnergyEvent;
import com.jike.jiujing.common.param.ScoreParam;
import com.jike.jiujing.common.param.SignInParam;
import com.jike.jiujing.common.service.ApiLoader;
import com.jike.jiujing.common.service.Callback;
import com.jike.jiujing.folding.FoldingCell;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by sjgb8d on 2020/10/13
 */
public class SignInFragment extends BaseFragment implements ScreenShotable, SigninAdapter.OnSignInClickListener {

    private List<SignInActivity> dataList = new ArrayList<>();

    private View containerView;
    private Bitmap bitmap;

    @BindView(R.id.listView) ListView listView;

    private SignInActivityList data;
    private SigninAdapter adapter;

    public static SignInFragment newInstance() {
        SignInFragment signInFragment = new SignInFragment();
        return signInFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_signin;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container_signin);
    }

    @Override
    protected void initView() {
        super.initView();
        new ApiLoader().getActivity(App.getInstance().getUser().getTeamID()).subscribe(new Callback<ResultData<SignInActivityList>>(){
            @Override
            public void onSuccess (ResultData<SignInActivityList> result) {
                if(result.isSuccess()) {
                    data = result.getData();
                    initList();
                }
            }
        });
    }

    private void initList() {
        String[] ids = getResources().getStringArray(R.array.activity_id);
        String[] names = getResources().getStringArray(R.array.array_names);
        TypedArray imgs = getResources().obtainTypedArray(R.array.activity_img);
        String[] shorts = getResources().getStringArray(R.array.array_shorts);
        String[] longs = getResources().getStringArray(R.array.array_longs);
        String[] times = getResources().getStringArray(R.array.activity_time);
        int[] energys = getResources().getIntArray(R.array.activity_energy);
        int[] counts = getResources().getIntArray(R.array.activity_count);
        if (data.getActivitylist() != null) {
            for (SignInActivity item : data.getActivitylist()) {
                for (int i = 0; i < names.length; i++) {
                    if(item.getActivityID().equals(ids[i])) {
                        SignInActivity data = new SignInActivity();
                        data.setActivityID(ids[i]);
                        data.setIcon(imgs.getDrawable(i));
                        data.setActivityName(names[i]);
                        data.setActivityStatus(item.getActivityStatus());
                        data.setActivityInfo(shorts[i]);
                        data.setActivityDesc(longs[i]);
                        data.setTime(times[i]);
                        data.setEnergy(energys[i]);
                        data.setCount(counts[i]);
                        data.setMemberlist(item.getMemberlist());
                        int joinSize = 0;
                        for(SignInMember member : item.getMemberlist()) {
                            if(member.getTeamID().equals(App.getInstance().getUser().getTeamID())) {
                                joinSize++;
                            }
                        }
                        data.setJoinSize(joinSize);
                        dataList.add(data);
                    }
                }
            }
        }
        adapter = new SigninAdapter(currentContext, dataList);
        adapter.setOnSignInClickListener(this);
        listView.setAdapter(adapter);
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(View view, int pos){
        ((FoldingCell) view).toggle(false);
        adapter.registerToggle(pos);
    }

    @Override
    public void onSignInClick(final int pos) {
        final CaptainUser user = App.getInstance().getUser();
        final List<Member> memberList = user.getMember();
        MemberPopup memberPopup = new MemberPopup(currentContext, memberList)
                .setOnSubmitListener(new MemberPopup.OnSubmitListener() {
                    @Override
                    public void onSubmit(int memberIndex) {
                        SignInParam param = new SignInParam();
                        param.setActivityID(dataList.get(pos).getActivityID());
                        param.setEnergy(dataList.get(pos).getEnergy());
                        param.setTeamID(user.getTeamID());

                        Member selectMember = memberList.get(memberIndex);
                        final SignInMember signInMember = new SignInMember(selectMember.getMemberId());
                        signInMember.setMemberName(selectMember.getMemberName());
                        signInMember.setTeamID(user.getTeamID());
                        signInMember.setScore("");
                        List<SignInMember> teamList = new ArrayList<>();
                        teamList.add(signInMember);
                        param.setMemberList(teamList);
                        new ApiLoader().joinActivity(param).subscribe(new Callback<ResultData<CaptainUser>>(){
                            @Override
                            public void onSuccess (ResultData<CaptainUser> result) {
                                if(result.isSuccess()) {
                                    user.setTeamEnergy(result.getData().getTeamEnergy());
                                    App.getInstance().setUser(user);
                                    EventBus.getDefault().post(new EnergyEvent());

                                    int joinSize = dataList.get(pos).getJoinSize();
                                    joinSize++;
                                    dataList.get(pos).setJoinSize(joinSize);
                                    dataList.get(pos).getMemberlist().add(signInMember);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                });
        memberPopup.showBottom(0.8f);
    }

    @Override
    public void onScoreClick(int activityPos, int memberPos) {
        CaptainUser user = App.getInstance().getUser();
        if(!user.getTeamID().equals("0")) {
            return;
        }
        final SignInActivity activity = dataList.get(activityPos);
        final SignInMember signInMember = activity.getMemberlist().get(memberPos);
        if(!TextUtils.isEmpty(signInMember.getScore())){
            return;
        }
        ScoreDialog dialog = new ScoreDialog(currentContext);
        dialog.setMemberName(signInMember.getMemberName());
        dialog.setOnSubmitClickListener(new ScoreDialog.OnSubmitClickListener() {
            @Override
            public void onSubmitClick(final String value) {
                ScoreParam param = new ScoreParam();
                param.setActivityID(activity.getActivityID());
                List<SignInMember> memberList = new ArrayList<>();

                SignInMember tempMember = new SignInMember(signInMember.getMemberID());
                tempMember.setTeamID(signInMember.getTeamID());
                tempMember.setScore(value);
                tempMember.setMemberName(signInMember.getMemberName());
                memberList.add(tempMember);
                param.setMemberList(memberList);
                new ApiLoader().scoreActivity(param).subscribe(new Callback<ResultData>(){
                    @Override
                    public void onSuccess (ResultData result) {
                        if(result.isSuccess()) {
                            signInMember.setScore(value);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });
        dialog.show();
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                SignInFragment.this.bitmap = bitmap;
            }
        };

        thread.start();
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

}

