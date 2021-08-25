package com.geyu.adapter;

import com.geyu.base.BaseFragment;
import com.geyu.ben.FragmentBen;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BaseFragmentPageAdapter extends FragmentStateAdapter {

    private List<FragmentBen> fragments;

    public BaseFragmentPageAdapter(List<FragmentBen> fragments,@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragments = fragments;
    }

    public BaseFragmentPageAdapter(List<FragmentBen> fragments,@NonNull Fragment fragment) {
        super(fragment);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return BaseFragment.newInstance(fragments.get(position).getFragment()).bindData(fragments.get(position).getT());
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
