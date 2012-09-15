package ru.lobko.slava.convertilka;

/**
 * Класс MainActivity. Отвечает за основное окно программы.
 * @author samssrus (Svyatoslav Lobko)
 * @version 0.1.2
 */

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.ClipboardManager;
import android.text.InputType;
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
	private static final int IDM_ABOUT = 202; // ид для меню О ПРОГРАММЕ
	private static final int IDM_EXIT  = 203; // ид для меню ВЫХОД

	private static final int DLG_ABOUT = 301; // ид для окна О ПРОГРАММЕ

	private NotificationManager mNotifyMgr;   // менеджер всплывающих подсказок
	private ClipboardManager clipboard; 	  // менеджер буфера обмена

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);		
		createUI(); 	//инициализировать основные компоненты программы
		clearFields();  //очистить поле ввода
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
		mNotifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
	}// end create UI
	
	/**
	 * процедура для очистки поля ввода
	 */
	protected void clearFields(){
		txtConvert.setText("");
	}//end clearFields

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, IDM_PREFS, Menu.NONE,
				getResources().getString(R.string.prefs));
		menu.add(Menu.NONE, IDM_ABOUT, Menu.NONE,
				getResources().getString(R.string.about));
		menu.add(Menu.NONE, IDM_EXIT, Menu.NONE,
				getResources().getString(R.string.exit));
		return super.onCreateOptionsMenu(menu);
	}// end onCreateOptionsMenu

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case IDM_PREFS:
			showPrefs();
			break;
		case IDM_ABOUT:
			showDialog(DLG_ABOUT);
			break;
		case IDM_EXIT:
			onExit();
			break;
		default:
			return false;
		}// end switch
		return true;
	}// end onOptionsItemSelected
	
	/**
	 * процедура вызова окна настроек
	 */
	private void showPrefs(){
		Intent intent = new Intent();
		intent.setClass(this, PreferencesActivity.class);
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
	
	@Override
	protected Dialog onCreateDialog(int id) {
    	Dialog dialog;
        switch(id) {
        case DLG_ABOUT:
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setMessage(getResources().getString(R.string.about_text));
        	dialog = builder.create();
            break;
        default:
            dialog = null;
        }
        return dialog;
	}//end function onCreateDialog
	
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
    	super.onDestroy();
        this.finish();		
	}//end onExit

}// end class MainActivity
