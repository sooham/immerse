package rafiz.sooham.immerse.views;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import javax.microedition.khronos.opengles.GL10;

import rafiz.sooham.immerse.R;

@EActivity(R.layout.activity_view)
public class ViewActivity extends AppCompatActivity {

    @AfterViews
    protected void init(){
    }
}
