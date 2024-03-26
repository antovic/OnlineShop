package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView usernameTextView;
    TextView emailTextView;
    Button passwordButton;
    Button endSessionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Init elements
        usernameTextView = findViewById(R.id.usernameTextViewProfile);
        emailTextView = findViewById(R.id.emailTextViewProfile);
        passwordButton = findViewById(R.id.passwordButton);
        endSessionButton = findViewById(R.id.endSessionButton);

        // Setup account information
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username", "username");
        usernameTextView.setText(username);
        String email = bundle.getString("email", "email");
        emailTextView.setText(email);

        // Set on click listeners
        passwordButton.setOnClickListener(this);
        endSessionButton.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.passwordButton:
                intent = new Intent(this, PasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.endSessionButton:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


}