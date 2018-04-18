package com.baiyai.android.fuganghomework;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final String EXTRA_NAME = "NAME";
    public static final String EXTRA_PHOTO = "PHOTO";

    private static final String DATA_REQUEST_URL = "http://baiyai.com/cantacoop/data.json";

    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new UserAdapter(this, new ArrayList<User>());

        GridView gridView = findViewById(R.id.user_grid);
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                User user = mAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_NAME, user.getName());
                intent.putExtra(EXTRA_PHOTO, user.getPhoto());
                startActivity(intent);
            }
        });

        UserAsyncTask task = new UserAsyncTask();
        task.execute();
    }

    private class UserAsyncTask extends AsyncTask<URL, Void, List<User>> {

        @Override
        protected List<User> doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(DATA_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Event} object
            List<User> users = extractFeatureFromJson(jsonResponse);

            // Return the {@link Event} object as the result fo the {@link TsunamiAsyncTask}
            return users;
        }

        @Override
        protected void onPostExecute(List<User> data) {

            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {

            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {

            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                // TODO: Handle the exception
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }

            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {

            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }

            return output.toString();
        }

        private List<User> extractFeatureFromJson(String userJSON) {

            if (TextUtils.isEmpty(userJSON)) {
                return null;
            }

            List<User> users = new ArrayList<>();

            try {
                JSONObject baseJsonResponse = new JSONObject(userJSON);
                JSONArray userArray = baseJsonResponse.getJSONArray("users");

                for (int i = 0; i < userArray.length(); i++) {

                    JSONObject currentUser = userArray.getJSONObject(i);
                    String photoUrl = currentUser.getString("photo");
                    String name = currentUser.getString("name");

                    User user = new User(name, photoUrl);

                    // Add the new {@link Earthquake} to the list of earthquakes.
                    users.add(user);
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the user JSON results", e);
            }

            return users;
        }
    }
}
