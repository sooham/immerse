package rafiz.sooham.immerse.views;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import rafiz.sooham.immerse.R;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @AfterViews
    protected void init(){

    }
}