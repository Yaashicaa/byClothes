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

import com.example.byclothes.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText Lphone,Lpassword;
    private String parentDb;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Lphone = findViewById(R.id.Lphone_ID);
        Lpassword = findViewById(R.id.Lpassword_ID);
        loginBtn = findViewById(R.id.login_BTN_ID);

        Intent intent = getIntent();
        parentDb = intent.getStringExtra("parentDb");
    }


    public void loginActivity(View v)
    {
        String phone = Lphone.getText().toString();
        String password = Lpassword.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please enter Phone number.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter Password.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            allowAccessToAccount(phone,password);
        }
    }

    public void allowAccessToAccount(final String phone, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.child(parentDb).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDb).child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone))
                    {
                        if(usersData.getPassword().equals(password))
                        {
                            if(parentDb.equals("Users"))
                            {
                                Toast.makeText(Login.this,"Logged in!", Toast.LENGTH_SHORT).show();
                                Intent home_intent = new Intent(Login.this,HomeProduct.class);
                                startActivity(home_intent);
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Logged in!", Toast.LENGTH_SHORT).show();
                                Intent admin_pro_intent = new Intent(Login.this,AdminProduct.class);
                                startActivity(admin_pro_intent);
                            }
                        }
                    }
                }
                else
                {
                    Toast.makeText(Login.this, "Account doesn't exist",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }

}
