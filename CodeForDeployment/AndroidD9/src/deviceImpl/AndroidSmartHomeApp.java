package deviceImpl;

import com.example.android.R;

import logic.*;
import framework.*;
import android.app.Activity;
import android.content.Context;
import iotsuite.android.localmiddleware.IDataListener;
import iotsuite.android.localmiddleware.PubSubsSensingFramework;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidSmartHomeApp implements ISmartHomeApp, IDataListener,
		View.OnClickListener {

	public static ListenerSmartHomeApp listenerOffCommand = null;
	public static ListenerSmartHomeApp listenerSetTempCommand = null;
	public static PubSubsSensingFramework pubSubSensingFramework;
	public static ListenerSmartHomeApp listenerProfileRequest = null;

	private Context appContext;
	public static Activity appActivity;

	public AndroidSmartHomeApp(Context context, LogicSmartHomeApp obj) {

		this.appContext = context;
		appActivity = (Activity) appContext;
		pubSubSensingFramework = PubSubsSensingFramework.getInstance();

		pubSubSensingFramework.registerForSensorData(this, "profileResponse");
		pubSubSensingFramework.registerForSensorData(this, "displayTempNotify");

	}

	@Override
	public void OffGUI(ListenerSmartHomeApp handler) {
		listenerOffCommand = handler;
	}

	@Override
	public void SetTempGUI(ListenerSmartHomeApp handler) {
		listenerSetTempCommand = handler;
	}

	@Override
	public void ProfileGUI(ListenerSmartHomeApp handler) {
		listenerProfileRequest = handler;
	}

	@Override
	public void onDataReceived(String eventName, Object data) {
		if (eventName.equals("profileResponse")) {
			TempStruct tempData = (TempStruct) data;
			TextView tv = (TextView) AndroidSmartHomeApp.appActivity
					.findViewById(R.id.textView1);
			tv.setText(Double.toString(tempData.gettempValue()));
		}
		if (eventName.equals("displayTempNotify")) {
			TempStruct tempData = (TempStruct) data;
			TextView tv = (TextView) AndroidSmartHomeApp.appActivity
					.findViewById(R.id.textView1);
			tv.setText(Double.toString(tempData.gettempValue()));
		}

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1: // Off Command
			listenerOffCommand.onNewOffCommand();
			break;

		case R.id.temp: // SetTemp Command
			EditText e1 = (EditText) AndroidSmartHomeApp.appActivity
					.findViewById(R.id.edit_temp);
			String message = e1.getText().toString();
			Double value = Double.parseDouble(message);
			listenerSetTempCommand.onNewSetTempCommand(new TempStruct(value,
					"C"));
			break;
		case R.id.button: // Profile Request
			EditText editText = (EditText) AndroidSmartHomeApp.appActivity
					.findViewById(R.id.edit_message);
			String userProfileValue = editText.getText().toString();
			listenerProfileRequest.onNewProfileRequest(userProfileValue);
			break;
		}

	}

}