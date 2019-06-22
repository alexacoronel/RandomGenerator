package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class spinWheel extends Activity {
    private String userName = login.user;
    private String password = login.password;
    private ArrayList<String> optionsListString = new ArrayList<String>();
    public static Boolean fromSaved = false;
    public static Boolean isThereSave = false;
    private PowerManager.WakeLock mWakeLock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_wheel);
        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        ArrayList<EditText> optionsList;
        Integer optionsCountTemp;
        if (!fromSaved) {
            optionsList = spinWheelFields.optionsList;
            for(EditText text:optionsList) {
                optionsListString.add(text.getText().toString());
            }
            optionsCountTemp = optionsListString.size();
            // Logs for debugging
            Log.d("TESTFromNotSaved", "New state:" + optionsListString.get(0));
            Log.d("TESTFromNotSaved", "New state:" + optionsListString.get(1));

        }
        else{
            Set<String> userSavedSet = new HashSet<String>();
            userSavedSet = preferences.getStringSet(userName + password + "save", null);
            optionsListString.addAll(userSavedSet);
            optionsCountTemp = optionsListString.size();
            // Logs for debugging
            Log.d("TESTLOADSharedPrefSAVEDSTATE", "Loaded state:" + optionsListString.get(0));
            Log.d("TESTLOADSharedPrefSAVEDSTATE", "Loaded state:" + optionsListString.get(1));
//            //Load saved options from shared pref

        }


        final Integer optionsCount = optionsCountTemp;

        PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
        mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "myapp:wakeLockTag");
        mWakeLock.acquire();

        Button btnSpin =(Button)findViewById(R.id.btnSpin);
        btnSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView wheel = (ImageView) findViewById(R.id.imgViewWheel);

                if (optionsCount == 2)
                    wheel.setImageResource(R.drawable.options_2);
                if (optionsCount == 3)
                    wheel.setImageResource(R.drawable.options_3);
                if (optionsCount == 4)
                    wheel.setImageResource(R.drawable.options_4);
                if(optionsCount==5)
                    wheel.setImageResource(R.drawable.options_5);
                if(optionsCount==6)
                    wheel.setImageResource(R.drawable.options_6);
                if(optionsCount==7)
                    wheel.setImageResource(R.drawable.options_7);
                if(optionsCount==8)
                    wheel.setImageResource(R.drawable.options_8);
                if(optionsCount==9)
                    wheel.setImageResource(R.drawable.options_9);
                if(optionsCount==10)
                    wheel.setImageResource(R.drawable.options_10);
                if(optionsCount==11)
                    wheel.setImageResource(R.drawable.options_11);
                if(optionsCount==12)
                    wheel.setImageResource(R.drawable.options_12);

                final ImageView Wheel = (ImageView)wheel;

                Random rand1 = new Random();
                Random rand2 = new Random();
                Integer maxDegrees = rand1.nextInt(720);
                Integer minDegrees = rand2.nextInt(360);

                RotateAnimation rotate = new RotateAnimation(minDegrees, maxDegrees + 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                rotate.setDuration(4000);
                rotate.setFillAfter(true);
                Wheel.startAnimation(rotate);

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(100);
                }

                new CountDownTimer(60000, 10000){
                    public void onFinish(){
                        mWakeLock.release();
                    }
                    public void onTick(long millisUntilFinished){
                    }
                }.start();

            }
        });

        btnSpin.performClick();

        Button btnLegend= (Button)findViewById(R.id.btnLegend);
        btnLegend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(spinWheel.this, v);
                popup.inflate(R.menu.legend_menu);

                for(int i = 0; i < optionsCount; i++){
                    popup.getMenu().add(i+1 + " - " + optionsListString.get(i));
                }

                popup.show();
            }
        });

        Button btnSave= (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save current optionsList into sharedPref with ID named after the username + password combination
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                isThereSave = true;

                Set<String> savedSet = new HashSet<String>();
                savedSet.addAll(optionsListString);
                editor.putStringSet(userName + password + "save", savedSet);
                editor.apply();

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(100);
                }
                Toast.makeText(getApplicationContext(), R.string.saved, Toast.LENGTH_SHORT).show();
//                                        Code For Debugging
//                Set<String> userDetails = new HashSet<String>();
//                userDetails = preferences.getStringSet(userName + password + "save", null);
//
//                optionsListString.addAll(userDetails);
//                Log.d("TESTSharedPrefSAVEDSTATE", "Saved state:" + optionsListString.get(0));
//                Log.d("TESTSharedPrefSAVEDSTATE", "Saved state:" + optionsListString.get(1));
            }
        });

        Button btnExit = (Button) findViewById(R.id.btnMainMenu);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainMenu = new Intent(getApplicationContext(), menu.class);
                startActivity(backToMainMenu);
            }
        });
    }

}