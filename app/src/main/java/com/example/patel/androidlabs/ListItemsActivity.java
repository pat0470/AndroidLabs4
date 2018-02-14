package com.example.patel.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    protected static final String ACTIVITY_NAME = "ListItemActivity";
    // int requestCode, resultCode;
    static final int REQUEST_IMAGE_CAPTURE = 50;
    private ImageButton imagebutton;
    private CheckBox cBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        imagebutton = (ImageButton) findViewById(R.id.imageButton);
        // Button ok = (Button)
        cBox = (CheckBox) findViewById(R.id.checkBox2);

        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
//                Intent intent = new Intent(ListItemActivity.this, ListItemActivity.class);
//                startActivityForResult(intent, 50);
            }
        });


        Switch sw = (Switch)  findViewById(R.id.switch2);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                //Log.v("Switch State=on", "Switch is ON"+isChecked);
                if(isChecked)
                {
                    CharSequence text = "Switch is On";// "Switch is Off"
                    int duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration); //this is the ListActivity
                    toast.show(); //display your message box
                }
                else
                {
                    CharSequence text = "Switch is Off";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(getApplicationContext(), text, duration); //this is the ListActivity
                    toast.show(); //display your message box
                }

            }});

        cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);


                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message); //Add a dialog message to strings.xml

                builder.setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent resultIntent = new Intent(ListItemsActivity.this,StartActivity.class);
                        CharSequence text=getResources().getString(R.string.Response);
                        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
                        resultIntent.putExtra("Response", "Here is my response");
                        setResult(Activity.RESULT_OK, resultIntent);
                        startActivity(resultIntent);
                        finish();

                    }
                });
                builder.setNegativeButton(R.string.CheckBox, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.show();

            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagebutton.setImageBitmap(imageBitmap);
        }

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
