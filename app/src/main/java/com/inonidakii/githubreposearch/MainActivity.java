package com.inonidakii.githubreposearch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        // TODO (2) Call getResponseFromHttpUrl and display the results in mSearchResultsTextView
        // TODO (3) Surround the call to getResponseFromHttpUrl with a try / catch block to catch an IOException
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