
import 'lifecycle_events_platform_interface.dart';

class LifecycleEvents {
  Future<String?> getPlatformVersion() {
    return LifecycleEventsPlatform.instance.getPlatformVersion();
  }
}
