package rafiz.sooham.immerse.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;

import rafiz.sooham.immerse.R;
import rafiz.sooham.immerse.core.Cons;

@EActivity(R.layout.activity_set)
public class SetActivity extends AppCompatActivity {

    @ViewById(R.id.view)
    ImageView view;

    private File imageFile;

    @AfterViews
    protected void init(){
        String path = getIntent().getStringExtra(Cons.FILEPATH_EXTRA);
        imageFile = path.isEmpty() ? null : new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        view.setImageBitmap(bitmap);
        Logger.d("Hello");
    }
}
