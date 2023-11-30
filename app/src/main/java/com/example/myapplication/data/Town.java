package com.example.myapplication.data;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.myapplication.R;

/**
 * 乡镇（三级列表）
 */
public class Town implements MultiItemEntity {

    public Town(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return R.layout.item_town;
    }

}


