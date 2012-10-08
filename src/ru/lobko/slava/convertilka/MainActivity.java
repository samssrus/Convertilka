package ru.lobko.slava.convertilka;

/**
 * Класс MainActivity. Отвечает за основное окно программы.
 * @author samssrus (Svyatoslav Lobko)
 * @version 0.1.2
 */

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.ClipboardManager;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText txtConvert; 	//поле ввода
	private boolean mode;		 	//режим конвертирования
	private boolean passMode; 		//режим отображения как пароль
	
	private static final int NOTIFY_ID = 101; // ид для всплавающих подсказок

	private static final int IDM_PREFS = 201; // ид для меню НАСТРОЙКИ
	private static final int IDM_HELP = 202; // ид для меню ПОМОЩЬ
	private static final int IDM_EXIT  = 203; // ид для меню ВЫХОД
	
	private static final int IDM_CLEAR  = 401; // ид для меню ОЧИСТИТЬ

	private NotificationManager mNotifyMgr;   // менеджер всплывающих подсказок
	private ClipboardManager clipboard; 	  // менеджер буфера обмена

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);		
		createUI(); 	//инициализировать основные компоненты программы
		
	}// end onCreate
		
	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		mode = prefs.getBoolean("safeMode", true);
		passMode = prefs.getBoolean("passMode", false);
		//меняем тип поля в зависимости от нужного режима
		txtConvert.setInputType(InputType.TYPE_CLASS_TEXT | (passMode ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_TEXT_VARIATION_NORMAL));		
	}//end onResume
	
	/**
	 * процедура инициализации основных компонентов окна программы
	 */
	public void createUI() {
		txtConvert = (EditText) findViewById(R.id.txtConvert);
		registerForContextMenu(txtConvert);
		mNotifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
	}// end create UI
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.add(Menu.NONE, IDM_CLEAR, Menu.NONE,
				getResources().getString(R.string.clear));
	}//end onCreateContextMenu
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_CLEAR:
			clearFields();
			break;		
		default:
			return false;
		}// end switch
		return true;
	}//end onContextItemSelected

	/**
	 * процедура для очистки поля ввода
	 */
	protected void clearFields(){
		txtConvert.setText("");
	}//end clearFields

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_PREFS, Menu.NONE,
				getResources().getString(R.string.prefs)).setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(Menu.NONE, IDM_HELP, Menu.NONE,
				getResources().getString(R.string.help)).setIcon(android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, IDM_EXIT, Menu.NONE,
				getResources().getString(R.string.exit)).setIcon(android.R.drawable.ic_lock_power_off);
		return super.onCreateOptionsMenu(menu);
	}// end onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_PREFS:
			showPrefs();
			break;
		case IDM_HELP:
			showHelp();
			break;
		case IDM_EXIT:
			onExit();
			break;
		default:
			return false;
		}// end switch
		return true;
	}// end onOptionsItemSelected
	
	private void showHelp(){
		Intent helpIntent = new Intent(this, HelpAboutTabsActivity.class);
		this.startActivity(helpIntent);
	}//end showHelp
	
	public void onHelp(View v){
		showHelp();
	}//end onHelp
	
	/**
	 * процедура вызова окна настроек
	 */
	private void showPrefs(){
		Intent intent = new Intent(this, PreferencesActivity.class);
		this.startActivity(intent);
	}//end void showPrefs
	
	/**
	 * процедура обработки нажатия на кнопку КОНВЕРТИРОВАТЬ 
	 */
	public void onConvert(View v){
		String text = txtConvert.getText().toString();
		if(text.length() <= 0) return;
		String result = Cyr2Lat.convert(text, mode);
		txtConvert.setText(result);	
		copy2clipboard(result);
		String noteText = (passMode ? getResources().getString(R.string.resultCopy2Clipboard) : result);
		showNotification(noteText,getResources().getString(R.string.conv_result),noteText);		
    }//end void onConvert
	
	/**
	 * процедура отображения результата в строке состояния
	 */
	private void showNotification(CharSequence tickerText, CharSequence contentTitle, CharSequence contentText) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		
		Context context = getApplicationContext();
		Intent notificationIntent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		
		Notification notification = new Notification(icon, tickerText, when);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		mNotifyMgr.notify(NOTIFY_ID, notification);
	}//end void showNotification
	
		
	/**
	 * процедура копирования результата в буфер обмена
	 */
	private void copy2clipboard(String text) {
		clipboard.setText(text);
	}//end copy2clipboard
	
	/**
	 * процедура выхода из программы
	 */
	private void onExit() {
		clearFields();  //очистить поле ввода
    	super.onDestroy();
        this.finish();		
	}//end onExit

}// end class MainActivity
