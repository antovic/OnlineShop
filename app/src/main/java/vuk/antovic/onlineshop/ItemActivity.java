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
    ListView listView;
    TextView textView;
    TextView emptyView;
    Button button;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);


        category = getIntent().getExtras().getString("category", "category");

        emptyView = findViewById(R.id.emptyView);
        listView = findViewById(R.id.listViewItems);
        adapter = new ItemAdapter(this);
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);

        textView = findViewById(R.id.textViewCategory);
        textView.setText(category);
        button = findViewById(R.id.buttonBack);
        button.setOnClickListener(this);

        if(category.equals("Dogs"))
        {
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.dry_food), "Dry food", 10000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.dry_food_big), "Dry food - XXL", 15000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.toy_bone), "Toy bone", 300));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.hippo), "Toy hippo", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.ball), "Ball", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.frisbee), "Frisbee", 1000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.brush), "Brush", 1000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.clippers), "Nail clippers", 750));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.chain), "Chain", 1100));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.collar), "Collar", 1050));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.leash), "Leash", 1000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.vest), "Vest", 3000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.box), "Box", 5000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.shampoo), "Dog shampoo", 2500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.wet_wipes), "Wet wipes", 200));

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