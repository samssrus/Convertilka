package ru.lobko.slava.convertilka;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class HelpAboutTabsActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_about_tabs_layout);

		TabHost tabHost = getTabHost();

		TabSpec helpTab = tabHost.newTabSpec("Help");
		helpTab.setIndicator("Help", getResources().getDrawable(android.R.drawable.ic_menu_help));
		Intent helpIntent = new Intent(this, HelpActivity.class);
		helpTab.setContent(helpIntent);

		TabSpec aboutTab = tabHost.newTabSpec("About");
		aboutTab.setIndicator("About", getResources().getDrawable(android.R.drawable.ic_menu_info_details));
		Intent aboutIntent = new Intent(this, AboutActivity.class);
		aboutTab.setContent(aboutIntent);

		tabHost.addTab(helpTab);
		tabHost.addTab(aboutTab);
						
	}// end onCreate

}// end class HelpAboutTabsActivity
