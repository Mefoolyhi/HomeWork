package com.example.user.helloworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LineEquationActivity extends AppCompatActivity {
    private EditText edit_a_line, edit_b_line;
    private Button btn_go;
    private AlertDialog.Builder ad;
    private String message;
    private double x,a ,b;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_equation);
        edit_a_line = findViewById(R.id.edita_line);
        edit_b_line = findViewById(R.id.editb_line);
        btn_go = findViewById(R.id.btn_go_line);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //тут была еще проверка на сообразительность пользователя, но она с отрицательными не работает :с
                //ну чтоб строку не ввел вместо чисел

                try {
                    if (edit_b_line.getText().toString().equals("")) {
                        b = 0.00;
                    } else
                        b = Double.parseDouble(edit_b_line.getText().toString());


                    if (edit_a_line.getText().toString().equals("")) {
                        a = 1.00;
                    } else
                        a = Double.parseDouble(edit_a_line.getText().toString());

                if (a == 0 && b != 0){
                    Toast.makeText(getApplicationContext(),"Неверно введен коэффициент",Toast.LENGTH_LONG).show();
                }
                else {
                    if (b == 0 && a == 0){
                        message = "0 = 0";
                    }
                    if (b > 0) {
                        message = a + "x + " + b + " = 0";
                    } else {
                        if (b == 0){
                            message = a +  "x = 0";
                        }
                        else
                            message = a + "x " + b + " = 0";
                    }


                    if (a != 0)
                        x = (-1 * b) / a;
                    if (x == -0){
                        x = 0;
                    }
                    ad = new AlertDialog.Builder(LineEquationActivity.this);
                    ad.setTitle("Мы решили");
                    if (a != 0)
                        ad.setMessage("Решением уравнения " + message + " будет корень: " + x);
                    else
                        ad.setMessage("Решением уравнения " + message + "\n" + "x - любое");
                    ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {

                        }
                    });
                    ad.setNegativeButton("Поделиться", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int arg1) {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            String shareBody;
                            if (a != 0)
                                shareBody = "Решением уравнения " + message + " будет корень: " + x;
                            else
                                shareBody = "Решением уравнения " + message + "\n" + "x - любое";
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, "Share"));

                        }
                    });
                    ad.setCancelable(false);
                    ad.show();


                }
                }
                catch (NumberFormatException nfe){
                    Toast.makeText(getApplicationContext(),"Неверно введен коэффициент",Toast.LENGTH_LONG).show();
                }

            }
        });

    }


}
