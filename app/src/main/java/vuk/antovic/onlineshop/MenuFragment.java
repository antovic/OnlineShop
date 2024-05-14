package vuk.antovic.onlineshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment implements  AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ListView listView;
    ArrayAdapter<String> adapter;
    DbHelper dbHelper;
    String DB = "OnlineShop.db";
    HTTPHelper httpHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        listView = view.findViewById(R.id.listViewMenu);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
//        dbHelper = new DbHelper(getContext(), DB, null, 1);
//        String categories[] = dbHelper.getCategories();
//        if(categories == null || categories.length == 0)
//        {
//            addItems();
//            categories = dbHelper.getCategories();
//        }
//        for (String category : categories) {
//            adapter.add(category);
//        }
        httpHelper = new HTTPHelper();
        new Thread(new Runnable() {
            public void run() {
                try {
                    JSONArray items = httpHelper.getItems();
                    try {
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                if(items != null){
                                    ArrayList<String> categories = new ArrayList<String>();
                                    for(int i = 0; i < items.length(); i++){
                                        try {
                                            JSONObject item = items.getJSONObject(i);
                                            String category = item.getString("category");
                                            if(!categories.contains(category))
                                            {
                                                categories.add(category);
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(categories == null)
                                    {
                                        Thread.currentThread().stop();
                                    }
                                    for(String category: categories) {
                                        adapter.add(category);
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
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(getActivity(), ItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("category", adapter.getItem(position));
        bundle.putString("username", getActivity().getIntent().getExtras().getString("username", "username"));
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void addItems()
    {
        //Dogs
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.dry_food), "Dry food", 10000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.dry_food_big), "Dry food - XXL", 15000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.toy_bone), "Toy bone", 300, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.hippo), "Toy hippo", 500, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.ball), "Ball", 500, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.frisbee), "Frisbee", 1000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.brush), "Brush", 1000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.clippers), "Nail clippers", 750, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.chain), "Chain", 1100, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.collar), "Collar", 1050, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.leash), "Leash", 1000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.vest), "Vest", 3000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.box), "Box", 5000, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.shampoo), "Dog shampoo", 2500, "Dogs"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.wet_wipes), "Wet wipes", 200, "Dogs"));

        //Cats
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.can), "Canned food", 500, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cat_food), "Dry food", 5000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cat_food_big), "Dry food - XXL", 7500, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.whiskas), "Whiskas", 1000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.roller), "Roller", 1500, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.catnip), "Catnip", 2000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bed), "Bed", 10000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bowl), "Bowl", 750, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.igloo), "Cat house - Igloo", 8100, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.scratcher), "Scratcher", 2000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.scratcher_big), "Scratcher - XXL", 6000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.mouse), "Toy mouse", 300, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bug), "Toy bug", 500, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.puzzle), "Toy puzzle", 2000, "Cats"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.ladybug), "Toy ladybug", 600, "Cats"));

        //Small pets
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage_3), "Cage 3", 7500, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage_4), "Cage 4", 6000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage_2), "Cage 2", 5000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage_5), "Cage 5", 4500, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage), "Cage", 3000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage_ball), "Ball cage", 2000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.cage_wood), "Wooden cage", 10000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.water_tank), "Water tank", 750, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.plastic_bowl), "Plastic bowl", 800, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.rat_bowl), "Hamster bowl", 1200, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.toilet), "Hamster toilet", 3000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.rat_tunnel), "Hamster tunnel", 300, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.rat_bridge), "Hamster bridge", 500, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.rabbit_leash), "Rabbit leash", 2000, "Small pets"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.carrot_holder), "Carrot holder", 1000, "Small pets"));

        //Birds
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage), "Bird cage", 3000, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage_2), "Bird cage 2", 5000, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage_3), "Bird cage 3", 7500, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage_4), "Bird cage 4", 6000, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage_5), "Bird cage 5", 4500, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage_6), "Bird cage 6", 2000, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_cage_7), "Bird cage 7", 10000, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_climber), "Bird climber", 1500, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_feeder), "Bird feeder", 500, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_feeder_2), "Bird feeder 2", 400, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_nest), "Bird nest", 4000, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_nest_2), "Bird nest 2", 4500, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_food), "Bird food", 3500, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_toy), "Parrot toy", 600, "Birds"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.bird_web), "Web for catching birds", 500, "Birds"));

        //Aquatic
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_1), "Aquarium 1", 4000, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_2), "Aquarium 2", 5500, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_3), "Aquarium 3", 7000, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_air_pump), "Air pump", 1200, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_vacuum_holder), "Vacuum holder", 900, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_filter), "Filter", 800, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_light), "Aquarium LED's", 1000, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_light_black), "Aquarium black light", 1200, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_gravel), "Gravel", 100, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_vase), "Vase decoration", 600, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_wood), "Wood decoration", 500, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_plants), "Plants", 400, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_plants_2), "Plants 2", 450, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_dino), "Toy dino", 600, "Aquatic"));
        dbHelper.insertItem(new ItemModel(getActivity().getDrawable( R.drawable.aquarium_ship), "Toy ship", 700, "Aquatic"));

    }

}