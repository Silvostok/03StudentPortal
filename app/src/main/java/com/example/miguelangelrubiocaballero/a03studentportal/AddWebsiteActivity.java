package com.example.miguelangelrubiocaballero.a03studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddWebsiteActivity extends AppCompatActivity {

    private EditText wUrl;
    private EditText wTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_website);

        Button wButton = (Button) findViewById(R.id.button);
        wUrl = (EditText)findViewById(R.id.editTextUrl);
        wTitle = (EditText)findViewById(R.id.editTextTitle);

        wButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String wUrlString = wUrl.getText().toString();
                String wTitleString = wTitle.getText().toString();
                if(!TextUtils.isEmpty(wUrlString) && !TextUtils.isEmpty(wTitleString))
                {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.INTENT_ADD_WEBSITE_URL, wUrlString);
                    resultIntent.putExtra(MainActivity.INTENT_ADD_WEBSITE_TITLE, wTitleString);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });


    }

}
