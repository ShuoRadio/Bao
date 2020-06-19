package com.stx.xhb.pagemenulibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ModelHomeEntrance;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 分页菜单适配器
 * @param <T>
 */
public class EntranceAdapter<T> extends RecyclerView.Adapter<EntranceAdapter.EntranceViewHolder> {
    private List<ModelHomeEntrance> mDatas;

    private Context mContext;

    /**
     * 页数下标,从0开始(通俗讲第几页)
     */
    private int mIndex;

    /**
     * 每页显示最大条目个数
     */
    private int mPageSize;

    private final LayoutInflater mLayoutInflater;

    private List<ModelHomeEntrance> homeEntrances;

    public EntranceAdapter(Context context, List<ModelHomeEntrance> datas, int index, int pageSize) {
        this.mContext = context;
        this.homeEntrances = datas;
        this.mPageSize = pageSize;
        this.mDatas = datas;
        this.mIndex = index;
        this.mLayoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getItemCount() {
        return mDatas.size() > (mIndex + 1) * mPageSize ? mPageSize : (mDatas.size() - mIndex * mPageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + mIndex * mPageSize;
    }


    @NonNull
    @Override
    public AbstractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = mPageMenuViewHolderCreator.getLayoutId();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return mPageMenuViewHolderCreator.createHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AbstractHolder holder, int position) {
        final int pos = position + mIndex * mPageSize;
        holder.bindView(holder,mDatas.get(pos),pos);
    }

}
