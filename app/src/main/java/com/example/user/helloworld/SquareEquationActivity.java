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

public class SquareEquationActivity extends AppCompatActivity {
    private AlertDialog.Builder ad;
    private EditText edit_a_square, edit_b_square, edit_c_square;
    private String message;
    private double x1,x2,a,b,c;
    private Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_equation);
        go = findViewById(R.id.btn_go_square);
        edit_a_square = findViewById(R.id.edita_square);
        edit_b_square = findViewById(R.id.editb_square);
        edit_c_square = findViewById(R.id.editc_square);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //тут была еще проверка на сообразительность пользователя, но она с отрицательными не работает :с
                //ну чтоб строку не ввел вместо чисел

                try {
                    if (edit_c_square.getText().toString().equals("")) {
                        c = 0.00;
                    } else
                        c = Double.parseDouble(edit_c_square.getText().toString());
                    if (edit_a_square.getText().toString().equals("")) {
                        a = 1.00;
                    } else
                        a = Double.parseDouble(edit_a_square.getText().toString());
                    if (edit_b_square.getText().toString().equals("")) {
                        b = 1.00;
                    } else
                        b = Double.parseDouble(edit_b_square.getText().toString());

                    if (a == 0) {
                        Toast.makeText(getApplicationContext(), "Неверно введен коэффициент", Toast.LENGTH_LONG).show();
                    } else {
                        message = a + "x² ";
                        if (b > 0)
                            message += "+ " + b + "x ";
                        if (b < 0)
                            message += b + "x ";
                        if (c > 0)
                            message += "+ " + c;
                        if (c < 0)
                            message += c;
                        message += " = 0";


                        x1 = ((-1 * b) + Math.sqrt(b * b - 4 * a * c)) / 2 * a;
                        x2 = ((-1 * b) - Math.sqrt(b * b - 4 * a * c)) / 2 * a;
                        ad = new AlertDialog.Builder(SquareEquationActivity.this);
                        ad.setTitle("Мы решили");
                        if (b * b - 4 * a * c < 0) {
                            ad.setMessage("У уравнения " + message + "\n" + "решений нет");
                        } else if (x1 == x2) {
                            ad.setMessage("Решением уравнения " + message + " будет корень: " + x1);
                        } else
                            ad.setMessage("Решением уравнения " + message + " будут корни: " + x1 + " " + x2);
                        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {

                            }
                        });
                        ad.setNegativeButton("Поделиться", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int arg1) {
                                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                                sharingIntent.setType("text/plain");
                                String shareBody;
                                if (b * b - 4 * a * c < 0) {
                                    shareBody = "У уравнения " + message + "\n" + "решений нет";
                                } else if (x1 == x2) {
                                    shareBody = "Решением уравнения " + message + " будет корень: " + x1;
                                } else
                                    shareBody = "Решением уравнения " + message + " будут корни: " + x1 + " " + x2;
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