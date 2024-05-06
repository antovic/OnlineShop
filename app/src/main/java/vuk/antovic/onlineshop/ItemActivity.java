package vuk.antovic.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


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

        dbHelper = new DbHelper(this, DB, null, 1);

        ItemModel[] items = dbHelper.getItemsByCategory(this, category);
        for(ItemModel item : items)
        {
            adapter.add(item);
        }

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
}