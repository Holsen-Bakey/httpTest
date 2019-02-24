package com.pic.optimize.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import com.pic.optimize.test.bean.QuestionInfo;
import com.pic.optimize.test.fragment.CardFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private Map<Integer, Fragment> classifyFragments = new HashMap<>();
    private List<QuestionInfo> classifyModels;

    public CardFragmentPagerAdapter(FragmentManager fm, List<QuestionInfo> classifyModels) {
        super(fm);
        this.classifyModels = classifyModels;
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(classifyModels.get(position));
    }

    @Override
    public int getCount() {
        return classifyModels.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment classifyFragment = (Fragment) super.instantiateItem(container, position);
        classifyFragments.put(position, classifyFragment);
        return classifyFragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        classifyFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

}
