package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText newPasswordEditText;
    EditText oldPasswordEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        // Init elements
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        oldPasswordEditText = findViewById(R.id.oldPasswordEditText);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
                //TODO
                break;
        }
    }
}