package ru.lobko.slava.convertilka;

import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;

public class PreferencesActivity extends PreferenceActivity {

	private static final int IDM_HELP = 202; // ид для меню ПОМОЩЬ
	private static final int IDM_HOME = 204; // ид для меню НАСТРОЙКИ
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.preferences);
	    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
	}//end onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_HOME, Menu.NONE,
				getResources().getString(R.string.home)).setIcon(R.drawable.ic_launcher);
		menu.add(Menu.NONE, IDM_HELP, Menu.NONE, getResources().getString(R.string.help)).setIcon(android.R.drawable.ic_menu_help);
		return super.onCreateOptionsMenu(menu);
	}//end onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {		
		case IDM_HOME:
			showMain();
			break;
		case IDM_HELP:
			showHelp();
			break;		
		default:
			return false;
		}// end switch
		return true;
	}//end onOptionsItemSelected
	
	private void showMain(){
		Intent intent = new Intent(this, MainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
	}//end void showMain
	
	private void showHelp(){
		Intent helpIntent = new Intent(this, HelpAboutTabsActivity.class);
		this.startActivity(helpIntent);
	}//end showHelp

}//end class PreferencesActivity
