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

    // TODO (2) Create a method called makeGithubSearchQuery
    // TODO (3) Within this method, build the URL with the text from the EditText and set the built URL to the TextView

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_search) {

            // TODO (4) Remove the Toast message when the search menu item is clicked
            Toast.makeText(this, getString(R.string.search), Toast.LENGTH_SHORT).show();
            // TODO (5) Call makeGithubSearchQuery when the search menu item is clicked
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}