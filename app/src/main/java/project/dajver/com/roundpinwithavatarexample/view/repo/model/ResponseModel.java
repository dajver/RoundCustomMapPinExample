package project.dajver.com.roundpinwithavatarexample.view.repo.model;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by gleb on 11/30/17.
 */

public class ResponseModel {

    private Bitmap image;
    private LatLng latLng;

    public ResponseModel(Bitmap image, LatLng latLng) {
        this.image = image;
        this.latLng = latLng;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }
}
