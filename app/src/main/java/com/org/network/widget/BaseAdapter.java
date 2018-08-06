package com.org.network.widget;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by quhong on 22/02/2017.
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    public List<T> mData;

    public BaseAdapter(List<T> data) {
        mData = data;
    }

    public void addNewData(List<T> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    public void setNewData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    protected OnChildClickListener mOnChildClickListener;

    public void setOnChildClick(OnChildClickListener onChildClickListener) {
        mOnChildClickListener = onChildClickListener;
    }

    protected OnItemClickListener mOnItemClickListener;

    public void setOnItemClick(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    protected OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public View inflate(ViewGroup parent, @LayoutRes int resource, ViewGroup root) {
        return View.inflate(parent.getContext(), resource, root);
    }

    public View inflate(ViewGroup parent, @LayoutRes int resource) {
        return inflate(parent, resource, null);
    }

    private void setDefaultClick(final VH holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, holder.getAdapterPosition());
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onClick(v, holder.getAdapterPosition());
                }
                return true;
            }
        });

    }

    private void setChildClick(final VH holder, Integer... resId) {
        if (resId == null) {
            return;
        }
        for (Integer aResId : resId) {
            View view = holder.itemView.findViewById(aResId);
            if (view == null) {
                throw new IllegalArgumentException("找不到该id");
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnChildClickListener != null) {
                        mOnChildClickListener.onClick(v, holder.getAdapterPosition());
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T t = null;
        t = getData(position);
        bind(holder, position, t);
        setDefaultClick(holder);
        setChildClick(holder, getIds());
    }

    public T getData(int position){
        if (mData != null && mData.size() != 0) {
            return mData.get(position);
        }
        return null;
    }

    public abstract void bind(VH holder, int position, T t);

    protected Integer[] getIds() {
        return null;
    }
}
