package com.taskapp.onboard;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter { //TODO: создали Adapter для того чтобы Активити могла показать данные из Фрагмента

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new BoardFragment(); //TODO: создаем BoardFragment()
    }

    @Override
    public int getCount() {
        return 3; //TODO: кол-во страниц Слайдера
    }
}
