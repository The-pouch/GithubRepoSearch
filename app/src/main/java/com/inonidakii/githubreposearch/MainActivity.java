package com.inonidakii.githubreposearch;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.inonidakii.githubreposearch.utilities.NetworkUtils;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText eSearchBoxEditText;
    TextView eUrlDisplayTextView;
    TextView eSearchResultsTextView;

    TextView eErrorDisplayTextView;
    ProgressBar eResultsProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eSearchBoxEditText = findViewById(R.id.et_search_box);
        eUrlDisplayTextView = findViewById(R.id.tv_url_display);
        eSearchResultsTextView = findViewById(R.id.tv_github_search_results_json);
        eErrorDisplayTextView = findViewById(R.id.tv_error_message_display);

        eResultsProgressBar = findViewById(R.id.pb_loading_indicator);
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

    private void showJsonDataView() {
        eSearchResultsTextView.setVisibility(View.VISIBLE);
        eErrorDisplayTextView.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        eSearchResultsTextView.setVisibility(View.INVISIBLE);
        eErrorDisplayTextView.setVisibility(View.VISIBLE);
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

    class GithubQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            eResultsProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (Exception exception) {
                Log.e(this.toString(), exception.toString());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String githubSearchResults) {
            eResultsProgressBar.setVisibility(View.INVISIBLE);
            super.onPostExecute(githubSearchResults);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                showJsonDataView();
                eSearchResultsTextView.setText(githubSearchResults);
            }
            else
                showErrorMessage();
        }
    }

}