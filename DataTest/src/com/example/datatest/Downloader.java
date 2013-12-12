package com.example.datatest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ProgressBar;

public class Downloader extends AsyncTask<String, String, String>{

	MainActivity activity;
	ProgressBar bar;
	boolean isBurst;
	int ms;
	int bytes;
	
	public Downloader (MainActivity a, boolean bursty, int ms, int bytes) {
		this.ms = ms;
		this.bytes = bytes;
		isBurst = bursty;
		activity = a;
		bar = (ProgressBar) activity.findViewById(R.id.progressBar1);
	}
	
	public void DownloadFromUrl(String website) {
		try {
			activity.canPush = false;
			Intent stateUpdate = new Intent("com.quicinc.Trepn.UpdateAppState");
			stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value", 100);			
			
	        URL url = new URL(website);

	        //create the new connection
	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	        //set up some things on the connection
	        urlConnection.setRequestMethod("GET");
	        urlConnection.setDoOutput(true);

	        //and connect!
	        activity.sendBroadcast(stateUpdate);
	        urlConnection.connect();

	        //set the path where we want to save the file
	        //in this case, going to save it on the root directory of the
	        //sd card.
	        File SDCardRoot = Environment.getExternalStorageDirectory();
	        //create a new file, specifying the path, and the filename
	        //which we want to save the file as.
	        File file = new File(SDCardRoot,"somefile.ext");

	        //this will be used to write the downloaded data into the file we created
	        FileOutputStream fileOutput = new FileOutputStream(file);

	        
	        //context.sendBroadcast(stateUpdate);
	        //this will be used in reading the data from the internet
	        InputStream inputStream = urlConnection.getInputStream();

	        //this is the total size of the file
	        int totalSize = urlConnection.getContentLength();
	        //variable to store total downloaded bytes
	        int downloadedSize = 0;

	        //create a buffer...
	        byte[] buffer = new byte[1024];
	        int bufferLength = 0; //used to store a temporary size of the buffer

	        //now, read through the input buffer and write the contents to the file
	        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
	                //add the data in the buffer to the file in the file output stream (the file on the sd card
	                fileOutput.write(buffer, 0, bufferLength);
	                //add up the size so we know how much is downloaded
	                downloadedSize += bufferLength;
	                //this is where you would do something to report the prgress, like this maybe
	                updateProgress(downloadedSize, totalSize);

	        }
	        //close the output stream when done
	        stateUpdate = new Intent("com.quicinc.Trepn.UpdateAppState");
			stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value", 0);
	        fileOutput.close();
	        activity.sendBroadcast(stateUpdate);

	//catch some possible errors...
		} catch (MalformedURLException e) {
		        e.printStackTrace();
		} catch (IOException e) {
		        e.printStackTrace();
		}
		activity.canPush = true;
	}

	public void DownloadInBurst(String website, int ms, int nbytes) {
		try {
			activity.canPush = false;
			Intent stateUpdate = new Intent("com.quicinc.Trepn.UpdateAppState");
			stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value", 100);			
			
	        URL url = new URL(website);

	        //create the new connection
	        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

	        //set up some things on the connection
	        urlConnection.setRequestMethod("GET");
	        urlConnection.setDoOutput(true);

	        //and connect!
	        activity.sendBroadcast(stateUpdate);
	        urlConnection.connect();

	        //set the path where we want to save the file
	        //in this case, going to save it on the root directory of the
	        //sd card.
	        File SDCardRoot = Environment.getExternalStorageDirectory();
	        //create a new file, specifying the path, and the filename
	        //which we want to save the file as.
	        File file = new File(SDCardRoot,"somefile.ext");

	        //this will be used to write the downloaded data into the file we created
	        FileOutputStream fileOutput = new FileOutputStream(file);

	        
	        //context.sendBroadcast(stateUpdate);
	        //this will be used in reading the data from the internet
	        InputStream inputStream = urlConnection.getInputStream();

	        //this is the total size of the file
	        int totalSize = urlConnection.getContentLength();
	        //variable to store total downloaded bytes
	        int downloadedSize = 0;

	        //create a buffer...
	        byte[] buffer = new byte[1024];
	        int bufferLength = 0; //used to store a temporary size of the buffer

	        //now, read through the input buffer and write the contents to the file
	        int cBurst = 0;
	        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
	                //add the data in the buffer to the file in the file output stream (the file on the sd card
	                fileOutput.write(buffer, 0, bufferLength);
	                //add up the size so we know how much is downloaded
	                downloadedSize += bufferLength;
	                cBurst += bufferLength;
	                //this is where you would do something to report the prgress, like this maybe
	                updateProgress(downloadedSize, totalSize);
	                if (cBurst >= nbytes) {
	                	try {
							Thread.sleep(ms);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
	                	cBurst = 0;
	                }

	        }
	        //close the output stream when done
	        stateUpdate = new Intent("com.quicinc.Trepn.UpdateAppState");
			stateUpdate.putExtra("com.quicinc.Trepn.UpdateAppState.Value", 0);
	        fileOutput.close();
	        activity.sendBroadcast(stateUpdate);

	//catch some possible errors...
		} catch (MalformedURLException e) {
		        e.printStackTrace();
		} catch (IOException e) {
		        e.printStackTrace();
		}
		activity.canPush = true;
	}
	
	
	
	private void updateProgress(int downloadedSize, int totalSize) {
		bar.setProgress(downloadedSize*100/totalSize);
		//Toast.makeText(activity.getBaseContext(),String.valueOf(((double) downloadedSize)/totalSize * 100), Toast.LENGTH_LONG).show();
	}

	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		if (isBurst)
			DownloadInBurst(arg0[0], ms, bytes);
		else
			DownloadFromUrl(arg0[0]);
		return null;
	}
}
