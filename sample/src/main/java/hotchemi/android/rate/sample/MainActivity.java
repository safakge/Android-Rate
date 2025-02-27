package hotchemi.android.rate.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;
import hotchemi.android.rate.StoreType;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent customIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://zombo.com/"));

        AppRate.with(this)
                .setStoreType(StoreType.GOOGLEPLAY) //default is Google, other option is Amazon
                .setInstallDays(3) // default 10, 0 means install day.
                .setLaunchTimes(10) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowLaterButton(true) // default true.
                .setDebug(true) // default false.
                .setCancelable(false) // default false.
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .setTitle(R.string.new_rate_dialog_title)
                .setTextLater(R.string.new_rate_dialog_later)
                .setTextNever(R.string.new_rate_dialog_never)
                .setTextRateNow(R.string.new_rate_dialog_ok)
                .setCustomRateAction(() -> {
                    Toast.makeText(this, "Custom rate action triggered", Toast.LENGTH_SHORT).show();
                    this.startActivity(customIntent);
                })
                .setCustomRateIntent(customIntent) // [Comment out this line to use the default rate action]
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
    }

}