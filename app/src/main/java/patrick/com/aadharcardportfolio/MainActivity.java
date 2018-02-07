package patrick.com.aadharcardportfolio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.os.Handler;

public class MainActivity extends Activity {
    private static int SPLASH_TIME_OUT=4000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ImageView im = findViewById(R.id.jkl);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, Main.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

