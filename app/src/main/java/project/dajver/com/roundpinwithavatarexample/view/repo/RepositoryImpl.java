package project.dajver.com.roundpinwithavatarexample.view.repo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.io.InputStream;
import java.util.Random;

import io.reactivex.Observable;
import project.dajver.com.roundpinwithavatarexample.R;
import project.dajver.com.roundpinwithavatarexample.view.CustomPinView;
import project.dajver.com.roundpinwithavatarexample.view.model.PinsModel;
import project.dajver.com.roundpinwithavatarexample.view.repo.model.ResponseModel;

/**
 * Created by gleb on 11/30/17.
 */

public class RepositoryImpl implements IRepository {

    private Context context;

    public RepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<ResponseModel> getImages(String url, PinsModel pinsModel) {
        return Observable.create(observableEmitter -> {
            try {
                Bitmap bmpImg = null;
                try {
                    InputStream in = new java.net.URL(url).openStream();
                    bmpImg = BitmapFactory.decodeStream(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                CustomPinView customPinView = new CustomPinView(context);
                if(pinsModel.getMapStatus().equals("one"))
                    customPinView.setBackground(R.mipmap.yelow_map_pin);
                else if(pinsModel.getMapStatus().equals("two"))
                    customPinView.setBackground(R.mipmap.green_map_pin);
                else
                    customPinView.setBackground(R.mipmap.red_map_pin);
                customPinView.setIcon(bmpImg, pinsModel.getFullName(), pinsModel.getMapStatus());

                int rand = new Random().nextInt(151 - 14) + 14;
                LatLng randLatLng = new LatLng(-rand, rand);

                ResponseModel responseModel = new ResponseModel(createDrawableFromView(context, customPinView), randLatLng);
                observableEmitter.onNext(responseModel);
            } catch (Exception e) {
                observableEmitter.onError(e);
            } finally {
                observableEmitter.onComplete();
            }
        });
    }

    public Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
    }
}
