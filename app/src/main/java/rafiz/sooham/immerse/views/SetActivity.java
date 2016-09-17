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

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
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

    private int currNode;
    private boolean moving;

    @AfterViews
    protected void init(){
        Crouton.makeText(this, "Please set critical points", Style.INFO).show();

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
                        currNode = nodeUnderTouch(event.getX(), event.getY());
                        if (currNode != -1){
                            moving = true;
                        }
                        break;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        if (moving){
                            switch (currNode){
                                case 0:
                                    root.x = event.getX();
                                    root.y = event.getY();
                                    break;
                                case 1:
                                    topL.x = event.getX();
                                    topL.y = event.getY();
                                    break;
                                case 2:
                                    topR.x = event.getX();
                                    topR.y = event.getY();
                                    break;
                                case 3:
                                    botL.x = event.getX();
                                    botL.y = event.getY();
                                    break;
                                case 4:
                                    botR.x = event.getX();
                                    botR.y = event.getY();
                                    break;
                            }
                            addNodes();
                            view.refresh();
                        }
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        // end
                        currNode = -1;
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
                addNodes();
            }
        });
    }

    private void addNodes(){
        view.clearNodes();
        view.addNode(root.x, root.y);
        view.addNode(topL.x, topL.y);
        view.addNode(topR.x, topR.y);
        view.addNode(botL.x, botL.y);
        view.addNode(botR.x, botR.y);
    }

    private int nodeUnderTouch(double x, double y){
        if (looseEq(x, root.x) && looseEq(y, root.y)){
            return 0;
        } else if (looseEq(x, topL.x) && looseEq(y, topL.y)){
            return 1;
        }else if (looseEq(x, topR.x) && looseEq(y, topR.y)){
            return 2;
        } else if (looseEq(x, botL.x) && looseEq(y, botL.y)){
            return 3;
        } else if (looseEq(x, botR.x) && looseEq(y, botR.y)){
            return 4;
        }
        return -1;
    }

    private boolean looseEq(double a, double b){
        return a >= b - 100 && a <= b + 100;
    }
}
