package com.example.weather_app;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class Presenter implements Contract.Presenter {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 101;
    private static final String[] LOCATION_PERMISSION = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION};

    private final Contract.Model model;
    public final Contract.View view;
    private final Context context;
    private final Activity activity;

    public Presenter(Contract.Model model, Contract.View view, Context context, Activity activity) {
        this.model = model;
        this.view = view;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage(R.string.dialog_box_message);
            builder.setPositiveButton(R.string.dialog_box_positive_btn, (dialogInterface, i) -> {
                dialogInterface.dismiss();
                ActivityCompat.requestPermissions(
                        activity,
                        LOCATION_PERMISSION,
                        LOCATION_PERMISSION_REQUEST_CODE);
            });
            builder.setNegativeButton(R.string.dialog_box_negative_btn, (dialogInterface, i) ->
                    dialogInterface.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            ActivityCompat.requestPermissions(
                    activity,
                    LOCATION_PERMISSION,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void handleLocationPermissionRequestResult(int requestCode, String[] permissions,
                                                      int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                model.getLastLocation(activity);
            } else {
                // permission denied
            }
        }
    }
}
