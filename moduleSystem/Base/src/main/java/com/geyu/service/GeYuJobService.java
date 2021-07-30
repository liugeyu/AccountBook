package com.geyu.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class GeYuJobService  extends JobService {
    @Override
    public void onCreate() {
        super.onCreate();
//        getJobInfo();
        Log.e("service","GuardJobService--onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service","GuardJobService--onStartCommand");
        getJobInfo();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("service","GuardJobService--onStartJob");

        jobFinished(params,true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("service","GuardJobService--onStopJob");
        return false;
    }

    public void getJobInfo(){
        JobInfo.Builder ex = new JobInfo.Builder(8888, new ComponentName(this.getPackageName(), GeYuJobService.class.getName()));

        ex.setBackoffCriteria(30000, JobInfo.BACKOFF_POLICY_LINEAR);
        ex.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        ex.setMinimumLatency(30000);
        ex.setOverrideDeadline(30000);
        ex.setPersisted(true);
//        ex.setPeriodic(500L); //设置执行间隔时间
        //ex.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE); //设置需要的网络环境
        //ex.setMinimumLatency(3000);                            // 设置任务运行最少延迟时间
        //ex.setRequiresCharging(true);                         //设置是否在充电的时候执行
//
        JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            jobScheduler = getSystemService(JobScheduler.class);
        } else {
            jobScheduler = (JobScheduler)this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        }
        int ret = jobScheduler.schedule(ex.build());

        Log.e("service","GuardJobService--getJobInfo -- ret::" + ret);
    }


}
