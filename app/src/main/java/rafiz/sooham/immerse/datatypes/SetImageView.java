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

    public void clearNodes(){
        this.nodes.clear();
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
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(1).x, nodes.get(1).y, p);
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(2).x, nodes.get(2).y, p);
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(3).x, nodes.get(3).y, p);
        canvas.drawLine(nodes.get(0).x, nodes.get(0).y, nodes.get(4).x, nodes.get(4).y, p);

        Position p1 = getProject(nodes.get(0), nodes.get(1));
        canvas.drawLine(nodes.get(1).x, nodes.get(1).y, p1.x, p1.y, p);
        Position p2 = getProject(nodes.get(0), nodes.get(2));
        canvas.drawLine(nodes.get(2).x, nodes.get(2).y, p2.x, p2.y, p);
        Position p3 = getProject(nodes.get(0), nodes.get(3));
        canvas.drawLine(nodes.get(3).x, nodes.get(3).y, p3.x, p3.y, p);
        Position p4 = getProject(nodes.get(0), nodes.get(4));
        canvas.drawLine(nodes.get(4).x, nodes.get(4).y, p4.x, p4.y, p);

        canvas.drawLine(nodes.get(1).x, nodes.get(1).y, nodes.get(2).x, nodes.get(2).y, p);
        canvas.drawLine(nodes.get(2).x, nodes.get(2).y, nodes.get(4).x, nodes.get(4).y, p);
        canvas.drawLine(nodes.get(3).x, nodes.get(3).y, nodes.get(1).x, nodes.get(1).y, p);
        canvas.drawLine(nodes.get(4).x, nodes.get(4).y, nodes.get(3).x, nodes.get(3).y, p);
    }

    private Position getProject(Position start, Position end){
        Position project = new Position(0, 0);
        double a = Math.atan(Math.abs(Math.abs(start.y - end.y) / Math.abs(start.x - end.x)));
        if (start.x >= end.x && start.y >= end.y){
            a += Math.PI/2;
            a *= -1;
        } else if (start.x <= end.x && start.y >= end.y){
            a += Math.PI/2;
        } else if (start.x >= end.x && start.y <= end.y){
            a -= Math.PI/2                       ;
        } else if (start.x <= end.x && start.y <= end.y){
            a -= Math.PI/2;
            a *= -1;
        }
        project.set((float) (end.x + 750 * Math.sin(a)), (float) (end.y + 750 * Math.cos(a)));
        return project;
    }
}
