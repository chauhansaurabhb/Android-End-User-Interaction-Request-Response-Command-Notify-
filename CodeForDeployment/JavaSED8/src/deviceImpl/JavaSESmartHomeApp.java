package deviceImpl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.*;
import framework.*;
import android.content.Context;
import iotsuite.localmiddleware.IDataListener;
import com.google.gson.JsonObject;
import iotsuite.localmiddleware.PubSubsSensingFramework;

public class JavaSESmartHomeApp implements ISmartHomeApp, IDataListener {

	public static PubSubsSensingFramework pubSubSensingFramework;
	String request;

	double tempValue;

	String unitOfMeasurement;

	private ListenerSmartHomeApp listenerOffCommand = null;

	private ListenerSmartHomeApp listenerSetTempCommand = null;

	private ListenerSmartHomeApp listenerProfileRequest = null;

	public JavaSESmartHomeApp(Context context, LogicSmartHomeApp obj) {

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
	public void onDataReceived(String eventName, JsonObject data) {
		// TODO Auto-generated method stub
		// Unwrape the data from Json Object

		if (eventName.equals("profileResponse")) {

			double tempValue = data.get("tempValue").getAsDouble();

			String unitOfMeasurement = data.get("unitOfMeasurement")
					.getAsString();

		}

		if (eventName.equals("displayTempNotify")) {

			double tempValue = data.get("tempValue").getAsDouble();

			String unitOfMeasurement = data.get("unitOfMeasurement")
					.getAsString();

		}

	}

	public void OffCommandInterface() {
		listenerOffCommand.onNewOffCommand();
	}

	public void SetTempCommandInterface() {
		listenerSetTempCommand.onNewSetTempCommand(new TempStruct(tempValue,
				unitOfMeasurement));
	}

	public void ProfileRequestInterface() {
		listenerProfileRequest.onNewProfileRequest(request);
	}

	/*
	 * public void ProfileResponseReceived(TempStruct response){ }
	 */

	private class offButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			OffCommandInterface();
		}
	}

	private class setTempButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {

			SetTempCommandInterface();
		}
	}

	private class requestProfileButtonClickListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ProfileRequestInterface();
		}
	}

}