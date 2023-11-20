package com.example.weather_app;

public interface Contract {
    interface Model {
    }

    interface View {
    }

    interface Presenter {
        void requestLocationPermission();

        void handleLocationPermissionRequestResult(int requestCode, String[] permissions,
                                                   int[] grantResults);
    }
}
