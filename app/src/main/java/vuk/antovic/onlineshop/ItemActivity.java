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
        else if(category.equals("Cats"))
        {
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.can), "Canned food", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cat_food), "Dry food", 5000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cat_food_big), "Dry food - XXL", 7500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.whiskas), "Whiskas", 1000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.roller), "Roller", 1500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.catnip), "Catnip", 2000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bed), "Bed", 10000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bowl), "Bowl", 750));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.igloo), "Cat house - Igloo", 8100));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.scratcher), "Scratcher", 2000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.scratcher_big), "Scratcher - XXL", 6000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.mouse), "Toy mouse", 300));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bug), "Toy bug", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.puzzle), "Toy puzzle", 2000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.ladybug), "Toy ladybug", 600));
        }
        else if(category.equals("Small pets"))
        {
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage), "Cage", 3000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage_2), "Cage 2", 5000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage_3), "Cage 3", 7500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage_4), "Cage 4", 6000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage_5), "Cage 5", 4500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage_ball), "Ball cage", 2000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.cage_wood), "Wooden cage", 10000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.water_tank), "Water tank", 750));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.plastic_bowl), "Plastic bowl", 800));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.rat_bowl), "Hamster bowl", 1200));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.toilet), "Hamster toilet", 3000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.rat_tunnel), "Hamster tunnel", 300));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.rat_bridge), "Hamster bridge", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.rabbit_leash), "Rabbit leash", 2000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.carrot_holder), "Carrot holder", 1000));
        }
        else if(category.equals("Birds"))
        {
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage), "Bird cage", 3000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage_2), "Bird cage 2", 5000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage_3), "Bird cage 3", 7500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage_4), "Bird cage 4", 6000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage_5), "Bird cage 5", 4500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage_6), "Bird cage 6", 2000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_cage_7), "Bird cage 7", 10000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_climber), "Bird climber", 1500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_feeder), "Bird feeder", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_feeder_2), "Bird feeder 2", 400));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_nest), "Bird nest", 4000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_nest_2), "Bird nest 2", 4500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_food), "Bird food", 3500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_toy), "Parrot toy", 600));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.bird_web), "Web for catching birds", 500));
        }
        else if(category.equals("Aquatic"))
        {
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_1), "Aquarium 1", 4000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_2), "Aquarium 2", 5500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_3), "Aquarium 3", 7000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_air_pump), "Air pump", 1200));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_vacuum_holder), "Vacuum holder", 900));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_filter), "Filter", 800));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_light), "Aquarium LED's", 1000));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_light_black), "Aquarium black light", 1200));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_gravel), "Gravel", 100));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_vase), "Vase decoration", 600));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_wood), "Wood decoration", 500));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_plants), "Plants", 400));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_plants_2), "Plants 2", 450));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_dino), "Toy dino", 600));
            adapter.add(new ItemModel(ContextCompat.getDrawable(this, R.drawable.aquarium_ship), "Toy ship", 700));
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