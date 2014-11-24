package tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

public class DateConvert {
	
	static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static final DateFormat dateFormatAll = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Date StringToDate(String str) throws ParseException {
		Date date = null;
		date = dateFormat.parse(str);
		return date;
	}
	
	public static String DateToString(Date date) {
		String str = dateFormat.format(date);
		return str;
	}
	
	public static Date AllStringToDate(String str) throws ParseException {
		Date date = null;
		date = dateFormatAll.parse(str);
		return date;
	}
	
	public static String AllDateToString(Date date) {
		String str = dateFormatAll.format(date);
		return str;
	}
	
	public static String ConverToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = df.format(date);
		return strDate;
	}
	
	public static Date ConverToDate(String strDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(strDate);
		return date;
	}
	
	public static Date ConverFromCSVToDate(String strDate) throws ParseException {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date date = df.parse(strDate);
		return date;
	}
	
	
	public static Timestamp StringConvertToTimestamp(String str) throws ParseException{
		Date date = null;
		date = dateFormatAll.parse(str);
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
	
	public static Timestamp DateConvertToTimestamp(Date date){
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}
}












