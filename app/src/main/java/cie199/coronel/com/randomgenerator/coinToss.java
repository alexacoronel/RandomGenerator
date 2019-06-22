package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class coinToss extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_toss);

        Button toss = (Button)findViewById(R.id.btnToss);

        toss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCoin();
            }
        });
    }

    private void flipCoin(){
        Random rand = new Random();
        ImageView ivCoin = (ImageView) findViewById(R.id.ivCoin);
        int coinSide;

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator.hasVibrator()) {
            vibrator.vibrate(100);
        }

        coinSide = rand.nextInt(2);

        if(coinSide ==0 ){
            ivCoin.setImageResource(R.drawable.heads);
            Toast.makeText(getApplicationContext(), R.string.heads, Toast.LENGTH_SHORT).show();
        }
        else{
            ivCoin.setImageResource(R.drawable.tails);
            Toast.makeText(getApplicationContext(), R.string.tails, Toast.LENGTH_SHORT).show();
        }

        RotateAnimation rotate = new RotateAnimation(0, 360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        ivCoin.startAnimation(rotate);

    }
}
