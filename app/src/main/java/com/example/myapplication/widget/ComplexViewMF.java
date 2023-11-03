package com.example.myapplication.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.ComplexItemEntity;
import com.gongwen.marqueen.MarqueeFactory;

import java.util.ArrayList;
import java.util.List;

//MarqueeFactory<T extends View, E>
//泛型T:指定ItemView的类型
//泛型E:指定ItemView填充的数据类型
public class ComplexViewMF extends MarqueeFactory<LinearLayout, ComplexItemEntity> {
    private LayoutInflater inflater;

    public ComplexViewMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public LinearLayout generateMarqueeItemView(ComplexItemEntity data) {
        LinearLayout mView = (LinearLayout) inflater.inflate(R.layout.complex_view, null);
        ((TextView) mView.findViewById(R.id.title)).setText(data.getTitle());
        ((TextView) mView.findViewById(R.id.secondTitle)).setText(data.getSecondTitle());
        ((TextView) mView.findViewById(R.id.thirdTitle)).setText(data.getThirdTitle());
        ((TextView) mView.findViewById(R.id.fourthTitle)).setText(data.getFourthTitle());
        return mView;
    }
/*
    // 新的封装方法，直接传入四个字段的数据
    public void setData(List<String> titles, List<String> secondTitles, List<String> thirdTitles, List<String> fourthTitles) {
        List<ComplexItemEntity> complexDatas = new ArrayList<>();
        int size = Math.min(titles.size(), Math.min(secondTitles.size(), Math.min(thirdTitles.size(), fourthTitles.size())));
        for (int i = 0; i < size; i++) {
            complexDatas.add(new ComplexItemEntity(titles.get(i), secondTitles.get(i), thirdTitles.get(i), fourthTitles.get(i)));
        }
        setData(complexDatas);
    }*/
}
