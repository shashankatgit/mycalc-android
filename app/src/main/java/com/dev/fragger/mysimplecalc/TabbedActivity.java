package com.dev.fragger.mysimplecalc;

import android.app.ActivityGroup;
import android.app.ActivityManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabbedActivity extends ActivityGroup {
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        tabHost = (TabHost) findViewById(R.id.tabHost);

        tabHost.setup(getLocalActivityManager());

        TabHost.TabSpec ts = tabHost.newTabSpec("Simple Calc");
        ts.setIndicator("SimpleCalculator");
        ts.setContent(new Intent(this,Calculator.class));
        tabHost.addTab(ts);

        ts = tabHost.newTabSpec("Calculator");
        ts.setContent(new Intent(this, Calculator2.class));
        ts.setIndicator("Calculator");
        tabHost.addTab(ts);

    }
}
