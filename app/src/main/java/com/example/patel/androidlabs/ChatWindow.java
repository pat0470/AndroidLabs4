package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.Context;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);


        ChatAdapter messageAdapter =new ChatAdapter( this );
        ListView.




        ListView ChatList = (ListView) findViewById(R.id.chatview);



        final EditText ChatEdit = (EditText) findViewById(R.id.ChatEdit);

        final Button Chatbtn = (Button) findViewById(R.id.chatbtn);

        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < ChatEdit.length(); i++) {

            list.add(ChatEdit.toString());

            Chatbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    list.add(ChatEdit.toString());
                }
            });


        }




    }

    class ChatAdapter extends ArrayAdapter<String> {





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
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);


            TextView message = (TextView)result.findViewById(R.id.chatview);
            message.setText(   getItem(position)  ); // get the string at position
            return result;




        }

        @Override
        public long getItemId(int position) {
            return (position);
        }
    }
}