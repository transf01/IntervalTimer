package com.example.trans.intervaltimer;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private final static String WARMING_UP = "WARMING_UP";
    private final static String INTERVAL = "INTERVAL";
    private final static String TRIAL = "TRIAL";
    private final static String PREF = "PREF";

    private EditText mWarmingUp, mInterval, mTrials;
    private SharedPreferences mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPref = getSharedPreferences(PREF, MODE_PRIVATE);

        mWarmingUp = (EditText) findViewById(R.id.warming_up);
        mInterval = (EditText) findViewById(R.id.interval);
        mTrials = (EditText) findViewById(R.id.trial);

        initTab((TabHost)findViewById(R.id.tab_host));
        initStartButton((Button)findViewById(R.id.cycle_start));
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreIntValue(mWarmingUp, mPref, WARMING_UP);
        restoreIntValue(mInterval, mPref, INTERVAL);
        restoreTrials(mPref, mTrials);
    }

    private void initStartButton(Button startButton) {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIntValue(mWarmingUp, mPref, WARMING_UP);
                saveIntValue(mInterval, mPref, INTERVAL);
                saveTrials(mPref, mTrials);
            }
        });
    }

    private void initTab(TabHost tabHost) {
        tabHost.setup();

        TabHost.TabSpec cycleTab = tabHost.newTabSpec("cycleTab");
        cycleTab.setContent(R.id.tab1);
        cycleTab.setIndicator(getString(R.string.cycle));
        tabHost.addTab(cycleTab);

        TabHost.TabSpec plankTab = tabHost.newTabSpec("plankTab");
        plankTab.setContent(R.id.tab2);
        plankTab.setIndicator(getString(R.string.plank));
        tabHost.addTab(plankTab);
    }

    private void restoreIntValue(EditText et, SharedPreferences pref, String key) {
        int interval = pref.getInt(key, 0);
        if (interval != 0) {
            et.setText(String.valueOf(interval));
        }
    }

    private void saveIntValue(EditText et, SharedPreferences pref, String key) {
        SharedPreferences.Editor edit = pref.edit();
        try {
            Integer value = Integer.valueOf(et.getText().toString());
            edit.putInt(key, value);
            edit.apply();
        }catch (NumberFormatException e) {
            Log.d("trans", e.getStackTrace().toString());
        }
    }

    private void restoreTrials(SharedPreferences pref, EditText et) {
        String trials = pref.getString(TRIAL, null);
        if (trials != null && !trials.isEmpty()) {
            et.setText(trials);
        }
    }

    private void saveTrials(SharedPreferences pref, EditText et) {
        String value = et.getText().toString();
        Log.d("trans", "---------------------" + value);
        if (!value.isEmpty()) {
            Log.d("trans", "+++++++++++++++" + value);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString(TRIAL, value);
            edit.apply();
        }
    }
}
