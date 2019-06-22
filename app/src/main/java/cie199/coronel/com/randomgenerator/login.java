package cie199.coronel.com.randomgenerator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class login extends Activity {
    public static String user;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText inputUserName = (EditText) findViewById(R.id.etxtUserName);
        final EditText inputPassword = (EditText) findViewById(R.id.etxtPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnRegister = (Button) findViewById(R.id.btnGoRegister);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                user = inputUserName.getText().toString();
                password = inputPassword.getText().toString();


                String userDetails = preferences.getString(user + password + "data", "wrong");
                if (!userDetails.equals("wrong")){
                    Toast.makeText(getApplicationContext(), "Welcome " + userDetails + "!", Toast.LENGTH_SHORT).show();

                    Intent mainMenu = new Intent(getApplicationContext(), menu.class);
                    startActivity(mainMenu);
                }
                else   {
                    Toast.makeText(getApplicationContext(), R.string.invalidLoginInput, Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrationActivity = new Intent(getApplicationContext(), registration.class);
                startActivity(registrationActivity);
            }
        });

        new DownloadImageTask((ImageView)findViewById(R.id.imgViewLogo)).execute("http://m.ateneo.edu/sites/all/themes/ateneo_m/images/mobile-logo.png");
    }

    @Override
    public void onBackPressed() {
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