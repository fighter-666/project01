package com.example.myapplication.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.myapplication.R;
import com.example.myapplication.data.City;
import com.example.myapplication.data.Province;
import com.example.myapplication.data.Town;

import java.util.List;

/**
 * 地区适配器
 */
public class LocationAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public LocationAdapter(List<MultiItemEntity> data) {
        super(data);

        // 指定 type 对应的布局资源
        addItemType(R.layout.item_province, R.layout.item_province);
        addItemType(R.layout.item_city, R.layout.item_city);
        addItemType(R.layout.item_town, R.layout.item_town);

        setOnItemClickListener();
    }

    // 设置 Item 点击事件监听器
    private void setOnItemClickListener() {
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiItemEntity item = getItem(position);
                if (!(item instanceof AbstractExpandableItem)) {
                    return;
                }
                if (((AbstractExpandableItem) item).isExpanded()) {
                    // 收起被点击 Item 的子列表
                    collapse(position + getHeaderLayoutCount());
                } else {
                    // 展开被点击 Item 的子列表
                    expand(position + getHeaderLayoutCount());
                }
            }
        };
        setOnItemClickListener(onItemClickListener);
    }



    @Override
    protected void convert(@NonNull BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case R.layout.item_province:
                showProvince(helper, (Province) item);
                break;
            case R.layout.item_city:
                showCity(helper, (City) item);
                break;
            case R.layout.item_town:
                showTown(helper, (Town) item);
                break;
            default:
                break;
        }
    }

    private void showProvince(@NonNull BaseViewHolder helper, Province province) {
        helper.setText(R.id.tvTitle, province.getName());
        helper.getView(R.id.ivExpandIcon).setRotation(province.isExpanded() ? 180 : 0);
    }

    private void showCity(@NonNull BaseViewHolder helper, City city) {
        helper.setText(R.id.tvCity, city.getName());
        helper.getView(R.id.ivExpandIcon).setRotation(city.isExpanded() ? 180 : 0);
    }

    private void showTown(@NonNull BaseViewHolder helper, Town town) {
        helper.setText(R.id.tvTown, town.getName());
    }
}
