import 'dart:async';
import 'dart:convert';
import 'dart:typed_data';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

class QrUtils {
  static const MethodChannel _channel =
      const MethodChannel('com.aeologic.adhoc.qr_utils');

  static Future<String> get scanQR async {
    final String qrContent = await _channel.invokeMethod('scanQR');
    return qrContent;
  }

  static Future<Image> generateQR(String content) async {
    final Uint8List uInt8list =
        await _channel.invokeMethod('generateQR', {"content": content});
    return imageFromUInt8List(uInt8list);
  }

  static Image imageFromBase64String(String base64String) {
    return Image.memory(base64Decode(base64String));
  }

  static Uint8List dataFromBase64String(String base64String) {
    return base64Decode(base64String);
  }

  static String base64String(Uint8List data) {
    return base64Encode(data);
  }

  static Image imageFromUInt8List(Uint8List data) {
    return Image.memory(data);
  }
}
