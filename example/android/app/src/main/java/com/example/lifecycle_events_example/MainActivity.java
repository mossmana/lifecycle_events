package com.example.lifecycle_events_example;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.android.FlutterFragmentActivity;
import io.flutter.embedding.engine.FlutterEngine;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;

public class MainActivity
        extends FlutterFragmentActivity {
//        extends FlutterActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("DEBUG: onCreate called");
    }

    @Override
    protected void onStart () {
        super.onStart();
        System.out.println("DEBUG: onStart called");
    }

    @Override
    protected void onStop () {
        super.onStop();
        System.out.println("DEBUG: onStop called");
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        System.out.println("DEBUG: configureFlutterEngine called");
    }
}
