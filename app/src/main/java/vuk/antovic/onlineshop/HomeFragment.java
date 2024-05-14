package vuk.antovic.onlineshop;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    DbHelper dbHelper;
    String DB = "OnlineShop.db";
    HTTPHelper httpHelper;
    EditText categoryNameEditText;
    EditText itemNameEditText, itemPriceEditText, itemCategoryEditText, itemImageEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dbHelper = new DbHelper(getContext(), DB, null, 1);

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcomeUsername = view.findViewById(R.id.usernameTextView);
        LinearLayout adminView = view.findViewById(R.id.adminView);
        String username = getActivity().getIntent().getExtras().getString("username", "username");

        httpHelper = new HTTPHelper();
        Button addItemButton = view.findViewById(R.id.addItemButton);
        Button addCategoryButton = view.findViewById(R.id.addCategoryButton);
        addCategoryButton.setOnClickListener(this);
        addItemButton.setOnClickListener(this);
        categoryNameEditText = view.findViewById(R.id.categoryNameEditText);
        itemNameEditText = view.findViewById(R.id.itemNameEditText);
        itemPriceEditText = view.findViewById(R.id.itemPriceEditText);
        itemCategoryEditText = view.findViewById(R.id.itemCategoryEditText);
        itemImageEditText = view.findViewById(R.id.itemImageEditText);
        adminView.setVisibility(dbHelper.isAdmin(username)?View.VISIBLE:View.INVISIBLE);
        welcomeUsername.setText(username);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.addCategoryButton:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            if(httpHelper.createCategory(String.valueOf(categoryNameEditText.getText())))
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Category created", Toast.LENGTH_SHORT).show();
                                        categoryNameEditText.setText("");
                                    }
                                });
                            }
                            else
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Failed to create a category", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;

            case R.id.addItemButton:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            if(httpHelper.createItem(String.valueOf(itemNameEditText.getText()),
                                    String.valueOf(itemPriceEditText.getText()),
                                    String.valueOf(itemCategoryEditText.getText()),
                                    String.valueOf(itemImageEditText.getText())
                                    ))
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Item created", Toast.LENGTH_SHORT).show();
                                        itemNameEditText.setText("");
                                        itemPriceEditText.setText("");
                                        itemCategoryEditText.setText("");
                                        itemImageEditText.setText("");
                                    }
                                });
                            }
                            else
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Failed to create an item", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
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