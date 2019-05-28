package common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @date 2019-05-28 14:50:49
 * @author LEI TODO
 */

public class NumberUtil {
	/**
	 * 小数保留小数位数 默认四舍五入
	 * @param num
	 * @param newScale
	 * @return
	 */
	public static double doubleRetainAndRounding(double num,int newScale) {
		return new BigDecimal(num).setScale(newScale, RoundingMode.HALF_UP).doubleValue();
	}
	/**
	 * 小数保留小数位数
	 * @param num
	 * @param bigDecimalNum
	 * @param newScale
	 * @return
	 */
	public static double doubleRetainAndRounding(double num,RoundingMode bigDecimalNum,int newScale) {
		return new BigDecimal(num).setScale(newScale, bigDecimalNum).doubleValue();
	}
}
