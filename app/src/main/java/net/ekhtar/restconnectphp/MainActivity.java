package net.ekhtar.restconnectphp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);


        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    //URL url = new URL("http://developerhendy.16mb.com/hello.php");
                    URL url = new URL("http://www.ekhtar.net/hello.php");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    final String hello=bufferedReader.readLine();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(hello);
                        }
                    });

                } catch (IOException e1) {
                    e1.printStackTrace();
                    Toast.makeText(getApplicationContext(),e1.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        };

        Thread thread=new Thread(runnable);
        thread.start();
    }
}
