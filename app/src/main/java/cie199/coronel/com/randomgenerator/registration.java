package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class registration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        final EditText userName = (EditText) findViewById(R.id.regUserName);
        final EditText password = (EditText) findViewById(R.id.regPassword);
        final EditText passwordVerify = (EditText) findViewById(R.id.regVerifyPassword);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String newUser = userName.getText().toString();
                String newPassword = password.getText().toString();
                String verifyPassword = passwordVerify.getText().toString();

                if (!verifyPassword.equals(newPassword)){
                    Toast.makeText(getApplicationContext(), R.string.not_match, Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(newUser + newPassword + "data", newUser);
                    editor.apply();

                    Intent loginScreen = new Intent(getApplicationContext(), login.class);
                    startActivity(loginScreen);
                }
            }
        });

        new DownloadImageTask((ImageView)findViewById(R.id.imgViewLogo)).execute("http://m.ateneo.edu/sites/all/themes/ateneo_m/images/mobile-logo.png");
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
