# lifecycle_events

Sample Flutter Plugin to demonstrate the differences in how FlutterFragmentActivity and FlutterActivity get attached to the FlutterEngine when running on the Android Platform.

## Description of the issue

I am registering for the `ActivityLifeCycleCallBacks` in the `onAttachedToEngine` of the Android Implementation of my Flutter Plugin as shown [here](https://github.com/desusai7/lifecycle_events/blob/0f00e0b891056cf5e004509065c146ef6de59d34/android/src/main/java/com/example/lifecycle_events/LifecycleEventsPlugin.java#L25)

And I am printing out some debug statements in the `ActivityLifeCycleHandler` to demonstrate the issue as shown [here](https://github.com/desusai7/lifecycle_events/blob/main/android/src/main/java/com/example/lifecycle_events/ActivityLifeCycleHandler.java)

To observe the issue, run the app on the Android platform with `MainActivity` extending `FlutterActivity` as shown below, then you will observe that both `onActivityStarted` and `onActivityStopped` are properly triggered whenever the Application is moved background and foreground as shown in the image attached below: 

<img width="1726" alt="image" src="https://github.com/desusai7/lifecycle_events/assets/48179357/beceb611-17c6-4d56-b189-fc53daea616d">

And when we change the `MainActivity` to extend `FlutterFragmentActivity` and then we can observe that `onActivityStarted` is not triggered on the initial launch of the application, but from then the callbacks are triggered perfectly fine. 

![image](https://github.com/desusai7/lifecycle_events/assets/48179357/2722364f-ffe4-404c-b665-f9075d5a462e)

I am kind of wondering if the reason why the `onActivityStarted` is not triggered on the initial launch of the application when the `MainActivity` is extending `FlutterFragmentActivity` is because the `FlutterFragmentActivity` got attached to the `FlutterEngine` even before my `FlutterPlugin` got attached to the engine. 

But however in the case of `MainActivity` extending `FlutterActivity` the `Activity` seems to be getting attached to the `FlutterEngine` always after the `FlutterPlugin` got attached to it. 

Can we ensure that the process of activity getting attached to the engine is always done in a particular way irrespective of what type of activity is being extended by `MainActivity` to have a predictable and proper result? 

I would prefer `FlutterFragmentActivity` also to be attached to the `FlutterEngine` after all the `FlutterPlugin`'s are attached to the `FlutterEngine`. (Obviously, I do not have the knowledge of why it is being developed this way, and I can be completely wrong as well)

Also, please let me know if there is a better way to deal with this problem !
