package common.util;

import org.apache.commons.lang3.StringUtils;

/** 
* @date 2019-05-28 16:06:59
* @author LEI
* TODO
*/

public class StringUtil {
	/**
     * <p>Checks if a CharSequence is empty (""), null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
	 * @param cs
	 * @return
     */
	public static boolean isBlank(CharSequence cs) {
		return StringUtils.isBlank(cs);
	}
	/**
	 * <p>Checks if a CharSequence is empty ("") or null.</p>
	 * 
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = false
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 * @param cs
	 * @return
	 */
	public static boolean isEmply(CharSequence cs) {
		return StringUtils.isEmpty(cs);
	}
}
