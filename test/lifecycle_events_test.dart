import 'package:flutter_test/flutter_test.dart';
import 'package:lifecycle_events/lifecycle_events.dart';
import 'package:lifecycle_events/lifecycle_events_platform_interface.dart';
import 'package:lifecycle_events/lifecycle_events_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockLifecycleEventsPlatform
    with MockPlatformInterfaceMixin
    implements LifecycleEventsPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final LifecycleEventsPlatform initialPlatform = LifecycleEventsPlatform.instance;

  test('$MethodChannelLifecycleEvents is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelLifecycleEvents>());
  });

  test('getPlatformVersion', () async {
    LifecycleEvents lifecycleEventsPlugin = LifecycleEvents();
    MockLifecycleEventsPlatform fakePlatform = MockLifecycleEventsPlatform();
    LifecycleEventsPlatform.instance = fakePlatform;

    expect(await lifecycleEventsPlugin.getPlatformVersion(), '42');
  });
}
