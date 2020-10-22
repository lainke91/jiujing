package com.jike.jiujing.signin;

import android.content.Context;
import android.widget.ListView;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.jike.jiujing.common.adapter.CommonAdapter;
import com.jike.jiujing.common.adapter.ViewHolder;
import com.jike.jiujing.common.entry.Member;
import com.jike.jiujing.common.view.BasePopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MemberPopup extends BasePopup {

    private PersonAdapter adapter;


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.listView)
    ListView listView;

    private List<Member> dataList = new ArrayList<>();

    public interface OnSubmitListener {
        void onSubmit(int pos);
    }

    private OnSubmitListener onSubmitListener;
    public MemberPopup setOnSubmitListener(OnSubmitListener onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
        return this;
    }

    public MemberPopup(Context context, List<Member> dataList) {
        super(context, R.layout.popup_member);
        ButterKnife.bind(this, view);
        adapter = new PersonAdapter(context, dataList);
        listView.setAdapter(adapter);
    }

//    @OnClick(R.id.iv_close)
//    public void onCloseClick() {
//        dismiss();
//    }

    @OnItemClick(R.id.listView)
    public void onItemClick(int pos) {
        if (onSubmitListener != null) {
            onSubmitListener.onSubmit(pos);
        }
        dismiss();
    }

    public class PersonAdapter extends CommonAdapter<Member> {

        public PersonAdapter(Context context, List<Member> datas) {
            super(context, datas, R.layout.item_member);
        }

        @Override
        public void convert(ViewHolder holder, Member data, int pos) {
            holder.setText(R.id.tv_name,  data.getMemberName());

        }
    }
}
