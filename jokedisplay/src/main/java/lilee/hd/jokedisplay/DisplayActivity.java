package lilee.hd.jokedisplay;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import lilee.hd.jokedisplay.databinding.ActivityDisplayBinding;


public class DisplayActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke";
    ActivityDisplayBinding displayBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayBinding = DataBindingUtil.setContentView(this, R.layout.activity_display);
        String joke = getIntent().getStringExtra(JOKE_EXTRA);

        TextView jokeTextView = displayBinding.jokeView;

        if (joke != null && joke.length() !=0) {
            jokeTextView.setText(joke);
        } else jokeTextView.setText(R.string.no_jokes_found);

    }

}
