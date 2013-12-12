package com.example.datatest;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent.OnFinished;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends Activity {

	public boolean canPush = true;
	View Settings;
	Dialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LayoutInflater inflater = (LayoutInflater)this.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		Settings = inflater.inflate(R.layout.dialog_select, null);
		dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_select);
		dialog.setTitle("Settings.");
	}

	public void onSettingsClick(MenuItem item) {
			dialog.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		
		
		final NumberPicker picker1 = (NumberPicker) Settings.findViewById(R.id.ms);
		final NumberPicker picker2 = (NumberPicker) Settings.findViewById(R.id.bytes);
		
		picker1.setMinValue(0);
		picker1.setMaxValue(1000);
		picker2.setMinValue(100);
		picker2.setMaxValue(1024);
		
		final ToggleButton toggle = (ToggleButton) Settings.findViewById(R.id.toggleBurst);
		//toggle.isChecked();
		
		final ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		
		final Button button1 = (Button) findViewById(R.id.button1);
		final MainActivity t = this;
		button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast.makeText(t, toggle.isChecked() + "", 10000).show();
            	if(canPush) {
            		bar.setProgress(0);
            		new Downloader(t, toggle.isChecked(), picker1.getValue(), picker2.getValue())
            			.execute("http://www.princessgaby.com/data/100b");
            	}
            }
		});
		
		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(canPush) {
            		bar.setProgress(0);
            		new Downloader(t, toggle.isChecked(), picker1.getValue(), picker2.getValue())
        			.execute("http://www.princessgaby.com/data/1kb");
            	}
            }
        });
		
		final Button button3 = (Button) findViewById(R.id.button3);
		button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(canPush) {
            		bar.setProgress(0);
            		new Downloader(t, toggle.isChecked(), picker1.getValue(), picker2.getValue())
        			.execute("http://www.princessgaby.com/data/1mb");
            	}
            }
        });
		
		final Button button4 = (Button) findViewById(R.id.button4);
		button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(canPush) {
            		bar.setProgress(0);
            		new Downloader(t, toggle.isChecked(), picker1.getValue(), picker2.getValue())
        			.execute("http://www.princessgaby.com/data/2mb");
            	}
            }
        });
		
		final Button button5 = (Button) findViewById(R.id.button5);
		button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(canPush) {
            		bar.setProgress(0);
            		new Downloader(t, toggle.isChecked(), picker1.getValue(), picker2.getValue())
        			.execute("http://www.princessgaby.com/data/10mb");
            	}
            }
        });
		
		final Button button6 = (Button) findViewById(R.id.button6);
		button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	if(canPush) {
            		bar.setProgress(0);
            		new Downloader(t, toggle.isChecked(), picker1.getValue(), picker2.getValue())
        			.execute("http://www.princessgaby.com/data/100mb");
            	}
            }
        });
		
		return true;
	}

	public void OnFinishedInflate() {
		
	}
}
