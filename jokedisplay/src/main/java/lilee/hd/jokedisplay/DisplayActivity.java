package lilee.hd.jokedisplay;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DisplayActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView jokeTextView = findViewById(R.id.joke_tv);
        String joke = getIntent().getStringExtra(JOKE_EXTRA);

        if (joke != null) {
            jokeTextView.setText(joke);
        } else jokeTextView.setText(R.string.no_jokes_found);

    }

}
