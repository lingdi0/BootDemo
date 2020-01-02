package common.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* @date 2019-10-23 15:15:46
* @author LEI
* TODO
*/

public class ValidUtil {
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^.+?@[A-z0-9]+\\.[A-z]{2,}$");
	private static final Pattern PHONE_PATTERN = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$");
	private static final Pattern NUM_PATTERN = Pattern.compile("[0-9]+");
	private static final Pattern CHARACTER_PATTERN = Pattern.compile("[A-z]+");
	
	public static final boolean validEmail(String email) {
		if(email==null) {
			return false;
		}
		return EMAIL_PATTERN.matcher(email).matches();
	}
	
	public static final boolean validPhone(String phone) {
		if(phone==null) {
			return false;
		}
		return PHONE_PATTERN.matcher(phone).matches();
	}
	
	//0完全不匹配 1全数  2全英  3英数混合 
	public static final int validEngNum(String str) {
		int result = 0;
		if(str==null) {
			return 0;
		}
		if(NUM_PATTERN.matcher(str).find()) {
			result += 1;
		}
		if(CHARACTER_PATTERN.matcher(str).find()) {
			result += 2;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String s = "2019年12月25日（水）13:00 ～ 14:00 所要時間[ 01:00 ]　";
		Pattern p = Pattern.compile("(.+?)（.+?）(.+?)～(.+?)所要時間\\[(.+?)\\].+");
		Matcher m = p.matcher(s);
		if(m.find()==true) {
			boolean b = m.matches();
			System.out.println(b);
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
			System.out.println(m.group(4));
		}
		LocalTime t = LocalTime.parse("01:00");
		LocalTime zero = LocalTime.parse("00:00");
		long mins = ChronoUnit.MINUTES.between(zero, t);
		System.out.println(mins);
		;
	}
	
}
