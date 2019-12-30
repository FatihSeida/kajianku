package com.prplsd.kajianku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.prplsd.kajianku.R;
import com.prplsd.kajianku.model.KajiankuItem;

import java.util.ArrayList;
import java.util.List;

public class KajiankuAdapter extends RecyclerView.Adapter<KajiankuAdapter.MyViewHolder> {
    List<KajiankuItem> listItem;
    TextView tvKajiankuName;
    View view;
    ImageView ivKajiankuPhoto;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onClick(int position);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }
    public KajiankuAdapter(Context ctx) {
        this.ctx = ctx;
        listItem = new ArrayList<>();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View v) {
            super(v);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_kajianku, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        KajiankuItem item = listItem.get(position);
        ivKajiankuPhoto = holder.itemView.findViewById(R.id.iv_kajianku);
        tvKajiankuName = holder.itemView.findViewById(R.id.tv_kajianku_name);
        Glide.with(ctx).load(item.getFoto()).into(ivKajiankuPhoto);
        tvKajiankuName.setText(item.getNama_kajian());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listItem.size();
    }
    public void add(KajiankuItem item) {
        listItem.add(item);
        notifyItemInserted(listItem.size() + 1);
    }
    public void addAll(List<KajiankuItem> listItem) {
        for (KajiankuItem item : listItem) {
            add(item);
        }
    }
    public void removeAll() {
        listItem.clear();
        notifyDataSetChanged();
    }
    public void remove(int pos) {
        listItem.remove(pos);
        notifyDataSetChanged();
    }
    public void swap(List<KajiankuItem> datas) {
        if (datas == null || datas.size() == 0) listItem.clear();
        if (listItem != null && listItem.size() > 0)
            listItem.clear();
        listItem.addAll(datas);
        notifyDataSetChanged();
    }
    public KajiankuItem getItem(int pos) {
        return listItem.get(pos);
    }
    public void setFilter(List<KajiankuItem> list) {
        listItem = new ArrayList<>();
        listItem.addAll(list);
        notifyDataSetChanged();
    }
    public List<KajiankuItem> getListItem() {
        return listItem;
    }

}
