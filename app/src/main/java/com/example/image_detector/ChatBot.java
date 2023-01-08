package com.example.image_detector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.samsao.messageui.views.MessagesWindow;
import com.samsao.messageui.views.WriteMessageBox;

public class ChatBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        // "context" must be an Activity, Service or Application object from your app.
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        MessagesWindow messagesWindow = (MessagesWindow) findViewById(R.id.customized_messages_window);
        Button send = findViewById(com.samsao.messageui.R.id.message_box_button);
        send.setBackgroundColor(Color.BLUE);
        send.setTextSize(14);
        EditText message = messagesWindow.getWritingMessageView().findViewById(com.samsao.messageui.R.id.message_box_text_field);

        message.setHint("Type Here...");
        messagesWindow.setBackgroundResource(R.color.teal_200);

        messagesWindow.getWritingMessageView().findViewById(com.samsao.messageui.R.id.message_box_button).setOnClickListener(v -> {
            messagesWindow.sendMessage(message.getText().toString());

            Python py = Python.getInstance();

            PyObject pyObj = py.getModule("chatbot");

            PyObject obj = pyObj.callAttr("main", message.getText().toString());

            messagesWindow.receiveMessage(obj.toString());

            message.setText("");
        });

    }
}