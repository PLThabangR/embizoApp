package embizo.com;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerActivity extends AppCompatActivity {

    private WebView mywebView;
    private Button btnSign;
    private EditText username;
    private EditText _email;
    ProgressBar progressBar;
    private FirebaseAuth auth;

    private EditText _password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.usernameText);
        _email= findViewById(R.id.emailText);
        _password = findViewById(R.id.passwordText);

        progressBar = findViewById(R.id.progressbar);




        //  mywebView = findViewById(R.id.webview);



        //  mywebView.setWebViewClient(new WebViewClient());
        //  mywebView.loadUrl("https://www.google.com");
        //  WebSettings webSettings = mywebView.getSettings();
        //  webSettings.setJavaScriptEnabled(true);
    }
    /* public void onBackPressed() {
         if(mywebView.canGoBack()){
             mywebView.goBack();
         }else{

             super.onBackPressed();
         }

     }
     */

    //Check if the user is currently signed in




    //create user to the database
    public void createUSer(){

        String emailInput = _email.getText().toString().trim();
       String passwordInput = _password.getText().toString().trim();

       if(emailInput.isEmpty()){
           _email.setError("Email is required");
            _email.requestFocus();
            return;
       }

       if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
           _email.setError("Enter a valid email");
           _email.requestFocus();
           return;


       }

        if(passwordInput.isEmpty()){
            _password.setError("Password is required");
            _password.requestFocus();
            return;
        }


        if(passwordInput.length()<6){
            _password.setError("Minimum length of password should be 6");
            _password.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(emailInput,passwordInput)
                .addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        progressBar.setVisibility(View.GONE);

                        if(task.isSuccessful()){
                            Toast.makeText(registerActivity.this, "welcome " , Toast.LENGTH_LONG).show();
                            startActivity(new Intent(registerActivity.this,HomeActivity.class));
                            finish();

                        }else{

                            //Show this if user is already registered
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                               // Toast.makeText(getApplicationContext(),"You are already registerd",Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),"Authentication failed "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"Authentication failed "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }




                    }
                });

    }


    public void logUserInn(View v) {

      //  Intent intent = new Intent(this,HomeActivity.class);
      // startActivity(intent);

       // Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        createUSer();

    }





}
