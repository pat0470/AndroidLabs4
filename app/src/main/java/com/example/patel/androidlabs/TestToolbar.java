package com.example.patel.androidlabs;



import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;
import android.view.LayoutInflater;

public class TestToolbar extends AppCompatActivity {


    private EditText NewMsg;
    private String notificationMessage;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        view = findViewById(android.R.id.content);

       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    } public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        super.onOptionsItemSelected(mi);
        switch (mi.getItemId()) {
            case R.id.action_one:
                Log.i("Action One ", "Action one selected" );
                Snackbar.make(view, notificationMessage, Snackbar.LENGTH_LONG)
                        .show();
                break;

            case R.id.action_two:
                Log.i("Action Two", "Action Two Selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle(R.string.choice);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myintent = new Intent(TestToolbar.this, StartActivity.class);
                        startActivity(myintent);
                        finish();
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

                break;

            case R.id.action_three:
                Log.i("Action Three", "Action Three Selected");
                final AlertDialog.Builder builder1 = new AlertDialog.Builder(TestToolbar.this);
                LayoutInflater inflater = this.getLayoutInflater();
                View childView = inflater.inflate(R.layout.custome_dialog_layout,null);
                builder1.setView(childView);
                NewMsg = (EditText)childView.findViewById(R.id.edit_text);
                builder1.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        notificationMessage = NewMsg.getText().toString();

                    }
                });
                builder1.show();
                break;

            case R.id.action_four:
                Log.i("Action four Clicked", mi.getTitle().toString());
                Toast toast = Toast.makeText(getApplicationContext(),"version 1_1 keyur ", Toast.LENGTH_LONG); //this is the ListActivity
                toast.show();
                break;
        }
        return  true;
    }
}