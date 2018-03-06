package com.example.patel.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private SQLiteDatabase db;
    private ContentValues data;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

       ChatDatabaseHelper dbChat =  new ChatDatabaseHelper(this);

        db = dbChat.getWritableDatabase();
        data = new ContentValues();

        final ChatAdapter messageAdapter =new ChatAdapter( this );

        final ListView ChatList = (ListView) findViewById(R.id.chatView);

        ChatList.setAdapter(messageAdapter);

        final EditText ChatEdit = (EditText) findViewById(R.id.ChatEdit);

        Button Chatbtn1 = (Button) findViewById(R.id.Chatbtn);

        Chatbtn1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    list.add(ChatEdit.getText().toString());
                    data.put(ChatDatabaseHelper.KEY_MESSAGE,ChatEdit.getText().toString());
                    db.insert(ChatDatabaseHelper.TABLE_NAME,null,data);
                    ChatEdit.setText("");
                    messageAdapter.notifyDataSetChanged();
                }
            });
        }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor cursor = db.query(false, ChatDatabaseHelper.TABLE_NAME,
                new String[] { ChatDatabaseHelper.KEY_MESSAGE},
                null , null,
                null, null, null, null);

        cursor.moveToFirst();

        int countCursor = cursor.getCount();

        for(int i = 0; i < countCursor; i++) {
            String resultMSG= cursor.getString((cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            list.add(resultMSG);
            cursor.moveToNext();
        }
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

