package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText newPasswordEditText;
    EditText oldPasswordEditText;
    Button saveButton;


    Toast toast = null;


    DbHelper dbHelper;
    String DB = "OnlineShop.db";
    HTTPHelper httpHelper;
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
        httpHelper = new HTTPHelper();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
                String username = getIntent().getStringExtra("username");
                String oldPassword = String.valueOf(oldPasswordEditText.getText());
                String newPassword = String.valueOf(newPasswordEditText.getText());

                if(newPassword.length() < 5) {
                    Toast.makeText(this, "New password too short", Toast.LENGTH_SHORT).show();
                    break;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            if(!httpHelper.changePassword(username, oldPassword, newPassword))
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(toast != null) toast.cancel();
                                        toast = Toast.makeText(PasswordActivity.this, "Wrong username or password.", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                                Thread.currentThread().stop();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    dbHelper.updatePassword(username, newPassword);
                                    if(toast != null) toast.cancel();
                                    toast = Toast.makeText(PasswordActivity.this, "Password successfully changed", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                            });

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}