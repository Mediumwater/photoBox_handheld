package buttondevice.photobox.dd.de.pressbutton;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    public EditText ip = null;
    public EditText port = null;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = (Button) findViewById(R.id.button_start);
        ip = (EditText) findViewById(R.id.edittext_ip);
        port = (EditText) findViewById(R.id.edittext_port);


        SharedPreferences prefss = getSharedPreferences("de.dd.photoBox", MODE_PRIVATE);

        ip.setText(prefss.getString("ip", ""));
        port.setText(prefss.getString("port", ""));


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("jojo", "ada");

                String _ip = ip.getText().toString();
                String _port = port.getText().toString();

                if (_ip == "")
                    _ip = "192.168.0.1";
                if (_port == "")
                    _port = "80";

                final SharedPreferences.Editor prefs = getSharedPreferences("de.dd.photoBox", MODE_PRIVATE).edit();
                prefs.putString("ip", _ip);
                prefs.putString("port", _port);
                prefs.commit();

                Intent idlebuttonintent = new Intent(MainActivity.this, IdleButtonActivity.class);
                startActivity(idlebuttonintent);
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }
}

