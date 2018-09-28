package embizo.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.HashMap;
import java.util.Map;

import static embizo.com.R.anim.splash_screen;

public class MainActivity extends AppCompatActivity {
    private EditText email, password;
    private ImageView logo;
    FirebaseAuth auth;
    ProgressBar progressbar;

   /*private static final Pattern PASSWORD_PATTERN
            = Pattern.compile("^"+
                  //  "(?=.*[a-z])"+
                    //"(?=.*[A-Z])"+
                    //"(?=.*[0-9])"+
                    //"(?=.*[@#$%&+=])"+
                        "(?=.*[\\s+$=])"+   //must not contain any white spaces
                        "(?=.*[a-zA-Z])"+   //must contain any character
                   // ".{2,}"+
                    "$");

                    */


    private Button logUserInn;
    //Since we use a volley library we need to request que
    //private RequestQueue requestQue;
    //A string containing url to the PHP Script
    //private static final String URL ="";
    //private StringRequest request;


    public void logUserInn(View v) {

        userLogin();
        finish();

        // connectUserToPhp();

    }

    public void connectUserToPhp() {

        String url = "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("Succes")) {
                    Toast.makeText(MainActivity.this, "Login Success ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Login Failed ", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("UserName ", email.getText().toString().trim());
                params.put("Password", password.getText().toString().trim());
                return super.getParams();
            }
        };

        requestQueue.add(stringRequest);

    }


    public void AnimationLogo() {

        Animation animation = AnimationUtils.loadAnimation(this, splash_screen);
        logo.startAnimation(animation);
    }

    public void signUserInn(View v) {
        Intent intent = new Intent(this, registerActivity.class);
        startActivity(intent);
    }

    public void resetPassword(View v) {

        // Intent intent = new Intent(this,SignActivity.class);
        // startActivity(intent);
    }

    private boolean validateEmail() {
        String emailInput = email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;

        } else {
            email.setError(null);
            return true;
        }


    }


    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (passwordInput.length() < 3) {
            password.setError("Password too short");
            return false;

        } else {
            password.setError(null);
            return true;
        }


    }

    private void userLogin() {


        String emailInput = email.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();


        if (emailInput.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;


        }

        if (passwordInput.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }


        if (passwordInput.length() < 6) {
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;

        }

        progressbar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(emailInput,passwordInput)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        progressbar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "welcome ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            finish();


                        }else{

                            Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }

                });
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo= findViewById(R.id.appName);
        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.firstnameText);
        password=findViewById(R.id.passwordText);
        progressbar = findViewById(R.id.progressbar);
         //AnimationLogo();



    }
}
