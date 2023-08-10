package com.example.lifecycle_events;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

public class ActivityLifeCycleHandler implements Application.ActivityLifecycleCallbacks {

    ActivityLifeCycleHandler(Context context) {
        Application application = (Application) context.getApplicationContext();
        application.registerActivityLifecycleCallbacks(this);
    }

    static void registerActivityLifeCycleCallBacks(Context context) {
        new ActivityLifeCycleHandler(context);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        System.out.println("Activity started");
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        System.out.println("Activity stopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}