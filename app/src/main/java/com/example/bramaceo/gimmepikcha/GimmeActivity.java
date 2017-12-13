package com.example.bramaceo.gimmepikcha;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class GimmeActivity extends AppCompatActivity {


    private static final String BASE_URL = "https://picsum.photos/";

    private EditText editText;
    private Button button;
    private ProgressBar progressBar;
    private ImageView loadedImageView;
    private ImageView nextView;

    private String savedTitle;
    private String savedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gimme);

        editText = findViewById(R.id.title);
        button = findViewById(R.id.ok_button);
        progressBar = findViewById(R.id.progress_bar);
        loadedImageView = findViewById(R.id.loaded_image);
        nextView = findViewById(R.id.save_button);

        progressBar.setVisibility(View.GONE);
        loadedImageView.setVisibility(View.GONE);
        nextView.setVisibility(View.GONE);

        button.setOnClickListener(listener -> {
            String title = editText.getText().toString();

            if (!title.isEmpty()) {
                progressBar.setVisibility(View.VISIBLE);
                loadedImageView.setVisibility(View.GONE);
                nextView.setVisibility(View.GONE);

                new LoadImage(title).execute();
            } else {
                Toast.makeText(GimmeActivity.this,
                        getString(R.string.no_title),
                        Toast.LENGTH_SHORT).show();
            }
        });
        nextView.setOnClickListener(listener -> {
            if (savedTitle != null && savedUrl != null) {
                Constants.items.add(new Item(savedUrl, savedTitle));
                Intent intent = new Intent(GimmeActivity.this, ListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(GimmeActivity.this,
                        getString(R.string.no_title),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    class LoadImage extends AsyncTask<Void, Void, Void> {

        String title;
        String imageUrl;


        public LoadImage(String title) {
            this.title = title;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
                int i = 400 + (int) (Math.random() * 100);
                imageUrl = BASE_URL + i + "/" + i;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            loadedImageView.setVisibility(View.VISIBLE);
            nextView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            Picasso.with(loadedImageView.getContext())
                    .load(imageUrl)
                    .into(loadedImageView);

            savedTitle = title;
            savedUrl = imageUrl;
        }
    }
}
