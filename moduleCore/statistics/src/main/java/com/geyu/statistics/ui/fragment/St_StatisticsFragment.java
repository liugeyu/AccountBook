package com.geyu.statistics.ui.fragment;

import android.graphics.Color;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.geyu.Constant;
import com.geyu.base.Annotation.CreateViewModel;
import com.geyu.base.BaseMvvmFragment;
import com.geyu.database.ben.CategoryModel;
import com.geyu.db.CategroyManager;
import com.geyu.statistics.R;
import com.geyu.statistics.databinding.StFragmentStatistivsBinding;
import com.geyu.statistics.ui.viewmodel.St_StatisticsViewModel;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

@Route(path = Constant.StatisticsClass.FRAGMENT_STATISTICS)
@CreateViewModel(St_StatisticsViewModel.class)
public class St_StatisticsFragment extends BaseMvvmFragment<St_StatisticsViewModel, StFragmentStatistivsBinding> implements OnChartValueSelectedListener {

    protected final String[] parties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    @Override
    protected int getLayoutId() {
        return R.layout.st_fragment_statistivs;
    }

    @Override
    protected void initView() {
        super.initView();

        initChat();
    }

    private void initChat() {
        mDataBinding.pieChart.setUsePercentValues(true);
        mDataBinding.pieChart.getDescription().setEnabled(false);
        mDataBinding.pieChart.setExtraOffsets(5, 10, 5, 5);

        mDataBinding.pieChart.setDragDecelerationFrictionCoef(0.95f);

//        mDataBinding.pieChart.setCenterTextTypeface(tfLight);
        mDataBinding.pieChart.setCenterText("test");

        mDataBinding.pieChart.setDrawHoleEnabled(true);
        mDataBinding.pieChart.setHoleColor(Color.WHITE);

        mDataBinding.pieChart.setTransparentCircleColor(Color.WHITE);
        mDataBinding.pieChart.setTransparentCircleAlpha(110);

        mDataBinding.pieChart.setHoleRadius(58f);
        mDataBinding.pieChart.setTransparentCircleRadius(61f);

        mDataBinding.pieChart.setDrawCenterText(true);

        mDataBinding.pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mDataBinding.pieChart.setRotationEnabled(true);
        mDataBinding.pieChart.setHighlightPerTapEnabled(true);

        // chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        mDataBinding.pieChart.setOnChartValueSelectedListener(this);


        mDataBinding.pieChart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = mDataBinding.pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mDataBinding.pieChart.setEntryLabelColor(Color.WHITE);
//        mDataBinding.pieChart.setEntryLabelTypeface(tfRegular);
        mDataBinding.pieChart.setEntryLabelTextSize(12f);
    }


    private void setData(int count, float range) {

        CategroyManager.findAll(CategoryModel.TYPE_EXPENSE)
                .flatMap(new Function<List<CategoryModel>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull List<CategoryModel> categoryModels) throws Exception {
                        return null;
                    }
                });

        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        mDataBinding.pieChart.setData(data);

        // undo all highlights
        mDataBinding.pieChart.highlightValues(null);

        mDataBinding.pieChart.invalidate();
    }

    @Override
    protected void initData() {
        super.initData();

        setData(4,4);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
