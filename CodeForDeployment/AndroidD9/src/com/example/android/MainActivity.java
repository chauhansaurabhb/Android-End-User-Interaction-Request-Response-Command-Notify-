package com.example.android;

import deviceImpl.AndroidSmartHomeApp;
import iotsuite.android.localmiddleware.PubSubsSensingFramework;
import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends Activity {
	// public class MainActivity extends Activity implements IDataListener {

	Button buttonOff;
	Button buttonSetTemp;
	Button buttonProfileRequest;

	public static PubSubsSensingFramework pubSubSensingFramework;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new sim.deviceD9.Startup().startDevice(this,
				this.getApplicationContext());

		// pubSubSensingFramework = PubSubsSensingFramework.getInstance();
		// pubSubSensingFramework.registerForSensorData(this,
		// "profileResponse");

		// Button Off
		buttonOff = (Button) findViewById(R.id.button1);
		buttonOff.setOnClickListener(new AndroidSmartHomeApp(this, null));

		// Button SetTemp
		buttonSetTemp = (Button) findViewById(R.id.temp);
		buttonSetTemp.setOnClickListener(new AndroidSmartHomeApp(this, null));

		// Button Profile Request
		buttonProfileRequest = (Button) findViewById(R.id.button);
		buttonProfileRequest.setOnClickListener(new AndroidSmartHomeApp(this,
				null));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}