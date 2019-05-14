import Flutter
import UIKit

public class SwiftQrUtilsPlugin: NSObject, FlutterPlugin {

  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "com.aeologic.adhoc.qr_utils", binaryMessenger: registrar.messenger())
    let instance = SwiftQrUtilsPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    //result("iOS " + UIDevice.current.systemVersion)

    self.result = result
            if (call.method == "scanQR") {
                print("captureImage")
                //self.openCamera(false)
                result("scanQR " + UIDevice.current.systemVersion)
            }
            else if (call.method == "generateQR") {
                print("pickImage")
                //self.openPhotoLibrary(false)
                result("generateQR " + UIDevice.current.systemVersion)
            }

  }
}
