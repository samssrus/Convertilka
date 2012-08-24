package ru.lobko.slava.convertilka;

import static org.junit.Assert.*;

import org.junit.Test;

public class Cyr2LatTest {

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
