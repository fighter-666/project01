package com.example.myapplication.data;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.myapplication.R;


/**
 * 城市（二级列表）
 */
public class City extends AbstractExpandableItem<Town> implements MultiItemEntity {

    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public int getItemType() {
        return R.layout.item_city;
    }

}


