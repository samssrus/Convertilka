package ru.lobko.slava.convertilka;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Cyr2LatTest {

	@Test
	public void testConvert_g() {		
		assertEquals(";", Cyr2Lat.convert("ж", false));
	}
	@Test
	public void testConvert_G() {		
		assertEquals(":", Cyr2Lat.convert("Ж", false));
	}
	
	@Test
	public void testConvert_g2() {		
		assertEquals("", Cyr2Lat.convert("ж", true));
	}
	@Test
	public void testConvert_G2() {		
		assertEquals("", Cyr2Lat.convert("Ж", true));
	}
	
	@Test
	public void testConvert_ee() {		
		assertEquals("\'", Cyr2Lat.convert("э", false));
	}
	@Test
	public void testConvert_EE() {		
		assertEquals("\"", Cyr2Lat.convert("Э", false));
	}
	
	@Test
	public void testConvert_ee2() {		
		assertEquals("", Cyr2Lat.convert("э", true));
	}
	@Test
	public void testConvert_EE2() {		
		assertEquals("", Cyr2Lat.convert("Э", true));
	}
	
	@Test
	public void testConvert_b() {		
		assertEquals(",", Cyr2Lat.convert("б", false));
	}
	@Test
	public void testConvert_B() {		
		assertEquals("<", Cyr2Lat.convert("Б", false));
	}
	
	@Test
	public void testConvert_b2() {		
		assertEquals("", Cyr2Lat.convert("б", true));
	}
	@Test
	public void testConvert_B2() {		
		assertEquals("", Cyr2Lat.convert("Б", true));
	}
	
	@Test
	public void testConvert_yu() {		
		assertEquals(".", Cyr2Lat.convert("ю", false));
	}
	@Test
	public void testConvert_Yu() {		
		assertEquals(">", Cyr2Lat.convert("Ю", false));
	}
	
	@Test
	public void testConvert_yu2() {		
		assertEquals("", Cyr2Lat.convert("ю", true));
	}
	@Test
	public void testConvert_Yu2() {		
		assertEquals("", Cyr2Lat.convert("Ю", true));
	}
	
	@Test
	public void testConvert_x() {		
		assertEquals("[", Cyr2Lat.convert("х", false));
	}
	@Test
	public void testConvert_X() {		
		assertEquals("{", Cyr2Lat.convert("Х", false));
	}
	@Test
	public void testConvert_x2() {		
		assertEquals("", Cyr2Lat.convert("х", true));
	}
	@Test
	public void testConvert_X2() {		
		assertEquals("", Cyr2Lat.convert("Х", true));
	}
	
	@Test
	public void testConvert_at() {		
		assertEquals("]", Cyr2Lat.convert("ъ", false));
	}
	@Test
	public void testConvert_AT() {		
		assertEquals("}", Cyr2Lat.convert("Ъ", false));
	}
	@Test
	public void testConvert_at2() {		
		assertEquals("", Cyr2Lat.convert("ъ", true));
	}
	@Test
	public void testConvert_AT2() {		
		assertEquals("", Cyr2Lat.convert("Ъ", true));
	}
	
	@Test
	public void testConvert_e() {		
		assertEquals("`", Cyr2Lat.convert("ё", false));
	}
	@Test
	public void testConvert_E() {		
		assertEquals("~", Cyr2Lat.convert("Ё", false));
	}
	@Test
	public void testConvert_e2() {		
		assertEquals("", Cyr2Lat.convert("ё", true));
	}
	@Test
	public void testConvert_E2() {		
		assertEquals("", Cyr2Lat.convert("Ё", true));
	}
		
	@Test
	public void testConvert1() {		
		assertEquals("z", Cyr2Lat.convert("я", false));
	}
	
	@Test
	public void testConvert2() {		
		assertEquals("z", Cyr2Lat.convert("я", true));
	}
	
	@Test
	public void testConvert3() {		
		assertEquals("z", Cyr2Lat.convert("z", false));
	}
	
	@Test
	public void testConvert4() {		
		assertEquals("z", Cyr2Lat.convert("z", true));
	}
	
	@Test
	public void testConvert5() {		
		assertEquals("z;", Cyr2Lat.convert("я;", false));
	}
	
	@Test
	public void testConvert6() {		
		assertEquals("z", Cyr2Lat.convert("я;", true));
	}
	
	@Test
	public void testConvert7() {		
		assertEquals("z;", Cyr2Lat.convert("z;", false));
	}
	
	@Test
	public void testConvert8() {		
		assertEquals("z", Cyr2Lat.convert("z;", true));
	}
	
	@Test
	public void testConvert9() {		
		assertEquals(";", Cyr2Lat.convert(";", false));
	}
	
	@Test
	public void testConvert10() {		
		assertEquals("", Cyr2Lat.convert(";", true));
	}
	
	@Test
	public void testConvert11() {		
		assertEquals("\"", Cyr2Lat.convert("\"", false));
	}
	
	@Test
	public void testConvert12() {		
		assertEquals("", Cyr2Lat.convert("\"", true));
	}
	
	@Test
	public void testConvert13() {		
		assertEquals(",.<>[]{}~`;'", Cyr2Lat.convert(",.<>[]{}~`;'", false));
	}
	
	@Test
	public void testConvert14() {		
		assertEquals("", Cyr2Lat.convert(",.<>[]{}~`;'", true));
	}

	@Test
	public void testCyr2lat1() {
		assertEquals("z", Cyr2Lat.cyr2lat("я"));
	}
	
	@Test
	public void testCyr2lat2() {
		assertEquals("z", Cyr2Lat.cyr2lat("z"));
	}
	
	@Test
	public void testCyr2lat3() {
		assertEquals("z{", Cyr2Lat.cyr2lat("я{"));
	}
	
	@Test
	public void testCyr2lat4() {
		assertEquals("z{", Cyr2Lat.cyr2lat("z{"));
	}

	@Test
	public void testSafeCyr2lat1() {
		assertEquals("z", Cyr2Lat.safeCyr2lat("я"));
	}
	
	@Test
	public void testSafeCyr2lat2() {
		assertEquals("z", Cyr2Lat.safeCyr2lat("z"));
	}
	
	@Test
	public void testSafeCyr2lat3() {
		assertEquals("z", Cyr2Lat.safeCyr2lat("я}"));
	}
	
	@Test
	public void testSafeCyr2lat4() {
		assertEquals("z", Cyr2Lat.safeCyr2lat("z}"));
	}

}
