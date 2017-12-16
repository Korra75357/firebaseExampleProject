package com.example.hp.firebase;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
   private Button login;
   private EditText email,password;
  private FirebaseAuth firebaseAuth;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth= FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() !=null){
            Toast.makeText(Main2Activity.this,"already logged in",Toast.LENGTH_SHORT).show();
        }
        login=(Button) findViewById(R.id.login);
       password=(EditText)findViewById(R.id.passwordin);
        email=(EditText)findViewById(R.id.mailin);

        login.setOnClickListener(this);
    }
public void userLogin() {
    String Email = email.getText().toString().trim();
    String Password = password.getText().toString().trim();
    firebaseAuth.signInWithEmailAndPassword(Email, Password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Main2Activity.this, "already logged in", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
}
    @Override
    public void onClick(View view) {
        if(view==login){
            userLogin();
        }

    }
}
