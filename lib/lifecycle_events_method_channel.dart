import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'lifecycle_events_platform_interface.dart';

/// An implementation of [LifecycleEventsPlatform] that uses method channels.
class MethodChannelLifecycleEvents extends LifecycleEventsPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('lifecycle_events');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
