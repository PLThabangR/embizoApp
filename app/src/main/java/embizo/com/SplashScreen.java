package embizo.com;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.annotation.AnnotationTypeMismatchException;

import static embizo.com.R.anim.splash_screen;

public class SplashScreen extends AppCompatActivity {
   // private ImageView tv;
    private ImageView iv;
   // private TextView iiv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

      //  tv =findViewById(R.id.backgroundPic);
        iv =findViewById(R.id.splash);
        iv.animate().scaleX(0.5f);
        iv.animate().scaleY(0.5f);
      //  iiv =findViewById(R.id.loading);

        Animation animation = AnimationUtils.loadAnimation(this, splash_screen);
      //  tv.startAnimation(animation);

        iv.animate().scaleX(1f).scaleY(1f).setDuration(4000);



        iv.startAnimation(animation);


       //iiv.startAnimation(animation);

        final Intent intent = new Intent(this,MainActivity.class);
        final Thread timer = new Thread() {



       // timer.start();
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(intent);
                    finish();

                }
            }

            };
        timer.start();
    }
}
