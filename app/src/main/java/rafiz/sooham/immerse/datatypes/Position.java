package rafiz.sooham.immerse.datatypes;

import org.opencv.core.Point;

public class Position {

    public float x;
    public float y;

    public Position(float x, float y){
        set(x, y);
    }

    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }
}
