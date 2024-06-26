package vuk.antovic.onlineshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    Button submitButton;
    EditText usernameEditText;
    EditText passwordEditText;

    DbHelper dbHelper;
    String DB = "OnlineShop.db";

    Toast toast = null;

    HTTPHelper httpHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        //Init elements
        submitButton = view.findViewById(R.id.loginSubmit);
        submitButton.setOnClickListener(this);
        usernameEditText = view.findViewById(R.id.loginUsername);
        passwordEditText = view.findViewById(R.id.loginPassword);

        dbHelper = new DbHelper(getActivity(), DB, null, 1);
        httpHelper = new HTTPHelper();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginSubmit:
                String username, password;
                username = String.valueOf(usernameEditText.getText());
                password = String.valueOf(passwordEditText.getText());


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try
                        {
                            if(!httpHelper.login(username,password))
                            {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(toast != null) toast.cancel();
                                        toast = Toast.makeText(getActivity(), "Wrong username or password.", Toast.LENGTH_SHORT);
                                        toast.show();

                                    }
                                });
                                Thread.currentThread().stop();
                            }
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            Bundle loginInfo = new Bundle();
                            loginInfo.putString("username", username);
                            intent.putExtras(loginInfo);
                            startActivity(intent);
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