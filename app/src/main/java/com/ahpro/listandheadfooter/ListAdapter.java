package com.ahpro.listandheadfooter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder> {
    private Context mContext;
    private int mLayout;
    private List<ListItem> mList;
    private LayoutInflater mInflater;

    public ListAdapter(Context context, int layout, List<ListItem> list) {
        mContext = context;
        mLayout = layout;
        mList = list;
        mInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView DocNum;
        TextView ItemCode;
        TextView ItemName;
        TextView PostDate;
        TextView DueDate;
        TextView Quantity;
        TextView InventoryUOM;
        TextView CompletedQuantity;
        TextView InventoryUOM2;
        TextView Remarks;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            DocNum = itemView.findViewById(R.id.DocNum);
            ItemCode = itemView.findViewById(R.id.ItemCode);
            ItemName = itemView.findViewById(R.id.ItemName);
            PostDate = itemView.findViewById(R.id.PostDate);
            DueDate = itemView.findViewById(R.id.DueDate);
            Quantity = itemView.findViewById(R.id.Quantity);
            InventoryUOM = itemView.findViewById(R.id.InventoryUOM);
            CompletedQuantity = itemView.findViewById(R.id.CompletedQuantity);
            InventoryUOM2 = itemView.findViewById(R.id.InventoryUOM2);
            Remarks = itemView.findViewById(R.id.Remarks);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        ListItem data = mList.get(position);

        holder.DocNum.setText(data.getDocNum());
        holder.ItemCode.setText(data.getItemCode());
        holder.ItemName.setText(data.getItemName());
        holder.PostDate.setText(data.getPostDate());
        holder.DueDate.setText(data.getDueDate());
        holder.Quantity.setText(data.getQuantity());
        holder.InventoryUOM.setText(data.getUOM());
        holder.CompletedQuantity.setText(data.getCmplQty());
        holder.InventoryUOM2.setText(data.getUOM());
        holder.Remarks.setText(data.getRemarks());
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Context context = v.getContext();
                if (position != -1) {
                    Intent intent = new Intent(mContext, PopupActivity.class);
                    intent.putExtra("ItemCode", mList.get(position).getItemCode());
                    mContext.startActivity(intent);
                }
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
