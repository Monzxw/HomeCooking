package mon.homecooking;

import android.app.FragmentManager;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import butterknife.ButterKnife;
import butterknife.BindView;

import fragment.*;
import adapter.MainFgpaperAdater;


public class MainActivity extends AppCompatActivity{

    classify_fragment classifypage;
    home_fragment homepage;
    more_fragment morepage;

    @BindView(R.id.mainhome)
    RadioButton mainhome;
    @BindView(R.id.mainclassify)
    RadioButton mainclassify;
    @BindView(R.id.mainmore)
    RadioButton mainmore;
    @BindView(R.id.mainradiogroup)
    RadioGroup mainradiogroup;
    @BindView(R.id.mainviewpager)
    ViewPager mainviewPager;

    FragmentManager fgmanager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        fgmanager = getFragmentManager();
        setgraylogo(0);
        setViewpager(); //设置Viewpager
    }
    public void onclick(View view){
        switch (view.getId()){
            case R.id.mainhome:
                break;
        }
    }
    public void shrink(){
       Drawable[] drawables1 = mainhome.getCompoundDrawables();
       Rect r = new Rect(0, 0, drawables1[1].getMinimumWidth() * 1 / 9 , drawables1[1].getMinimumHeight() * 1 / 9);
       drawables1[1].setBounds(r);
       mainhome.setCompoundDrawables(null, drawables1[1], null, null);

       Drawable[] drawables2 = mainclassify.getCompoundDrawables();
       Rect r1 = new Rect(0, 0, drawables2[1].getMinimumWidth() * 1 / 9, drawables2[1].getMinimumHeight() * 1 / 9);
       drawables2[1].setBounds(r1);
       mainclassify.setCompoundDrawables(null, drawables2[1], null, null);

       Drawable[] drawables3 = mainmore.getCompoundDrawables();
       Rect r2 = new Rect(0, 0, drawables3[1].getMinimumWidth() * 1 / 9, drawables3[1].getMinimumHeight() * 1 / 9);
       drawables3[1].setBounds(r2);
       mainmore.setCompoundDrawables(null, drawables3[1], null, null);
    }
    public void setViewpager(){
        MainFgpaperAdater mainFgpaperAdater = new MainFgpaperAdater(getSupportFragmentManager());
        mainviewPager.setAdapter(mainFgpaperAdater);

        mainradiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.mainhome:
                        mainviewPager.setCurrentItem(0);
                        setgraylogo(0);
                        break;
                    case R.id.mainclassify:
                        mainviewPager.setCurrentItem(1);
                        setgraylogo(1);
                        break;
                    case R.id.mainmore:
                        setgraylogo(2);
                        mainviewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        mainviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state==2){
                    switch (mainviewPager.getCurrentItem()){
                        case 0:
                           mainradiogroup.check(R.id.mainhome);
                            setgraylogo(0);
                            break;
                        case 1:
                            mainradiogroup.check(R.id.mainclassify);
                            setgraylogo(1);
                            break;
                        case 2:
                           mainradiogroup.check(R.id.mainmore);
                            setgraylogo(2);
                            break;
                    }
                }
            }
        });
    }
    public void setallwhitelogo(){
        Drawable homeDrawable = getResources().getDrawable(R.drawable.main_01);
        homeDrawable.setBounds(0, 0,  homeDrawable.getMinimumWidth(),  homeDrawable.getMinimumHeight());
        mainhome.setCompoundDrawables(null, homeDrawable,  null, null);

        Drawable classifyDrawable = getResources().getDrawable(R.drawable.main_02);
        classifyDrawable.setBounds(0, 0, classifyDrawable.getMinimumWidth(),  classifyDrawable.getMinimumHeight());
        mainclassify.setCompoundDrawables(null, classifyDrawable, null, null);

        Drawable moreDrawable = getResources().getDrawable(R.drawable.main_03);
        moreDrawable.setBounds(0, 0,moreDrawable.getMinimumWidth(), moreDrawable.getMinimumHeight());
        mainmore.setCompoundDrawables(null, moreDrawable, null, null);
        shrink();
    }
    public void setgraylogo(int i){
        switch (i)
        {
            case 0:
                setallwhitelogo();
                Drawable homeDrawable = getResources().getDrawable(R.drawable.home_normal);
                homeDrawable.setBounds(0, 0,  homeDrawable.getMinimumWidth(),  homeDrawable.getMinimumHeight());
                mainhome.setCompoundDrawables(null, homeDrawable,  null, null);
                shrink();
                break;
            case 1:
                setallwhitelogo();
                Drawable classifyDrawable = getResources().getDrawable(R.drawable.classify_normal);
                classifyDrawable.setBounds(0, 0, classifyDrawable.getMinimumWidth(),  classifyDrawable.getMinimumHeight());
                mainclassify.setCompoundDrawables(null, classifyDrawable, null, null);
                shrink();
                break;
            case 2:
                setallwhitelogo();
                Drawable moreDrawable = getResources().getDrawable(R.drawable.more_normal);
                moreDrawable.setBounds(0, 0,moreDrawable.getMinimumWidth(), moreDrawable.getMinimumHeight());
                mainmore.setCompoundDrawables(null,  moreDrawable, null, null);
                shrink();
                break;
        }
    }
}
