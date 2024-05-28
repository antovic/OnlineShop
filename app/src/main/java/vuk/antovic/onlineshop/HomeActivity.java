package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    // Elements
    Button homeButton;
    Button menuButton;
    Button accountButton;
    Button bagButton;
    AccountFragment accountFragment;
    HomeFragment homeFragment;
    MenuFragment menuFragment;
    IServiceBinder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        //Binding the service
        Intent intent = new Intent(HomeActivity.this, SaleService.class);
        bindService(intent, HomeActivity.this, Context.BIND_AUTO_CREATE);

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

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.d("utorak", "onServiceConnected");
        binder = IServiceBinder.Stub.asInterface(iBinder);
        try {
            binder.setUsername(getIntent().getStringExtra("username"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            Log.d("utorak", String.valueOf(binder.getSale()));
            if(binder.getSale())
            {
                menuButton.setBackgroundColor(Color.RED);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentLayout, menuFragment)
                        .commit();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        binder = null;
    }



    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    // Method to retrieve the binder
    public IServiceBinder getBinder() {
        return binder;
    }
}