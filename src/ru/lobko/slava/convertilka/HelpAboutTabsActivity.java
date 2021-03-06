package ru.lobko.slava.convertilka;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HelpAboutTabsActivity extends TabActivity {
	
	private static final int IDM_PREFS = 201; // ид для меню НАСТРОЙКИ
	private static final int IDM_HOME = 204; // ид для меню НАСТРОЙКИ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_about_tabs_layout);

		TabHost tabHost = getTabHost();

		TabSpec helpTab = tabHost.newTabSpec("Help");
		helpTab.setIndicator(getResources().getString(R.string.help), getResources().getDrawable(android.R.drawable.ic_menu_help));
		Intent helpIntent = new Intent(this, HelpActivity.class);
		helpTab.setContent(helpIntent);

		TabSpec aboutTab = tabHost.newTabSpec("About");
		aboutTab.setIndicator(getResources().getString(R.string.about), getResources().getDrawable(android.R.drawable.ic_menu_info_details));
		Intent aboutIntent = new Intent(this, AboutActivity.class);
		aboutTab.setContent(aboutIntent);

		tabHost.addTab(helpTab);
		tabHost.addTab(aboutTab);
						
	}// end onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_HOME, Menu.NONE,
				getResources().getString(R.string.home)).setIcon(R.drawable.ic_launcher);
		menu.add(Menu.NONE, IDM_PREFS, Menu.NONE,
				getResources().getString(R.string.prefs)).setIcon(android.R.drawable.ic_menu_preferences);
		return super.onCreateOptionsMenu(menu);
	}//end onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_HOME:
			showMain();
			break;
		case IDM_PREFS:
			showPrefs();
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
	
	/**
	 * процедура вызова окна настроек
	 */
	private void showPrefs(){
		Intent intent = new Intent(this, PreferencesActivity.class);
		this.startActivity(intent);
	}//end void showPrefs
	
}// end class HelpAboutTabsActivity
