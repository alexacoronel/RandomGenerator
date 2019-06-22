package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class spinWheelEntries extends Activity {

    public static Integer entriesCount;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_wheel_entries);

        addItemsOnSpinner();

        spinner = (Spinner) findViewById(R.id.spinnerEntries);

        Button btnEnter= (Button)findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = String.valueOf(spinner.getSelectedItem());
                entriesCount = Integer.parseInt(value);

                Log.d("alexa", value);

                Intent openSpinWheelFields = new Intent(getApplicationContext(), spinWheelFields.class);
                startActivity(openSpinWheelFields);
            }
        });

    }

    public void addItemsOnSpinner(){
        spinner = (Spinner) findViewById(R.id.spinnerEntries);
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 2; i <=12; i++){
            list.add(Integer.toString(i));
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
