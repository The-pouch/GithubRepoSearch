package com.inonidakii.githubreposearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eSearchBoxEditText = findViewById(R.id.et_search_box);
        eUrlDisplayTextView = findViewById(R.id.tv_url_display);
        eSearchResultsTextView = findViewById(R.id.tv_github_search_results_json);
    }

    void makeGithubSearchQuery() {
        String githubSearchQuery = eSearchBoxEditText.getText().toString();
        URL githubSearchUrl = NetworkUtils.buildUrl(githubSearchQuery);
        eUrlDisplayTextView.setText(githubSearchUrl.toString());
        String response = null;
        try {
            response = NetworkUtils.getResponseFromHttpUrl(githubSearchUrl);
            eSearchResultsTextView.setText(response);
        } catch (Exception exception) {
            Log.e(this.toString(), exception.toString());
        }
    // TODO (4) Create a new GithubQueryTask and call its execute method, passing in the url to query
    }

    // TODO (1) Create a class called GithubQueryTask that extends AsyncTask<URL, Void, String>
    // TODO (2) Override the doInBackground method to perform the query. Return the results. (Hint: You've already written the code to perform the query)
    // TODO (3) Override onPostExecute to display the results in the TextView

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