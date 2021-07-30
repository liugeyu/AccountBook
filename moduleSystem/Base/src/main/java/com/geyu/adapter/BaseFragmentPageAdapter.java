package com.geyu.adapter;

import com.geyu.base.BaseFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BaseFragmentPageAdapter extends FragmentStateAdapter {

    private List<Class<? extends BaseFragment>> fragments;
    public BaseFragmentPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public BaseFragmentPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public BaseFragmentPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BaseFragment.newInstance(fragments.get(position));
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
