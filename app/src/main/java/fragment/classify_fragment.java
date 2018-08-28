package fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mon.homecooking.R;
import adapter.ClassifyFgpaperAdater;
import mon.homecooking.Seach;

public class classify_fragment extends Fragment {
    classify_classify classify;
    classify_food food;
    @BindView(R.id.classify_viewpager)
    ViewPager classify_viewpager;
    @BindView(R.id.classify_radiogroup)
    RadioGroup classify_radiogroup;
    @BindView(R.id.classify_classifyname)
    RadioButton rBclassify;
    @BindView(R.id.classify_foodname)
    RadioButton rBfood;
    @BindView(R.id.et_classifyseach)
    LinearLayout ll_seach;
    FragmentManager fgment = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.classify,null,false);
        ButterKnife.bind(getActivity());
        classify_viewpager = view.findViewById(R.id.classify_viewpager);
        classify_radiogroup = view.findViewById(R.id.classify_radiogroup);
        rBclassify = view.findViewById(R.id.classify_classifyname);
        rBfood = view.findViewById(R.id.classify_foodname);
        ll_seach = view.findViewById(R.id.ll_seach);
        init();
        return view;
    }
    private void init(){
        fgment = getFragmentManager();

        ll_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it1 = new Intent(getActivity(),Seach.class);
                startActivity(it1);
            }
        });
        setViewpager();
        selectedText(0);
    }
    private void setViewpager(){
        final ClassifyFgpaperAdater classifyFgpaperAdater = new ClassifyFgpaperAdater(getActivity().getSupportFragmentManager());
        classify_viewpager.setAdapter(classifyFgpaperAdater);

        classify_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.classify_classifyname:
                        classify_viewpager.setCurrentItem(0);
                        selectedText(0);
                        break;
                    case R.id.classify_foodname:
                        classify_viewpager.setCurrentItem(1);
                        selectedText(1);
                        break;
                }
            }
        });

        classify_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                    if (state == 2){
                        switch (classify_viewpager.getCurrentItem()){
                            case 0:
                                classify_radiogroup.check(R.id.classify_classifyname);
                                selectedText(0);
                                break;
                            case 1:
                                classify_radiogroup.check(R.id.classify_foodname);
                                selectedText(1);
                                break;
                        }
                    }
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    public void selectedText(int i)
    {
        if(i == 0)
        {
            rBclassify.setTextColor(getResources().getColor(R.color.main_color));
            rBfood.setTextColor(getResources().getColor(R.color.main_blacktext));
        }
        else
        {
            rBfood.setTextColor(getResources().getColor(R.color.main_color));
            rBclassify.setTextColor(getResources().getColor(R.color.main_blacktext));
        }
    }
}
