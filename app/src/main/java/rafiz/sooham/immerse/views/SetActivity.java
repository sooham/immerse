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
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                    {
                        // start
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:
                    {
                        // drag
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    {
                        // end
                        break;
                    }
                }
                return true;
            }
        });

        root = new Position(view.getWidth()/2, view.getHeight()/2);
        topL = new Position(view.getWidth()/4, view.getHeight()/4);
        topR = new Position(3*view.getWidth()/4, view.getHeight()/4);
        botL = new Position(view.getWidth()/4, 3*view.getHeight()/4);
        botR = new Position(3*view.getWidth()/4, 3*view.getHeight()/4);

        view.addNode(root.x, root.y);
    }
}
