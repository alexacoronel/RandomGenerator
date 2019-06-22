package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnSpinWheel = (Button)findViewById(R.id.btnSpinWheel);
        btnSpinWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSpinWheelEntries = new Intent(getApplicationContext(), spinWheelEntries.class);
                startActivity(openSpinWheelEntries);
            }
        });

        Button btnRandNum = (Button)findViewById(R.id.btnRandNum);
        btnRandNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openRandNum = new Intent(getApplicationContext(), randomNumber.class);
                startActivity(openRandNum);
            }
        });

        Button btnCoinToss = (Button)findViewById(R.id.btnCoinToss);
        btnCoinToss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openCoinToss = new Intent(getApplicationContext(), coinToss.class);
                startActivity(openCoinToss);
            }
        });

        Button btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLogin = new Intent(getApplicationContext(), login.class);
                startActivity(openLogin);
            }
        });

        Button btnSaved = (Button) findViewById(R.id.btnSaved);
        btnSaved.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(spinWheel.isThereSave) {
                    Intent openSaved = new Intent(getApplicationContext(), spinWheel.class);
                    spinWheel.fromSaved = true;
                    startActivity(openSaved);
                }
                else{
                    Toast.makeText(getApplicationContext(), R.string.noSave, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




}
