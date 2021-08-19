package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.maps.CameraUpdateFactory;
import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.OnMapReadyCallback;
import com.huawei.hms.maps.SupportMapFragment;
import com.huawei.hms.maps.model.LatLng;
import com.huawei.hms.maps.model.Marker;
import com.huawei.hms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback,HuaweiMap.OnMyLocationButtonClickListener{

    private SupportMapFragment mSupportMapFragment;
    private HuaweiMap hMap;
    private static final String TAG = "Map";
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        requestPermission();
        if(isGMS()) {
        } else if(isHMS()) {
        }
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(this);


    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "sdk >= 23 M");
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] strings =
                        {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                ActivityCompat.requestPermissions(this, strings, 1);
            }
        }
    }

    @Override
    public void onMapReady(HuaweiMap huaweiMap) {
        hMap = huaweiMap;
        hMap.setMyLocationEnabled(true);
        hMap.setOnMyLocationButtonClickListener(this);
        LatLng hotel = new LatLng(3.885664568136127, 100.93512881077277);
        hMap.addMarker(new MarkerOptions().position(hotel)
                .title("We Are Here"));
        hMap.moveCamera(CameraUpdateFactory.newLatLng(hotel));

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onStart() { super.onStart(); }

    public boolean  isGMS(){
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) == com.google.android.gms.common.ConnectionResult.SUCCESS;
    }
    public boolean  isHMS(){
        return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this) == com.huawei.hms.api.ConnectionResult.SUCCESS;
    }
}