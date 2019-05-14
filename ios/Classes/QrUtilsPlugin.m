#import "QrUtilsPlugin.h"
#import <qr_utils/qr_utils-Swift.h>

@implementation QrUtilsPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftQrUtilsPlugin registerWithRegistrar:registrar];
}
@end
