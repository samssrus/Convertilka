package ru.lobko.slava.convertilka;

/**
 * Cyr2Lat - класс для конвертирования кириллических символов в латинские.
 * Если переключившись на латинскую раскладку набирать символы "Б","Ю","Х","Ъ",
 * "Ё","Ж","Э", то будут набраны ,.[]`;' (или <>{}~:" при нажатом Shift) - эти 
 * символы как правило запрещены для использования в паролях.
 * Поэтому, конвертация возможна в двух вариантах. 
 * В небезопасном режиме (допустимы любые символы и знаки припинания) и 
 * безопасном режиме (удаляются все потенциально опасные символы). 
 * Небезопасный режим - "Б" в ">" или "Ъ" в "}" или "жаба" в ";f,f" 
 * Безопасный режим   - "Б" в ""  или "Ъ" в ""  или "жаба" в "ff" 
 * @author samssrus (Svyatoslav Lobko)
 * @version 0.1.1
 */

public class Cyr2Lat {

	private static String rus = new String("ЙйЦцУуКкЕеНнГгШшЩщЗзХхЪъФфЫыВвАаПпРрОоЛлДдЖжЭэЯяЧчСсМмИиТтЬьБбЮюЁё");
	private static char[] eng = {'Q','q','W','w','E','e','R','r','T','t','Y','y','U','u','I','i','O','o','P','p','{','[','}',']',
		'A','a','S','s','D','d','F','f','G','g','H','h','J','j','K','k','L','l',':',';','\"','\'',
		'Z','z','X','x','C','c','V','v','B','b','N','n','M','m','<',',','>','.','~','`'};
	private static String notSafe = "([\"'<>,\\.;:\\{\\}\\[\\]~`'])";
	
	public Cyr2Lat(){}//end constructor
	
	/**
	 * Конвертирует переданные символы в зависимости от заданного режима
	 * @param text Символы которые будут конвертированы
	 * @param mode Режим конвертации (безопасный true и небезопасный false)
	 * @return String Возвращает конвертированную строку
	 */
	public static String convert(String text, boolean mode){
		String out = "";		
		switch(mode ? 1 : 0){
		case 0:
			out = cyr2lat(text);
			break;
		case 1:
			out = safeCyr2lat(text);
			break;
		}
		return out;
	}//end convert
	
	/**
	 * Конвертирует переданные символы в небезопасном режиме
	 * @param text Символы которые будут конвертированы
	 * @return String Возвращает конвертированную строку
	 */
	public static String cyr2lat(String text){
		StringBuilder convertedText = new StringBuilder();
		char[] textChars = text.toCharArray();  //переводим входную строку в массив символов
		int length = text.length();				//подсчитываем количество символов в строке
		for(int i=0; i<length; i++){
			int k = rus.indexOf(textChars[i]);
			if(k != -1){ 	//если символ кирилический то конвертируем его в латинский
				convertedText.append(eng[k]);						
			}else{ 			//если символ латинский или цифра то просто добавляем его
				convertedText.append(textChars[i]);				
			}//end if
		}//end for
		return convertedText.toString();
	}//end cyr2lat
	
	/**
	 * Конвертирует переданные символы в безопасном режиме
	 * @param text Символы которые будут конвертированы 
	 * @return String Возвращает конвертированную строку
	 */
	public static String safeCyr2lat(String text){
		return cyr2lat(text).replaceAll(notSafe, "");
	}//end safeCyr2lat
			
}//end class Cyr2Lat