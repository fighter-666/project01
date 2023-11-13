package com.example.myapplication.adapter;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import java.util.List;

/**
 * Created by PersonalFolder on 17/4/1.
 */
public class FragAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mFm;
    private List<Fragment> mFragments;
    private Fragment replaceFragment;
    private int mPosition;
    private boolean canReplace;
    private String[] CONTENT;


    @SuppressLint("WrongConstant")
    public FragAdapter(FragmentManager fm, List<Fragment> fragments, String[] CONTENT) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.mFm = fm;
        this.mFragments = fragments;
        this.CONTENT = CONTENT;
    }

    /*@Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length].toUpperCase();
    }*/

    /**
     * 动态替换fragment,可直接调用 * @param position 指定替换已有fragment的position,第一个是0; * @param fragment 待替换的新的Fragment;
     */
    public void replaceFragment(int position, Fragment fragment) {
        if (position >= 0 && position < mFragments.size()) {
            this.replaceFragment = fragment;
            this.mPosition = position;
            canReplace = true;
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemPosition(Object object) {
        //这是重点继续研究
        //return super.getItemPosition(object);//默认是不改变
        return POSITION_NONE;//可以即时刷新看源码
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //得到缓存的fragment
        Fragment fragment = (Fragment) super.instantiateItem(container,
                position);
        //得到tag，这点很重要
        String fragmentTag = fragment.getTag();
        if (canReplace && mPosition == position) {
            //如果这个fragment需要更新
            FragmentTransaction ft = mFm.beginTransaction();
            //移除旧的fragment
            ft.remove(fragment);
            //换成新的fragment
            fragment = replaceFragment;
            //添加新fragment时必须用前面获得的tag，这点很重要
            ft.add(container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commitAllowingStateLoss();
            //复位更新标志
            canReplace = false;
        }
        return fragment;
    }


    @Override
    public Fragment getItem(int arg0) {

        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {

        return mFragments.size();
    }

}
