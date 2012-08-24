package ru.lobko.slava.convertilka;

import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.os.Bundle;

public class PreferencesActivity extends PreferenceActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preferences);
	    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
	}//end onCreate

}//end class PreferencesActivity
