package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText newPasswordEditText;
    EditText oldPasswordEditText;
    Button saveButton;


    DbHelper dbHelper;
    String DB = "OnlineShop.db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        // Init elements
        newPasswordEditText = findViewById(R.id.newPasswordEditText);
        oldPasswordEditText = findViewById(R.id.oldPasswordEditText);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        dbHelper = new DbHelper(this, DB, null, 1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
                String username = getIntent().getStringExtra("username");
                String oldPassword = String.valueOf(oldPasswordEditText.getText());
                String newPassword = String.valueOf(newPasswordEditText.getText());
                if(!dbHelper.validLogin(username, oldPassword))
                {
                    Toast.makeText(this, "Invalid old password.", Toast.LENGTH_SHORT).show();
                }
                if(newPassword.length() < 6) {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                }
                dbHelper.updatePassword(username, newPassword);
                Toast.makeText(this, "Password successfully changed", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}