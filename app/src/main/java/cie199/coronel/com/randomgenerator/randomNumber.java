package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class randomNumber extends Activity {

    public static Integer minNumber;
    public static Integer maxNumber;
    ArrayList<Integer> numberRange = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

        Button btnEnter= (Button)findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText minEditText = findViewById(R.id.editTextMinimum);
                EditText maxEditText = findViewById(R.id.editTextMaximum);
                String minValue = minEditText.getText().toString();
                String maxValue = maxEditText.getText().toString();
                if (maxValue.equals("")&& minValue.equals("")){
                    Toast.makeText(getApplicationContext(), R.string.noInput, Toast.LENGTH_SHORT).show();
                }
                else {
                    minNumber = Integer.parseInt(minValue);
                    maxNumber = Integer.parseInt(maxValue);

                    if (maxNumber > minNumber) {
                        //enter code for generating random number
                        numberRange.clear();
                        for (int i = minNumber; i < maxNumber + 1; i++) {
                            numberRange.add(i);
                        }

                        Collections.shuffle(numberRange);
                        String randNum1 = numberRange.get(0).toString();
                        TextView randomNumber = (TextView) findViewById(R.id.randNumDisplay);
                        randomNumber.setText(randNum1);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.invalidIntInput, Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}