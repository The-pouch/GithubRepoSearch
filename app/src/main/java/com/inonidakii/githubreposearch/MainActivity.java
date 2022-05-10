package com.inonidakii.githubreposearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inonidakii.githubreposearch.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText eSearchBoxEditText;
    TextView eUrlDisplayTextView;
    TextView eSearchResultsTextView;

    // TODO (12) Create a variable to store a reference to the error message TextView

    // TODO (24) Create a ProgressBar variable to store a reference to the ProgressBar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eSearchBoxEditText = findViewById(R.id.et_search_box);
        eUrlDisplayTextView = findViewById(R.id.tv_url_display);
        eSearchResultsTextView = findViewById(R.id.tv_github_search_results_json);

        // TODO (13) Get a reference to the error TextView using findViewById

        // TODO (25) Get a reference to the ProgressBar using findViewById
    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the github repository you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our {@link GithubQueryTask}
     */
    void makeGithubSearchQuery() {
        String githubSearchQuery = eSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubSearchQuery);
        eUrlDisplayTextView.setText(githubSearchUrl.toString());
        new GithubQueryTask().execute(githubSearchUrl);

    }

    // TODO (14) Create a method called showJsonDataView to show the data and hide the error

    // TODO (15) Create a method called showErrorMessage to show the error and hide the data

    class GithubQueryTask extends AsyncTask<URL, Void, String>{
        // TODO (26) Override onPreExecute to set the loading indicator to visible
        String response = null;
        @Override
        protected String doInBackground(URL... urls) {

            try {
                response = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (Exception exception) {
                Log.e(this.toString(), exception.toString());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            // TODO (27) As soon as the loading is complete, hide the loading indicator
            // TODO (17) Call showJsonDataView if we have valid, non-null results
            super.onPostExecute(s);
            eSearchResultsTextView.setText(s);
            // TODO (16) Call showErrorMessage if the result is null in onPostExecute
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}