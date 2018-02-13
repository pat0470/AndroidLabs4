package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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





      final  ListView ChatList = (ListView) findViewById(R.id.chatView);


         ChatList.setAdapter(messageAdapter);

        final EditText ChatEdit = (EditText) findViewById(R.id.ChatEdit);

         Button Chatbtn1 = (Button) findViewById(R.id.Chatbtn);


        for (int i = 0; i < ChatEdit.length(); i++) {

            list.add(ChatEdit.toString());

            Chatbtn1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    list.add(ChatEdit.toString());
                    messageAdapter.notifyDataSetChanged();
                    ChatEdit.setText("");

                    Intent intent = new Intent(ChatWindow.this,ChatWindow.class);

                }
            });


        }




    }

   private class ChatAdapter extends ArrayAdapter<String> {





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
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing,null);


            TextView message = (TextView)result.findViewById(R.id.chatView);
            message.setText(   getItem(position)  ); // get the string at position
            return result;




        }

        @Override
        public long getItemId(int position) {
            return (position);
        }
    }
}