package com.example.network_connection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.am_tv);

        new NetAsyncTask().execute("https://mail.ru");
    }

    class NetAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(strings[0])
                    .get()
                    .build();
            Response response;

            try {
                response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;


            //это не используется
           /* StringBuilder result = new StringBuilder();
            String urlAsString = strings[0];
            URL url = null;

            try {
                url = new URL(urlAsString);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(stream));
                while ((bufferedReader.read()) != -1){
                    result.append(bufferedReader.readLine());
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result.toString();*/
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }
    }
}
