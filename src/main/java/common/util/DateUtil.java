package common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.Date;

/** 
* @date 2019-05-23 16:07:05
* @author LEI
* TODO
*/

public class DateUtil {
	private static final String DEFAULT_DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * date -> string(yyyy-MM-dd)
	 * @param date
	 * @return
	 */
	public static String dateToString(LocalDate date) {
		return date.toString();
	}
	
	/**
	 * date -> string(format)
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(LocalDate date,String format) {
		return date.format(DateTimeFormatter.ofPattern(format));
	}
	/**
	 * dateTime -> string(yyyy-MM-dd HH:mm:ss)
	 * @param date
	 * @return
	 */
	public static String dateToString(LocalDateTime dateTime) {
		return dateToString(dateTime,DEFAULT_DATETIME_FORMAT);
	}
	/**
	 * dateTime -> string(format)
	 * @param date
	 * @param format
	 * @return
	 */
	public static String dateToString(LocalDateTime dateTime,String format) {
		return dateTime.format(DateTimeFormatter.ofPattern(format));
	}
	/**
	 * string(yyyy-MM-dd) -> date
	 * @param date
	 * @return
	 */
	public static LocalDate stringToDate(String date) {
		return LocalDate.parse(date);
	}
	/**
	 * string(format) -> date
	 * @param date
	 * @param format
	 * @return
	 */
	public static LocalDate stringToDate(String date,String format) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
	}
	/**
	 * string(yyyy-MM-dd HH:mm:ss) -> dateTime
	 * @param dateTime
	 * @return
	 */
	public static LocalDateTime stringToDateTime(String dateTime) {
		return stringToDateTime(dateTime,DEFAULT_DATETIME_FORMAT);
	}
	/**
	 * string(format) -> dateTime
	 * @param dateTime
	 * @param format
	 * @return
	 */
	public static LocalDateTime stringToDateTime(String dateTime,String format) {
		return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format));
	}
	/**
	 * based on this one, with the amountin terms of the unit added 
	 * @param date
	 * @param amount
	 * @param type
	 * @return
	 */
	public static LocalDate plus(LocalDate date,Long amount,ChronoUnit type) {
		return date.plus(amount,type);
	}
	/**
	 * based on this one, with the amountin terms of the unit subtracted
	 * @param date
	 * @param amount
	 * @param type
	 * @return
	 */
	public static LocalDate minus(LocalDate date,Long amount,ChronoUnit type) {
		return date.minus(amount, type);
	}
	/**
	 * based on this one, with the amountin terms of the unit added 
	 * @param dateTime
	 * @param amount
	 * @param type
	 * @return
	 */
	public static LocalDateTime plus(LocalDateTime date,Long amount,ChronoUnit type) {
		return date.plus(amount,type);
	}
	/**
	 * based on this one, with the amountin terms of the unit subtracted
	 * @param dateTime
	 * @param amount
	 * @param type
	 * @return
	 */
	public static LocalDateTime minus(LocalDateTime dateTime,Long amount,ChronoUnit type) {
		return dateTime.minus(amount, type);
	}
	/**
	 * this LocalDate with the day-of-month altered. 
	 * @param date
	 * @param day
	 * @return
	 */
	public static LocalDate withDayOfMonth(LocalDate date,int day) {
		return date.withDayOfMonth(day);
	}
	/**
	 * LocalDate -> Date
	 * @param date
	 * @return
	 */
	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay().toInstant(ZoneOffset.of("+8")));
	}
	/**
	 * LocalDateTime ->Date
	 * @param dateTime
	 * @return
	 */
	public static Date toDate(LocalDateTime dateTime) {
		return Date.from(dateTime.toInstant(ZoneOffset.of("+8")));
	}
	
	public static LocalDateTime timestampToDateTime(long timestamp) {
		return LocalDateTime.ofEpochSecond(timestamp/1000, 0, OffsetDateTime.now().getOffset());
	}
	
	public static long DateTimeToTimestamp(LocalDateTime datetime) {
		return datetime.toInstant(OffsetDateTime.now().getOffset()).getEpochSecond();
	}
	
	public static LocalDateTime currentDateTime() {
		return LocalDateTime.now().withNano(0);
	}
}
