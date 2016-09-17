package rafiz.sooham.immerse.datatypes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class SetImageView extends ImageView {

    private Paint p;

    private List<Position> nodes;
    private double width, height;

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

    public void setSize(double width, double height){
        this.width = width;
        this.height = height;
    }

    public void addNode(float x, float y){
        nodes.add(new Position(x, y));
        this.invalidate();
    }

    public void refresh(){
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Position n : nodes){
            canvas.drawCircle(n.x, n.y, 20, p);
            drawEdges(canvas);
        }
    }

    private void drawEdges(Canvas canvas){
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(1).x*-1, nodes.get(1).y*-1, p);
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(2).x*3, nodes.get(2).y*-5, p);
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(3).x*-5, nodes.get(3).y*3, p);
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(4).x*5, nodes.get(4).y*5, p);

        canvas.drawLine(nodes.get(1).x, nodes.get(1).y, nodes.get(2).x, nodes.get(2).y, p);
        canvas.drawLine(nodes.get(2).x, nodes.get(2).y, nodes.get(4).x, nodes.get(4).y, p);
        canvas.drawLine(nodes.get(3).x, nodes.get(3).y, nodes.get(1).x, nodes.get(1).y, p);
        canvas.drawLine(nodes.get(4).x, nodes.get(4).y, nodes.get(3).x, nodes.get(3).y, p);
    }
}
