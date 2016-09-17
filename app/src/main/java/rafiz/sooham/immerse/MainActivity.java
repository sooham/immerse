package rafiz.sooham.immerse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.opencv.core.Core;

public class MainActivity extends AppCompatActivity {

    static{ System.loadLibrary("opencv_java3"); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
