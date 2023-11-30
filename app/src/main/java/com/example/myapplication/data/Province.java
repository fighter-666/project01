package com.example.myapplication.data;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.myapplication.R;
/*所有带子列表的 Item 都要实现接口 IExpandable<T> 。
抽象类 AbstractExpandableItem<T> 已经实现了该接口并做了常用接口封装，
推荐直接继承它。
getLevel() 函数的返回值必须从 0 开始，子列表的 level 必须大于
父列表的 level 。
为了使不同 Item 使用不同布局，需要实现接口 MultiItemEntity 。
*/
/**
 * 省份（一级列表）
 */
public class Province extends AbstractExpandableItem<City> implements MultiItemEntity {

    private String name;

    public Province(String name) {
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
        return 0;
    }

    @Override
    public int getItemType() {
        return R.layout.item_province;
    }

}

