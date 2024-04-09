package com.example.lifecycle_events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import androidx.lifecycle.Lifecycle;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.embedding.engine.plugins.lifecycle.HiddenLifecycleReference;
//import io.flutter.embedding.engine.plugins.lifecycle.FlutterLifecycleAdapter;

/** LifecycleEventsPlugin */
public class LifecycleEventsPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  @Nullable Lifecycle lifecycle;

  private ActivityLifeCycleHandler lifecycleHandler;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    System.out.println("DEBUG: onAttachedToEngine called");
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "lifecycle_events");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    System.out.println("DEBUG: onDetachedFromEngine called");
    channel.setMethodCallHandler(null);
  }

  @Override
  public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
    System.out.println("DEBUG: onAttachedToActivity start");
    System.out.println("DEBUG: binding - " + binding);
    // TODO: Switch to using FlutterLifecycleAdapter
    lifecycle = ((HiddenLifecycleReference)binding.getLifecycle()).getLifecycle();
    System.out.println("DEBUG: got lifecycle");
    if (lifecycle == null) {
      System.out.println("DEBUG: lifecycle is null");
      return;
    }
    lifecycle.addObserver(lifecycleHandler = new ActivityLifeCycleHandler());
    System.out.println("DEBUG: onAttachedToActivity end");
  }

  @Override
  public void onDetachedFromActivity() {
    System.out.println("DEBUG: onDetachedToActivity called");
    if (lifecycle != null && lifecycleHandler != null) {
      lifecycle.removeObserver(lifecycleHandler);
    }
    lifecycle = null;
  }

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
    System.out.println("DEBUG: onReattachedToActivityForConfigChanges");
    onAttachedToActivity(binding);
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {
    System.out.println("DEBUG: onDetachedFromActivityForConfigChanges");
    onDetachedFromActivity();
  }
}
