package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import fragment.*;

public class MainFgpaperAdater extends FragmentPagerAdapter {

    List<Fragment> fgList = new ArrayList<>();
    public MainFgpaperAdater(FragmentManager fm) {
        super(fm);
        fgList.add(new home_fragment());
        fgList.add(new classify_fragment());
        fgList.add(new more_fragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fgList.get(position);
    }

    @Override
    public int getCount() {
        return fgList.size();
    }
}
