package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    // Elements
    LinearLayout welcomeLayout;
    TextView welcomeTextView;
    TextView usernameTextView;
    Button homeButton;
    Button menuButton;
    Button accountButton;
    Button bagButton;
    AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();

        // Init elements
        welcomeLayout = findViewById(R.id.welcomeLayout);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(bundle.getString("username","username"));
        homeButton = findViewById(R.id.homeButton);
        menuButton = findViewById(R.id.menuButton);
        accountButton = findViewById(R.id.accountButton);
        bagButton = findViewById(R.id.bagButton);
        accountFragment = AccountFragment.newInstance("AccountFragment", "AccountFragment");
        // Set onclickListeners
        homeButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        accountButton.setOnClickListener(this);
        bagButton.setOnClickListener(this);

        // Styling
        homeButton.setBackgroundColor(Color.BLUE);
        homeButton.setTextColor(Color.WHITE);
        menuButton.setEnabled(false);
        accountButton.setBackgroundColor(Color.BLUE);
        accountButton.setTextColor(Color.WHITE);
        bagButton.setEnabled(false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.homeButton:
                welcomeLayout.setVisibility(View.VISIBLE);
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment");
                if(fragment != null)
                    getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                break;
            case R.id.menuButton:
                //TODO - Menu button logic
                break;
            case R.id.accountButton:
                welcomeLayout.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, accountFragment, "fragment")
                        .commit();
                break;
            case R.id.bagButton:
                //TODO - Bag button logic
                break;
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment");
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        welcomeLayout.setVisibility(View.VISIBLE);
    }

}