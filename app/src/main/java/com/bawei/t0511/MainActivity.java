package com.bawei.t0511;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawei.t0511.fragment.Syragment;
import com.bawei.t0511.fragment.Xqragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager main_vp;
    private RadioGroup main_rg;
    private List<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_vp = findViewById(R.id.main_vp);
        main_rg = findViewById(R.id.main_rg);
        list.add(new Syragment());
        list.add(new Xqragment());

        main_vp.setOffscreenPageLimit(0);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), list);
        main_vp.setAdapter(fragmentAdapter);
        //联动
        main_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                int id = main_rg.getChildAt(i).getId();
                main_rg.check(id);
                for (int j = 0; j < list.size(); j++) {
                    RadioButton radioButton = (RadioButton) main_rg.getChildAt(j);
                    if (j == i) {
                        radioButton.setTextColor(Color.RED);
                    } else {
                        radioButton.setTextColor(Color.BLACK);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.f_one:
                        main_vp.setCurrentItem(0);
                        break;

                    case R.id.f_two:
                        main_vp.setCurrentItem(1);
                        break;
                }

            }
        });

    }


    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public void onChangeItem() {
        main_vp.setCurrentItem(1);
    }

    public void onChangesItem() {
        main_vp.setCurrentItem(0);
    }


}
