package com.jike.jiujing.signin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jike.jiujing.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class SigninAdapter extends SelectableAdapter<SigninAdapter.TicketsViewHolder> {

    public interface ListListener {
        void onListItemClick(final int position);
        boolean onListItemLongClick(final int position);
    }


    private Context mContext;
    private List<String> mTickets;
    private ListListener listListener;

    public void setListListener(ListListener listListener) {
        this.listListener = listListener;
    }

    public SigninAdapter(final Context context, final List<String> tickets) {
        mContext = context;
        mTickets = tickets;
    }

    public List<String> getTickets() {
        return mTickets;
    }

    public void addTickets(List<String> tickets) {
        mTickets.addAll(tickets);
        notifyItemRangeInserted(mTickets.size() - tickets.size(), tickets.size());
    }

    public void clear() {
        int itemCount = getItemCount();
        mTickets.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    @Override
    public TicketsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new TicketsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_signin, parent, false));
    }

    @Override
    public void onBindViewHolder(final TicketsViewHolder holder, final int position) {
        holder.bindData(mTickets.get(position));
        holder.relativeLayout.setTag(position);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listListener != null) {
                    listListener.onListItemClick((int) v.getTag());
                }
            }
        });
        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if(listListener != null) {
                    return listListener.onListItemLongClick((int) view.getTag());
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(TicketsViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public int getItemCount() {
        return mTickets.size();
    }

    public class TicketsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTitle;
        private TextView mTextViewLikes;
        private TextView mTextViewAddress;
        private TextView mTextViewDueDate;
        private ImageView mImageViewCategory;
        private RelativeLayout relativeLayout;

        public TicketsViewHolder(final View itemView) {
            super(itemView);
            mTextViewAddress = (TextView) itemView.findViewById(R.id.txt_address);
            mTextViewLikes = (TextView) itemView.findViewById(R.id.txt_likes);
            mTextViewTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mTextViewDueDate = (TextView) itemView.findViewById(R.id.txt_due_date);
            mImageViewCategory = (ImageView) itemView.findViewById(R.id.img_category);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
        }

        private void bindData(final String ticket) {

//            Category ticketCategory = ticket.getCategory();
//            if (ticketCategory == null) {
//                mImageViewCategory.setImageResource(R.drawable.ic_doc);
//            } else {
//                long categoryId = ticketCategory.getId();
//                final String categoryImageUrl = CategoryUtils.getCategoryImageById(categoryId);
//                Picasso.with(mContext)
//                        .load(categoryImageUrl)
//                        .error(R.drawable.ic_doc)
//                        .placeholder(R.drawable.ic_doc)
//                        .into(mImageViewCategory);
//            }
//
//            mTextViewTitle.setText(TextUtils.isEmpty(ticket.getTitle())
//                    ? mContext.getString(R.string.empty_category) : ticket.getTitle());
//
//            TicketStates currentState = TicketStates.getTicketStateById(ticket.getState().getId());
//            mTextViewLikes.setText(String.valueOf(ticket.getLikesCounter()));
//
//            mTextViewDueDate.setText(ticket.getCreated() == 0 ? "" : DateUtil.getDueDateFormattedDate(
//                    (currentState == TicketStates.DONE ? ticket.getCompleted() : ticket.getCreated())
//                            * Const.MILLIS_IN_SECOND));
//            String addressText = mContext.getString(R.string.empty_address);
//            if (ticket.getAddress() != null && ticket.getAddress().getStreet() != null && ticket.getAddress().getHouse() != null) {
//                addressText = ticket.getAddress().getStreet().getName() + "," + ticket.getAddress().getHouse().getName();
//            } else if (ticket.getGeoAddress() != null) {
//                addressText = GeoAddress.generateAddressLabel(ticket.getGeoAddress());
//            }
//            mTextViewAddress.setText(addressText);
            boolean isSelected = isSelected(getAdapterPosition());
            relativeLayout.setBackgroundResource(isSelected ? R.drawable.list_item_shape_selected : R.drawable.list_item_shape);
        }

    }
}
