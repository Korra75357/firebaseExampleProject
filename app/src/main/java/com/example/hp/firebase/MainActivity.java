package com.example.hp.firebase;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
private EditText email,password;
 private Button signup;
 private TextView signin;
 private ProgressDialog progressBar;
 private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        progressBar=new ProgressDialog(this);
        email=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.signupHere);
        signin=(TextView)findViewById(R.id.login);
        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }
   private void registerHere (){
       String Email =email.getText().toString().trim();
       String Password =password.getText().toString().trim();
       if(TextUtils.isEmpty(Email)){
           Toast.makeText(this,"enter email id",Toast.LENGTH_SHORT).show();
           return;
       }
       if(TextUtils.isEmpty(Password)){
           Toast.makeText(this,"enter password ",Toast.LENGTH_SHORT).show();
           return;
       }
       progressBar.setMessage("please wait while registering");
       progressBar.show();
       firebaseAuth.createUserWithEmailAndPassword(Email,Password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(MainActivity.this,"Registration is succussful",Toast.LENGTH_SHORT).show();
                           Log.e("tag","inside onComplete");
                       }
                       else{
                           Toast.makeText(MainActivity.this,"Registration is not succussful",Toast.LENGTH_SHORT).show();
                       }
                   }
               });
       Log.e("tag","inside onComplete");
   }
   private void signHere (){

   }
    @Override
    public void onClick(View view) {
        if(view==signup){
            registerHere();
        }
        if(view ==signin){
            signHere();
        }
    }

}
