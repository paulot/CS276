package com.example.datatest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		final Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Thread thread = new Thread() {
            		@Override
            	      public void run() {
            			Downloader.DownloadFromUrl("http://www.princessgaby.com/data/100b");
            		}
            	};
            	thread.start();
            	Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
            }
        });
		
		final Button button2 = (Button) findViewById(R.id.button2);
		button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Thread thread = new Thread() {
            		@Override
            	      public void run() {
            			Downloader.DownloadFromUrl("http://www.princessgaby.com/data/1kb");
            		}
            	};
            	thread.start();
            	Toast.makeText(getBaseContext(), "Done", Toast.LENGTH_LONG).show();
            }
        });
		
		return true;
	}

}
