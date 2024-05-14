package vuk.antovic.onlineshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    EditText usernameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    Button submitButton;
    CheckBox isAdminCheckBox;

    DbHelper dbHelper;
    String DB = "OnlineShop.db";
    HTTPHelper httpHelper;

    Toast toast = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        usernameEditText = view.findViewById(R.id.registerUsername);
        emailEditText = view.findViewById(R.id.registerEmail);
        passwordEditText = view.findViewById(R.id.registerPassword);
        submitButton = view.findViewById(R.id.registerSubmit);
        isAdminCheckBox = view.findViewById(R.id.isAdminCheckBox);
        submitButton.setOnClickListener(this);
        httpHelper = new HTTPHelper();
        dbHelper = new DbHelper(getContext(), DB, null, 1);

        return view;
    }

    public static boolean isValidEmailAddress(String email) {
        Pattern regexPattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = regexPattern.matcher(email);
        return matcher.matches();
    }

    public boolean validInput(String username, String email, String password)
    {
        boolean usernameClause = username.length() >= 5 && username.length() <= 20;
        boolean emailClause = isValidEmailAddress(email);
        boolean passwordClause = password.length() >= 5;
        return usernameClause && emailClause && passwordClause;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.registerSubmit:
                String username, email, password;
                username = String.valueOf(usernameEditText.getText());
                email = String.valueOf(emailEditText.getText());
                password = String.valueOf(passwordEditText.getText());

                if(!validInput(username, email, password))
                {
                    if (toast != null) toast.cancel();
                    toast = Toast.makeText(getActivity(), "Invalid input, try again.", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }
                boolean isAdmin = isAdminCheckBox.isChecked();
                final String[] id = new String[1];
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            JSONObject jsonObject = httpHelper.createUser(username, email, password, isAdmin);
                            if(jsonObject == null)
                            {
                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        public void run() {
                                            Toast.makeText(getActivity(), "Failed to register", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Thread.currentThread().stop();
                            }
                            try {
                                getActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getActivity(), "Registered successfully", Toast.LENGTH_SHORT).show();
                                        try {
                                            id[0] = jsonObject.getString("_id");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            dbHelper.insertUser(new User(username, email, password), isAdmin, id[0]);
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            Bundle loginInfo = new Bundle();
                            loginInfo.putString("username", username);
                            intent.putExtras(loginInfo);
                            startActivity(intent);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}