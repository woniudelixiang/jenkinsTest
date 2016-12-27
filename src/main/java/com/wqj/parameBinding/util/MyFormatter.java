package com.wqj.parameBinding.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.number.CurrencyFormatter;

import junit.framework.Assert;

public class MyFormatter {

	@SuppressWarnings("deprecation")
	public static void test1() throws ParseException{
		
		CurrencyFormatter  currencyFormatter = new CurrencyFormatter();
		currencyFormatter.setFractionDigits(2);  //保留小数点后几位
		currencyFormatter.setRoundingMode(RoundingMode.CEILING); //舍入模式   RoundingMode.CEILING 四舍五入
		System.out.println(currencyFormatter.parse("$123.125", Locale.US));
		Assert.assertEquals(new BigDecimal("123.13"), currencyFormatter.parse("$123.125", Locale.US));
	}
	
	@SuppressWarnings("deprecation")
	public static void test2() throws ParseException{
		CurrencyFormatter  currencyFormatter = new CurrencyFormatter();
		currencyFormatter.setFractionDigits(2);  //保留小数点后几位
		currencyFormatter.setRoundingMode(RoundingMode.CEILING); //舍入模式   RoundingMode.CEILING 四舍五入
		System.out.println(currencyFormatter.print(new BigDecimal("123"), Locale.US));
		Assert.assertEquals("$123.00", currencyFormatter.print(new BigDecimal("123"), Locale.US));
	}
	
	public static void main(String[] args) {
		try {
			test2();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
