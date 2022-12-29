package com.example.banglatalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private EditText PhoneNumber, Code;
    private Button SendCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verifyCallBack;

    String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        PhoneNumber = (EditText) findViewById(R.id.verifyPhone);
        Code = (EditText) findViewById(R.id.verifyCode);
        SendCode = (Button) findViewById(R.id.verifyButton);

        login();

        SendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(verify != null)
                {
                    verifyPhoneNumberByCode();
                }
                else {
                    numberVerification();
                }

            }
        });



        verifyCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                sign_in(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verify = s;
                Code.setText("Verify Code");
            }
        };



    }

    private void sign_in(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    login();
                }
            }
        });

    }

    private void login() {
        FirebaseUser user =  FirebaseAuth.getInstance().getCurrentUser();
        if(user != null)
        {
            startActivity(new Intent(getApplicationContext(), MainPageActivity.class));
            finish();
            return;
        }
    }

    private void verifyPhoneNumberByCode()
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verify, Code.getText().toString());
        sign_in(credential);
    }

    private void numberVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                PhoneNumber.getText().toString(),
                60, TimeUnit.SECONDS, this, verifyCallBack
        );
    }
}