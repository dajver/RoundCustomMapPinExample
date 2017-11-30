package project.dajver.com.roundpinwithavatarexample.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import project.dajver.com.roundpinwithavatarexample.R;

/**
 * Created by gleb on 11/30/17.
 */

public class CustomPinView extends FrameLayout {

    @BindView(R.id.profile_image)
    CircleImageView markerImageView;
    @BindView(R.id.custom_marker_view)
    RelativeLayout customMarkerView;
    @BindView(R.id.userName)
    TextView userName;

    private Context context;

    public CustomPinView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CustomPinView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomPinView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_custom_map_pin, this);
        ButterKnife.bind(this);
    }

    public void setBackground(int id) {
        customMarkerView.setBackgroundResource(id);
    }

    public void setIcon(Bitmap bmpImg, String name, String mapStatus) {
        if(bmpImg != null)
            markerImageView.setImageBitmap(bmpImg);
        else {
            Rect rect = new Rect(0, 0, 1, 1);
            Bitmap image = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(image);
            int color = Color.RED;
            if(mapStatus.equals("one"))
                color = getResources().getColor(R.color.yellow);
            else if(mapStatus.equals("two"))
                color = getResources().getColor(R.color.green);
            else
                color = getResources().getColor(R.color.red);
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(rect, paint);
            markerImageView.setImageBitmap(image);

            userName.setVisibility(View.VISIBLE);
            userName.setText(name);
        }
    }
}