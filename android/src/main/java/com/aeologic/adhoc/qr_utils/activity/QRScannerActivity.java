package android.src.main.java.com.aeologic.adhoc.qr_utils.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import static android.app.Activity.RESULT_OK;

/**
 * QrUtilsPlugin
 */
public class QrUtilsPlugin implements MethodCallHandler, PluginRegistry.ActivityResultListener {
    /**
     * Plugin registration.
     */
    private static final String TAG = QrUtilsPlugin.class.getSimpleName();
    private static final String METHOD_CHANNEL = "com.aeologic.adhoc.qr_utils";
    private Result result;
    private int requestID=0;
    private static final int REQUEST_SCAN_QR = 101;
    private static final int REQUEST_GENERATE_QR = 102;


    private Activity activity;

    public QrUtilsPlugin(Activity activity) {
        this.activity = activity;
    }

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), METHOD_CHANNEL);
        QrUtilsPlugin cameraPlugin = new QrUtilsPlugin(registrar.activity());
        registrar.addActivityResultListener(cameraPlugin);
        channel.setMethodCallHandler(cameraPlugin);
    }

    @Override
    public void onMethodCall(MethodCall call, final Result result) {
        this.result = result;
        if (call.method.equals("scanQR")) {
            requestID = REQUEST_SCAN_QR;
            checkPermission();
        } else if (call.method.equals("generateQR")) {
            requestID = REQUEST_GENERATE_QR;
        }
        else {
            result.notImplemented();
        }
    }

    private void checkPermission() {
        Dexter.withActivity(activity)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                if (report.areAllPermissionsGranted()) {
                    if (requestID == REQUEST_SCAN_QR) {
                        scanQR();
                    } else if (requestID == REQUEST_GENERATE_QR) {
                        generateQR();
                    }
                } else {
                    Toast.makeText(activity, "Please grant all permissions!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    private void generateQR() {

    }

    private void scanQR() {
        /*mediaStorageDir = new File(Environment.getExternalStorageDirectory(), Constants.IMAGES);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e("DIR_CREATION", "Failed to create directory!");
            }
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + _CAPTURE_IMAGE_NAME + new SimpleDateFormat(Constants.TIMESTAMP_FORMAT).format(new Date()) + Constants.PNG);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".flutter.provider", mediaFile);
            Log.v(TAG, uri.toString());

        } else {
            uri = Uri.fromFile(mediaFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        activity.startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);*/
    }



    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent intent) {
        Log.v(TAG, "onActivityResult()");
        if (resultCode == RESULT_OK && requestCode == REQUEST_SCAN_QR) {
            /*if (uri != null && uri.getPath() != null) {
                result.success(uri.getPath());
                return true;
            } else {
                result.success(null);
                Toast.makeText(activity, "Process Failed...", Toast.LENGTH_SHORT).show();
            }*/
        }

        return false;
    }

    private String getPath(Uri uri, String[] projection) {
        Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}