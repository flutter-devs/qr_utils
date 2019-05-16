For help getting started with Flutter, view our online
[documentation](https://flutter.io/).


[![Pub](https://img.shields.io/badge/Pub-0.1.0-orange.svg?style=flat-square)](https://pub.dartlang.org/packages/qr_utils)



# qr_utils

A new Flutter QR scanner and generator plugin.

### Implementation in Flutter

Simply add a dependency to you pubspec.yaml for qr_utils.

Then import the package in your dart file with

```dart
import 'package:qr_utils/qr_utils.dart';
```


### Screenshots

### iOS

<img height="420px" src="https://github.com/flutter-devs/qr_utils/blob/master/assets/screenshots/screenshot1.jpg"> <img height="420px" src="https://github.com/flutter-devs/camera_utils/blob/master/assets/screenshots/screenshot2.jpg"> <img height="420px" src="https://github.com/flutter-devs/camera_utils/blob/master/assets/screenshots/screenshot3.jpg">

### Android

<img height="420px" src="https://github.com/flutter-devs/qr_utils/blob/master/assets/screenshots/screenshot1.jpg"> <img height="420px" src="https://github.com/flutter-devs/camera_utils/blob/master/assets/screenshots/screenshot2.jpg"> <img height="420px" src="https://github.com/flutter-devs/camera_utils/blob/master/assets/screenshots/screenshot3.jpg">


### Usages

1. Scan QR

    ```dart
    // Scan QR
    final content = await QrUtils.scanQR;
    ```

2. Generate QR

     ```dart
     // Generate QR
    Image image = await QrUtils.generateQR(content);
    ```
