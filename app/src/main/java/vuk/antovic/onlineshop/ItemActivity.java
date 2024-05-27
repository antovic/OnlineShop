package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity implements View.OnClickListener {
    String category;
    String username;
    ListView listView;
    TextView textView;
    TextView emptyView;
    Button button;
    ItemAdapter adapter;
    DbHelper dbHelper;
    String DB = "OnlineShop.db";
    HTTPHelper httpHelper;
    private BroadcastReceiver finishActivityReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        // Initialize and register the BroadcastReceiver
        finishActivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                getIntent().putExtra("isSale", false);
                if ("com.example.FINISH_ACTIVITY".equals(intent.getAction())) {
                    finish();
                }
            }
        };
        IntentFilter filter = new IntentFilter("com.example.FINISH_ACTIVITY");
        registerReceiver(finishActivityReceiver, filter);

        category = getIntent().getExtras().getString("category", "category");
        username = getIntent().getExtras().getString("username", "username");
        emptyView = findViewById(R.id.emptyView);
        listView = findViewById(R.id.listViewItems);
        adapter = new ItemAdapter(this);
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);

        textView = findViewById(R.id.textViewCategory);
        textView.setText(category);
        button = findViewById(R.id.buttonBack);
        button.setOnClickListener(this);

        httpHelper = new HTTPHelper();
        new Thread(new Runnable() {
            public void run() {
                try {
                    JSONArray allItems = httpHelper.getItemByCategory(category);
                    try {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                if(allItems != null){
                                    ArrayList<ItemModel> items = new ArrayList<ItemModel>();
                                    for(int i = 0; i < allItems.length(); i++){
                                        try {
                                            JSONObject item = allItems.getJSONObject(i);
                                            String name = item.getString("name");
                                            String price = item.getString("price");
                                            String category = item.getString("category");
                                            String imageName = item.getString("imageName");
                                            ItemModel foundItem = new ItemModel(getDrawableFromImageName(ItemActivity.this, imageName), name, Integer.valueOf(price), category);
                                            items.add(foundItem);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(items == null)
                                    {
                                        Thread.currentThread().stop();
                                    }
                                    for(ItemModel item: items) {
                                        adapter.add(item);
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.buttonBack:
                finish();
                break;
        }
    }

    public static Drawable getDrawableFromImageName(Context context, String imageName) {
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        if (resourceId != 0) {
            return context.getResources().getDrawable(resourceId, null);
        } else {
            // Handle the case when the resource is not found
            return null;
        }
    }
}