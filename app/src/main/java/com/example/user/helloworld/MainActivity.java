package com.example.user.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

//чудил Елений
public class MainActivity extends AppCompatActivity {
    private Button square;
    private Button line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        square = findViewById(R.id.button1);
        line = findViewById(R.id.button2);

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SquareEquationActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LineEquationActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}
