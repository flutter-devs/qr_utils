import 'dart:async';

import 'package:flutter/services.dart';

class QrUtils {
  static const MethodChannel _channel =
      const MethodChannel('com.aeologic.adhoc.qr_utils');

  static Future<String> get scanQR async {
    final String qrContent = await _channel.invokeMethod('scanQR');
    return qrContent;
  }

  static Future<String> get generateQR async {
    final String qrTempPath = await _channel.invokeMethod('generateQR');
    return qrTempPath;
  }
}
