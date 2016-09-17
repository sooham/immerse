package rafiz.sooham.immerse.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import rafiz.sooham.immerse.R;
import rafiz.sooham.immerse.core.Cons;
import rafiz.sooham.immerse.datatypes.Position;
import rafiz.sooham.immerse.datatypes.SetImageView;

@EActivity(R.layout.activity_set)
public class SetActivity extends AppCompatActivity {

    @ViewById(R.id.view)
    SetImageView view;

    private File imageFile;

    private Position root;
    private Position topL, topR, botL, botR;

    private Position currNode;
    private boolean moving;

    @AfterViews
    protected void init(){
        String path = getIntent().getStringExtra(Cons.FILEPATH_EXTRA);
        imageFile = path.isEmpty() ? null : new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        view.setImageBitmap(bitmap);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction() & MotionEvent.ACTION_MASK;
                switch (action) {
                    case MotionEvent.ACTION_DOWN: {
                        // start
                        int touch = nodeUnderTouch(event.getX(), event.getY());
                        if (touch == 0){
                            currNode = root;
                            moving = true;
                        }
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        if (moving){
                            currNode.x = event.getX();
                            currNode.y = event.getY();
                            view.refresh();
                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        currNode = null;
                        moving = false;
                        break;
                    }
                }
                return true;
            }
        });

        view.post(new Runnable() {
            @Override
            public void run() {
                root = new Position(view.getWidth()/2, view.getHeight()/2);
                topL = new Position(view.getWidth()/4, view.getHeight()/4);
                topR = new Position(3*view.getWidth()/4, view.getHeight()/4);
                botL = new Position(view.getWidth()/4, 3*view.getHeight()/4);
                botR = new Position(3*view.getWidth()/4, 3*view.getHeight()/4);
                view.addNode(root.x, root.y);
                view.addNode(topL.x, topL.y);
                view.addNode(topR.x, topR.y);
                view.addNode(botL.x, botL.y);
                view.addNode(botR.x, botR.y);
            }
        });
    }

    public int nodeUnderTouch(double x, double y){
        if (looseEq(x, root.x) && looseEq(y, root.y)){
            return 0;
        }
        return -1;
    }

    public boolean looseEq(double a, double b){
        return a >= b - 5 && a <= b + 5;
    }
}
