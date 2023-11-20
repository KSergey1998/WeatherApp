package com.example.weather_app;

import android.app.Activity;

public interface Contract {
    interface Model {
        void getLastLocation(Activity activity);
    }

    interface View {
    }

    interface Presenter {
        void requestLocationPermission();

        void handleLocationPermissionRequestResult(
                int requestCode,
                String[] permissions,
                int[] grantResults);
    }
}
