package my.edu.utar.secret_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(this);


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
    protected void onStart() {
        super.onStart();
    }
}