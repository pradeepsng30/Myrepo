package com.example.shakeit;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;



public class MainActivity extends ActionBarActivity implements  SensorEventListener{

	SensorManager sm;
	Sensor proxSensor,acc;
	TextView Text1,Text2;
	View view2=null;
	
	
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		sm=(SensorManager)getSystemService(SENSOR_SERVICE);
		proxSensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
		sm.registerListener(this, proxSensor, SensorManager.SENSOR_DELAY_NORMAL);
		acc=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		if (id == R.id.action_end) {
			System.runFinalizersOnExit(true);
			System.exit(0);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	
	
	long eventtime = SystemClock.uptimeMillis();
	public void play(View view){
		View view1=null;
		pause(view1);
	Intent downIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
	KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
	downIntent.putExtra(Intent.EXTRA_KEY_EVENT, downEvent);
	sendOrderedBroadcast(downIntent, null);
	}
	
	public void pause(View view){
	Intent upIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
	KeyEvent upEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
	upIntent.putExtra(Intent.EXTRA_KEY_EVENT, upEvent);
	sendOrderedBroadcast(upIntent, null);
	}
	/*NEXT*/
	public void next(View view){
		View view1=null;
		pause(view1);
		Intent downIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
	KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN,   KeyEvent.KEYCODE_MEDIA_NEXT, 0);
	downIntent.putExtra(Intent.EXTRA_KEY_EVENT, downEvent);
	sendOrderedBroadcast(downIntent, null);
	}
	
	/*PREVIOUS*/
	public void prev(View view){
		View view1=null;
		pause(view1);
		Intent downIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
	KeyEvent downEvent = new KeyEvent(eventtime, eventtime, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
	downIntent.putExtra(Intent.EXTRA_KEY_EVENT, downEvent);
	sendOrderedBroadcast(downIntent, null);
	}
/*
	
	public  void play(View view)
	{	AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		if(mAudioManager.isMusicActive()) {
		    Intent i = new Intent("com.android.music.musicservicecommand");
		    i.putExtra("togglepause" , "command" );
		    MainActivity.this.sendBroadcast(i);
		}
	}

	
	
	
	public  void pause(View view)
	{	AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		if(mAudioManager.isMusicActive()) {
		    Intent i = new Intent("com.android.music.musicservicecommand");
		    i.putExtra("pause" , "command" );
		    MainActivity.this.sendBroadcast(i);
		}
	}
	
	public  void prev(View view)
	{	AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		if(mAudioManager.isMusicActive()) {
		    Intent i = new Intent("com.android.music.musicservicecommand");
		    i.putExtra("previous" , "command" );
		    MainActivity.this.sendBroadcast(i);
		}
	}
	
	public  void next(View view)
	{	AudioManager mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		if(mAudioManager.isMusicActive()) {
		    Intent i = new Intent("com.android.music.musicservicecommand");
		    i.putExtra("next" , "command" );
		    MainActivity.this.sendBroadcast(i);
		}
	}*/

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getName().toString().contains("Acc"))
		{if(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]*event.values[2] >125)
		{if(event.values[0]<-3){next(view2);SystemClock.sleep(40);}
		else if(event.values[0]>3){prev(view2);SystemClock.sleep(40);}
			
		}
		}
		
		
		else
		{if(event.values[0]==0)
			play(view2);
			SystemClock.sleep(25);
		}
		try{Text1.setText(event.sensor.getName().toString());
			Text2.setText("X: "+String.valueOf(event.values[0])+
							"\nY: "+String.valueOf(event.values[1])
							+"\nZ: "+String.valueOf(event.values[2]));
		}catch(Exception e)
		{
		// TODO Auto-generated method stub
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	public void Start(View view)
	{Text1=(TextView)findViewById(R.id.textView1);
	Text2=(TextView)findViewById(R.id.textView2);
	
		
	}
}
