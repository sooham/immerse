package rafiz.sooham.immerse;

import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @AfterViews
    protected void init(){
        Logger.init("DEBUG").hideThreadInfo().methodCount(4);
    }
}
