package com.example.ape;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {
    List<ChatMessage> messages = new ArrayList<ChatMessage>();
    //LayoutInflater inflater;
    Context context;

    public MessageAdapter(Context context){
        this.context = context;
        //inflater = LayoutInflater.from(context);
    }

    public void add(ChatMessage message){
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return messages.size();
    }

    @Override
    public Object getItem(int i){
        return messages.get(i);
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @SuppressLint({"WrongViewCast", "InflateParams"})
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ChatMessage message = messages.get(i);

        if (message.isBelongsToCurrentUser()) { // this message was sent by us so let's create a basic chat bubble on the right
            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.sent_messages, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.time = (TextView) convertView.findViewById(R.id.text_message_time);
            convertView.setTag(holder);

            holder.avatar.setVisibility(View.VISIBLE);
            holder.name.setVisibility(View.VISIBLE);
            holder.messageBody.setVisibility(View.VISIBLE);
            holder.time.setVisibility(View.VISIBLE);
            holder.avatar.setImageResource(R.drawable.defultavatar);
            holder.name.setText(message.getUsername());
            holder.messageBody.setText(message.getText());
            holder.time.setText(message.getTime());
        } else {
            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.recive_messages, null);
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.messageBody = (TextView) convertView.findViewById(R.id.message_body);
            holder.time = (TextView) convertView.findViewById(R.id.text_message_time);
            convertView.setTag(holder);

            holder.avatar.setVisibility(View.VISIBLE);
            holder.name.setVisibility(View.VISIBLE);
            holder.messageBody.setVisibility(View.VISIBLE);
            holder.time.setVisibility(View.VISIBLE);
            holder.avatar.setImageResource(R.drawable.defultavatar);
            holder.name.setText(message.getUsername());
            holder.messageBody.setText(message.getText());
            holder.time.setText(message.getTime());
            //GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
        }

        return convertView;
    }
}

class MessageViewHolder {
    public ImageView avatar;
    public TextView name;
    public TextView messageBody;
    public TextView time;
}
