package ru.lobko.slava.convertilka;

/**
 * Cyr2Lat - класс для конвертирования кириллических символов в латинские и обратно.
 * Если переключившись на латинскую раскладку набирать символы "Б","Ю","Х","Ъ",
 * "Ё","Ж","Э", то будут набраны ,.[]`;' (или <>{}~:" при нажатом Shift) - эти 
 * символы как правило запрещены для использования в паролях.
 * Поэтому, конвертация возможна в двух вариантах. 
 * В небезопасном режиме (допустимы любые символы и знаки припинания) и 
 * безопасном режиме (удаляются все потенциально опасные символы). 
 * Небезопасный режим - "Б" в ">" или "Ъ" в "}" или "жаба" в ";f,f" 
 * Безопасный режим   - "Б" в ""  или "Ъ" в ""  или "жаба" в "ff" 
 * @author samssrus (Svyatoslav Lobko)
 * @version 2.0
 */

public class Cyr2Lat {

	private static String rus = new String("ЙйЦцУуКкЕеНнГгШшЩщЗзХхЪъФфЫыВвАаПпРрОоЛлДдЖжЭэЯяЧчСсМмИиТтЬьБбЮюЁё");
	private static String eng = new String("QqWwEeRrTtYyUuIiOoPp{[}]AaSsDdFfGgHhJjKkLl:;\"'ZzXxCcVvBbNnMm<,>.~`");
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
			if(rus.indexOf(textChars[i]) != -1 ){
				convertedText.append(eng.charAt(rus.indexOf(textChars[i])));
			}else if(eng.indexOf(textChars[i]) != -1 ){
				convertedText.append(rus.charAt(eng.indexOf(textChars[i])));
			}else{
				convertedText.append(textChars[i]);
			}
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