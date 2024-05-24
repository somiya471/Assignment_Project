package com.example.assignmentproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText username,name,mobileno,password;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        mobileno = findViewById(R.id.mobileno);
        password = findViewById(R.id.password);
        register_btn = findViewById(R.id.registerButton);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserName(username.getText().toString());
                userEntity.setName(name.getText().toString());
                userEntity.setMobileNo(mobileno.getText().toString());
                userEntity.setPassword(password.getText().toString());
                if (validateInput(userEntity)) {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                }else {
                    Toast.makeText(RegisterActivity.this, "Fill all the fields !!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private Boolean validateInput(UserEntity userEntity) {
        if (userEntity.getUserName().isEmpty() ||
        userEntity.getName().isEmpty() ||
        userEntity.getMobileNo().isEmpty() ||
        userEntity.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }
}