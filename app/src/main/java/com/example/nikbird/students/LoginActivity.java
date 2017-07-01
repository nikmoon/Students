package com.example.nikbird.students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity{

    private Button buttonLogin;
    private Button buttonRegistration;
    private EditText editTextLogin;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLogin = (Button) findViewById(R.id.btnLoginTry);
        buttonRegistration = (Button) findViewById(R.id.btnRegistration);
        editTextLogin = (EditText) findViewById(R.id.etLogin);
        editTextPassword = (EditText) findViewById(R.id.etPassword);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();
                if (authenticated(login, password)) {
//                    Intent intent = new Intent(view.getContext(), SelectionActivity.class);
                    Intent intent = new Intent(view.getContext(), ActivityLists.class);
                    startActivity(intent);
                }
            }
        });

        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Проверка данных, введенных пользователей
     *
     * @param login
     * @param password
     * @return
     */
    private boolean authenticated(String login, String password) {
        return true;
    }
}
