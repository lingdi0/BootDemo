package common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/** 
* @date 2019-05-28 15:12:41
* @author LEI
* TODO
*/

public class JsonUtil {
	private static final ObjectMapper OM = new ObjectMapper();
	/**
	 * string -> pojo
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> T parsePojo(String jsonString,Class<T> clazz) {
		try {
			return (T) OM.readValue(jsonString, clazz);
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * string -> List<pojo>
	 * @param <T>
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseListPojo(String jsonString,Class<T> clazz){
		JavaType javaType = OM.getTypeFactory().constructParametricType(ArrayList.class, clazz);
		try {
			return OM.readValue(jsonString, javaType);
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * pojo -> string
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		try {
			return OM.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
	}
	
}
