package project.dajver.com.roundpinwithavatarexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import project.dajver.com.roundpinwithavatarexample.view.repo.model.PinsModel;
import project.dajver.com.roundpinwithavatarexample.view.repo.RepositoryImpl;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<PinsModel> pinsModels = new ArrayList<>();
        pinsModels.add(new PinsModel(0, "George Clooney", "https://petapixel.com/assets/uploads/2012/04/famous1_mini.jpg", "one"));
        pinsModels.add(new PinsModel(1, "Donald Trump", "http://www.samhurdphotography.com/wp-content/uploads/2014/06/dc-celebrity-portrait-photographers.jpg", "two"));
        pinsModels.add(new PinsModel(2, "Robert De Niro", "https://www.thefamouspeople.com/profiles/images/robert-de-niro-3.jpg", "three"));
        for (int i = 0; i < pinsModels.size(); i++) {
            new RepositoryImpl(this).getImages(pinsModels.get(i).getAvatarUrl(), pinsModels.get(i))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responseModel -> {
                        mMap.addMarker(new MarkerOptions()
                                .position(responseModel.getLatLng())
                                .icon(BitmapDescriptorFactory.fromBitmap(responseModel.getImage())));
                    });
        }
    }
}
