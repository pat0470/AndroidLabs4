package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;
import static com.example.patel.androidlabs.R.drawable.flag;

public class LoginActivity extends Activity {

    protected static final String ACTIVITY_NAME = "LoginActivity";
    EditText id,pass;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iv=findViewById(R.id.imageView);
        iv.setBackground(getResources().getDrawable(R.drawable.flag));

        id=findViewById(R.id.loginName);
        pass=findViewById(R.id.password);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        Button button1 = (Button) findViewById(R.id.button2);

        //Context context = getActivity();

        SharedPreferences sharedPreferences = getSharedPreferences("pref",
                Context.MODE_PRIVATE);
        if (sharedPreferences.contains("id")) {
            id.setText(sharedPreferences.getString("id", ""));
        }
        if (sharedPreferences.contains("pass")) {
            pass.setText(sharedPreferences.getString("pass", ""));

        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("pref",0);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("id",id.getText().toString());
                editor.putString("pass",pass.getText().toString());
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}