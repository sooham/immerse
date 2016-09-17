package rafiz.sooham.immerse.datatypes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class SetImageView extends ImageView {

    private Paint p;

    List<Position> nodes;

    public SetImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setDither(true);
        p.setColor(Color.BLUE);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(10);

        nodes = new ArrayList<>();
    }

    public void addNode(float x, float y){
        nodes.add(new Position(x, y));
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Position n : nodes){
            canvas.drawCircle(n.x, n.y, 20, p);
        }
    }
}
