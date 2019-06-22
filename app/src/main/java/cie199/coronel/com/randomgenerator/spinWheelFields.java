package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class spinWheelFields extends Activity {

    private LinearLayout parentLinearLayout;
    public static ArrayList<EditText> optionsList = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_wheel_fields);

        optionsList.clear();

        Integer entriesCount = spinWheelEntries.entriesCount;
        EditText editText = new EditText(this);

        parentLinearLayout = (LinearLayout) findViewById(R.id.editTextContainer);

        for(int i=0; i<entriesCount; i++){
            editText = createEditText(i);
            optionsList.add(editText);
            Log.d("alexa", Integer.toString(editText.getId()));
            parentLinearLayout.addView(editText, parentLinearLayout.getChildCount()-1);

        }

        Button btnEnter= (Button)findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSpinWheel = new Intent(getApplicationContext(), spinWheel.class);
                spinWheel.fromSaved = false;
                startActivity(openSpinWheel);

            }
        });
    }

    private EditText createEditText(Integer id){
        final EditText editText = new EditText(this);
        editText.setHintTextColor(Color.argb(255, 182, 182, 182));
        editText.setHint("Add Option");
        editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setId(id);
        return editText;
    }
}
