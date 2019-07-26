package com.example.byclothes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    private EditText InputMail, Inputpassword, InputUsername, InputPhone;
    private Button createAccBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        InputMail = findViewById(R.id.gmail_ET_ID);
        Inputpassword = findViewById(R.id.password_ET_ID);
        InputUsername = findViewById(R.id.username_ET_ID);
        InputPhone = findViewById(R.id.phone_ET_ID);
        createAccBtn = findViewById(R.id.create_acc_BTN_ID);

        createAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    public void createAccount() {
        String username = InputUsername.getText().toString();
        String password = Inputpassword.getText().toString();
        String mail = InputMail.getText().toString();
        String phone = InputPhone.getText().toString();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter username.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter Phone number.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mail)) {
            Toast.makeText(this, "Please enter mail id.", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter Password.", Toast.LENGTH_SHORT).show();
        } else {
            //Data entry into firebase
            validateDetails(username, mail, phone, password);
        }
    }

    private void validateDetails(final String username, final String mail, final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(!((dataSnapshot.child("Users").child(phone).exists())))//&&(dataSnapshot.child("Users").child(mail).child(phone).exists()))
                {
                    HashMap<String,Object> userDataMap = new HashMap<>();
                    userDataMap.put("username",username);
                    userDataMap.put("phone",phone);
                    userDataMap.put("mail",mail);
                    userDataMap.put("password",password);

                    RootRef.child("Users").child(phone).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SignUp.this,"Account created !",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUp.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(SignUp.this,"Error, Try again",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(SignUp.this,"This phone number or mail id is already in use",Toast.LENGTH_SHORT).show();

                    Intent wrongSignUpIntent = new Intent(SignUp.this,MainActivity.class);
                    startActivity(wrongSignUpIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}