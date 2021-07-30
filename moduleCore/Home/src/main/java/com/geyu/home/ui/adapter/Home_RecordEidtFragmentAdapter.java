package com.geyu.home.ui.adapter;

import com.geyu.database.ben.Record;
import com.geyu.home.ui.fragment.Home_RecordEditFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Home_RecordEidtFragmentAdapter extends FragmentStateAdapter {
    private List<Integer> types = new ArrayList<>();
    private Record oldRecord;

    public Home_RecordEidtFragmentAdapter(@NonNull FragmentActivity fragmentActivity, List<Integer> inTypes,Record record) {
        super(fragmentActivity);
        types.addAll(inTypes);
        oldRecord = record;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int type = types.get(position);
        if (oldRecord != null && oldRecord.getType() == type) {
            return Home_RecordEditFragment.getInstance(oldRecord,type);
        }
        return Home_RecordEditFragment.getInstance(null,type);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
}
