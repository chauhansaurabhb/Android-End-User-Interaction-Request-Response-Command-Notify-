package factory;

import framework.*;
import logic.*;
import android.app.Activity;

public class HeaterFactory {

	public static IHeater getHeater(String nameHeater, Activity context,
			LogicHeater obj) {

		// if (nameHeater.equals(GlobalVariable.deviceAndroidType))
		// return new AndroidHeater(context, obj);

		return null;
	}

}