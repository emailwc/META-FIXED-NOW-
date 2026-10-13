package com.emailwc.ytkeyplayerexact;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Gravity;
import android.graphics.Color;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(80,80,80,80);
        layout.setBackgroundColor(Color.parseColor("#121212"));

        TextView title = new TextView(this);
        title.setText("YT Player Exact\nSONY FIX - ACTIVE");
        title.setTextSize(26);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0,0,0,60);
        layout.addView(title);

        TextView info = new TextView(this);
        info.setText("If you see this, the app WORKS.\nNot YouTube mobile website.");
        info.setTextColor(Color.LTGRAY);
        info.setGravity(Gravity.CENTER);
        info.setPadding(0,0,0,40);
        layout.addView(info);

        Button btn1 = new Button(this);
        btn1.setText("OPEN USB / STORAGE");
        btn1.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("video/*");
            startActivity(i);
        });
        layout.addView(btn1);

        Button btn2 = new Button(this);
        btn2.setText("OPEN YOUTUBE TV");
        btn2.setOnClickListener(v -> {
            try {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/"));
                i.setPackage("com.google.android.youtube.tv");
                startActivity(i);
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/")));
            }
        });
        layout.addView(btn2);

        setContentView(layout);
    }
}
