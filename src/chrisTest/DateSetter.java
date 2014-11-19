package chrisTest;

import java.sql.Date;



public class DateSetter extends Date {

	long date;
	int day, month, year;
	
	public DateSetter(long date) {
		super(date);
		
		
	}
	
	public DateSetter(int d, int m, int y) {
		super(setDate(d,m,y));
		
		this.day = d;
		this.month = m;
		this.year = y;
		this.date = setDate(d,m,y);
		
	}

public int getDifferenceBetweenTwoDates(DateSetter d2) {
	long dayLength = 86400000;
	long date1 = this.getLong()/dayLength;
	long date2 = d2.getLong()/dayLength;
	
	return (int) (date2 - date1);
	
}


public Date getEndTime(long duration) {
	
	long startTime = this.getLong();
	
	return new DateSetter(startTime + duration);
	
	
	
}

public static boolean isLeapYear(int y) {
		
		if (y%4!=0)
			return false;
		else if (y%100!=0)
			return true;
		else if (y%400!=0) 
			return false;
		else 
			return true;
		
	}
	
	public static long setDate(int d, int m, int y) {
		
		long dayLength = 86400000;
		long yearLength = (dayLength * 365);
		long leapYearLength = (dayLength * 366);
		
		long yearSQL = 0;
		
		
		for (int year = 1970; year < y; year++) {
			
		if (isLeapYear(year))	
			yearSQL+=leapYearLength;
		else
			yearSQL+=yearLength;

		}
		
		long monthSQL;
		long jan = (dayLength * 31);
		long feb;
		if(isLeapYear(y))
			 feb = 	jan + (dayLength * 29);
		else
			 feb = 	jan + (dayLength * 28);	
		long mar = 	feb + (dayLength * 31);		
		long apr = 	mar + (dayLength * 30);
		long may = 	apr + (dayLength * 31);
		long jun = 	may + (dayLength * 30);
		long jul = 	jun + (dayLength * 31);
		long aug = 	jul + (dayLength * 31);
		long sep = 	aug + (dayLength * 30);
		long oct = 	sep + (dayLength * 31);		
		long nov = 	oct + (dayLength * 30);		

		
		if (m == 1)
			monthSQL = 0;
		else if (m == 2)
			monthSQL = jan;
		else if (m == 3)
			monthSQL = feb;
		else if (m == 4)
			monthSQL = mar;
		else if (m == 5)
			monthSQL = apr;
		else if (m == 6)
			monthSQL = may;		
		else if (m == 7)
			monthSQL = jun;	
		else if (m == 8)
			monthSQL = jul;			
		else if (m == 9)
			monthSQL = aug;		
		else if (m == 10)
			monthSQL = sep;				
		else if (m == 11)
			monthSQL = oct;			
		else if (m == 12)
			monthSQL = nov;			
		else
			throw new IllegalArgumentException("Error: month must be between 1 and 12");
		
		long daySQL = (d - 1) * dayLength;
		
		long dateSQL = yearSQL + monthSQL + daySQL;

		return dateSQL;
	}
	
	public long getLong() {
		return this.date;
	}
	
	
}
