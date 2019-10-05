package com.taskapp.onboard;

import android.os.Bundle;

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
        BoardFragment fragment = new BoardFragment(); //TODO: отделили Фрагмент для того, чтобы заполить
        Bundle bundle = new Bundle(); //TODO: Bundle для Fragment (Intent для Activity)
        bundle.putInt("pos", position); //TODO: отправляем ключ("pos") и значение(позицию) во Фрагмент. Во Фрагменте надо принять это
        fragment.setArguments(bundle);
        return fragment; //TODO: создаем копию фрагмента в кол-ве 3
    }

    @Override
    public int getCount() {
        return 3; //TODO: кол-во страниц Слайдера
    }
}
