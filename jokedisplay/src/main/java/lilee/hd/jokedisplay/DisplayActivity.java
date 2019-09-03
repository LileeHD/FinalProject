package lilee.hd.jokedisplay;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;


public class DisplayActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA = "joke";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String joke = getIntent().getStringExtra(JOKE_EXTRA);
        ((TextView) findViewById(R.id.joke_tv)).setText(joke);
    }

}
