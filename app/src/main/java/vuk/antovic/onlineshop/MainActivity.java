package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginButton;
    Button registerButton;
    LoginFragment loginFragment;
    RegisterFragment registerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init buttons
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);

        //Init fragments
        loginFragment = LoginFragment.newInstance("LoginFragment", "LoginFragment");
        registerFragment = RegisterFragment.newInstance("RegisterFragment", "RegisterFragment");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.loginButton:
                loginButton.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentLayout,loginFragment, "fragment")
                        .commit();
                break;
            case R.id.registerButton:
                loginButton.setVisibility(View.INVISIBLE);
                registerButton.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentLayout,registerFragment, "fragment")
                        .commit();
                break;
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment");
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        loginButton.setVisibility(View.VISIBLE);
        registerButton.setVisibility(View.VISIBLE);
    }
}