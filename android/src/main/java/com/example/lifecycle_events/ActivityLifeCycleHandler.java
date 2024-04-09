package com.example.lifecycle_events;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Lifecycle;

public class ActivityLifeCycleHandler implements LifecycleEventObserver {

    @Override
    public void onStateChanged(
            @NonNull LifecycleOwner source,
            @NonNull Lifecycle.Event event
    ) {
        System.out.println("DEBUG: onStateChanged called - " + event);
        switch(event) {
            case ON_START:
                System.out.println("DEBUG: Activity started");
                break;
            case ON_STOP:
                System.out.println("DEBUG: Activity stopped");
                break;
            default:
                System.out.println(event + " not handled.");
                break;
        }
    }
}