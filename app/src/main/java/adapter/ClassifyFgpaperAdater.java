package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import fragment.classify_food;
import fragment.classify_classify;
import fragment.classify_fragment;

public class ClassifyFgpaperAdater extends FragmentPagerAdapter {
    List<Fragment> list = new ArrayList<>();

    public ClassifyFgpaperAdater(FragmentManager fm) {
        super(fm);
        list.add(new classify_classify());
        list.add(new classify_food());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }
    @Override
    public int getCount() {
        return list.size();
    }
}
