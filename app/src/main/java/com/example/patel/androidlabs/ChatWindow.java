package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatWindow extends Activity {



    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        final ChatAdapter messageAdapter =new ChatAdapter( this );

        final ListView ChatList = (ListView) findViewById(R.id.chatView);

        ChatList.setAdapter(messageAdapter);

        final EditText ChatEdit = (EditText) findViewById(R.id.ChatEdit);

        Button Chatbtn1 = (Button) findViewById(R.id.Chatbtn);


       //  ChatAdapter capt = new ChatAdapter(messageAdapter);

//        ChatEdit.setOnClickListener(new AdapterView.OnItemClickListener(){
//            // This is a callback function for when the user clickes on a row in the table:
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //position is the position that was clicked on. id is the database item   
//                // of the item at position position. id is whatever your getItemId(int position) returns.
//              //g.w("",""+position);
//                Log.w("ListView clicked", "Position:" + position);
//            }
//        });


        //for (int i = 0; i < ChatEdit.length(); i++) {

            //list.add(ChatEdit.toString());

            Chatbtn1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    list.add(ChatEdit.getText().toString());

                    ChatEdit.setText("");

                    messageAdapter.notifyDataSetChanged();

                    //Intent intent = new Intent(ChatWindow.this,ChatWindow.class);
                    //startActivity(intent);

                }
            });


        }

    public class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }


        @Override
        public int getCount() {
            return list.size();
        }


        @Override
        public String getItem(int position) {
            return list.get(position);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming,parent,false);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing,parent,false);

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  ); // get the string at position
            return result;
        }

        @Override
        public long getItemId(int position) {
            return (position);
        }
    }
}

