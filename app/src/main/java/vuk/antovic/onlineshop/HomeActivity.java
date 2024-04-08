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
    Button homeButton;
    Button menuButton;
    Button accountButton;
    Button bagButton;
    AccountFragment accountFragment;
    HomeFragment homeFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();

        // Init elements
        homeButton = findViewById(R.id.homeButton);
        menuButton = findViewById(R.id.menuButton);
        accountButton = findViewById(R.id.accountButton);
        bagButton = findViewById(R.id.bagButton);
        accountFragment = AccountFragment.newInstance("AccountFragment", "AccountFragment");
        homeFragment = HomeFragment.newInstance("HomeFragment", "HomeFragment");
        menuFragment = MenuFragment.newInstance("MenuFragment", "MenuFragment");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentLayout, homeFragment)
                .commit();
        // Set onclickListeners
        homeButton.setOnClickListener(this);
        menuButton.setOnClickListener(this);
        accountButton.setOnClickListener(this);
        bagButton.setOnClickListener(this);

        // Styling
        homeButton.setBackgroundColor(Color.BLUE);
        homeButton.setTextColor(Color.WHITE);
        menuButton.setBackgroundColor(Color.BLUE);
        menuButton.setTextColor(Color.WHITE);
        accountButton.setBackgroundColor(Color.BLUE);
        accountButton.setTextColor(Color.WHITE);
        bagButton.setEnabled(false);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        menuButton.setBackgroundColor(Color.BLUE);
        switch (v.getId()){
            case R.id.homeButton:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, homeFragment)
                        .commit();
                break;
            case R.id.menuButton:
                //TODO - Menu button logic
                menuButton.setBackgroundColor(Color.RED);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, menuFragment)
                        .commit();
                break;
            case R.id.accountButton:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, accountFragment)
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
    }

}