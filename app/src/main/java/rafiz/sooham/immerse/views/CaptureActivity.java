package rafiz.sooham.immerse.views;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import rafiz.sooham.immerse.R;
import rafiz.sooham.immerse.core.Cons;

@EActivity(R.layout.activity_capture)
public class CaptureActivity extends AppCompatActivity {

    private static final int PICTURE_REQUEST = 1;

    private final File imageFile = new File(Environment.getExternalStorageDirectory(),
            "test/test.jpg");

    @AfterViews
    protected void init(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
            startActivityForResult(intent, PICTURE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICTURE_REQUEST) {
            if (resultCode == RESULT_OK) {
                Intent i = new Intent(this, SetActivity_.class);
                i.putExtra(Cons.FILEPATH_EXTRA, imageFile.getAbsolutePath());
                startActivity(i);
            }
        }
    }
}
