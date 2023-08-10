import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'lifecycle_events_method_channel.dart';

abstract class LifecycleEventsPlatform extends PlatformInterface {
  /// Constructs a LifecycleEventsPlatform.
  LifecycleEventsPlatform() : super(token: _token);

  static final Object _token = Object();

  static LifecycleEventsPlatform _instance = MethodChannelLifecycleEvents();

  /// The default instance of [LifecycleEventsPlatform] to use.
  ///
  /// Defaults to [MethodChannelLifecycleEvents].
  static LifecycleEventsPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [LifecycleEventsPlatform] when
  /// they register themselves.
  static set instance(LifecycleEventsPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
