package rafiz.sooham.immerse.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import rafiz.sooham.immerse.R;
import rafiz.sooham.immerse.core.Core;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @AfterViews
    protected void init(){
        Core.INIT(this, this);
    }

    @Click(R.id.start_btn)
    protected void onCapture(){
        startActivity(new Intent(this, CaptureActivity_.class));
    }
}
