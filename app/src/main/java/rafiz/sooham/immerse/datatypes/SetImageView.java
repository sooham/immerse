package rafiz.sooham.immerse.datatypes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SetImageView extends ImageView{

    private Paint p;

    public SetImageView(Context context) {
        super(context);
    }

    public SetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setDither(true);
        p.setColor(0xFF00CC00);  // alpha.r.g.b
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, 0, 20, 20, p);
    }
}
