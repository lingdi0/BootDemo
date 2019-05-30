package common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

/** 
* @date 2019-05-29 10:10:51
* @author LEI
* TODO
*/

public class IOUtil {
	private static final String DEFAULT_ENCODING = "UTF-8";
	/**
	 * 获取文件
	 * @param names 文件全名
	 * @return
	 */
	public static File getFile(String ...names ) {
		return FileUtils.getFile(names);
	}
	/**
	 * 保存文件
	 * @param data 数据
	 * @param encoding 编码
	 * @param append 是否追加
	 * @param names 文件全名
	 */
	public static void saveFile(CharSequence data, Charset encoding, boolean append,String ... names) {
		try {
			FileUtils.write(getFile(names), data, encoding, append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存文件不追加
	 * @param data
	 * @param names
	 */
	public static void saveFile(byte[] data,String ... names) {
		saveFile(data, false, names);
	}
	/**
	 * 保存文件
	 * @param data
	 * @param append
	 * @param names
	 */
	public static void saveFile(byte[] data,boolean append,String ... names) {
		try {
			FileUtils.writeByteArrayToFile(getFile(names), data,append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 保存文件不追加 utf-8
	 * @param lines
	 * @param names
	 */
	public static void saveFile(Collection<?> lines,String ... names) {
		saveFile(lines, false, names);
	}
	/**
	 * 保存文件 utf-8
	 * @param lines
	 * @param append
	 * @param names
	 */
	public static void saveFile(Collection<?> lines,boolean append,String ... names) {
		saveFile(lines, DEFAULT_ENCODING, append, names);
	}
	/**
	 * 保存文件
	 * @param lines
	 * @param encoding
	 * @param append
	 * @param names
	 */
	public static void saveFile(Collection<?> lines,String encoding,boolean append,String ... names) {
		try {
			FileUtils.writeLines(getFile(names), lines, append);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除文件/文件目录
	 * @param names
	 */
	public static void deleteFile(String ... names) {
		File file = getFile(names);
		if(!FileUtils.deleteQuietly(file)) {
			try {
				FileUtils.deleteDirectory(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 通过url下载文件
	 * @param url
	 * @param names
	 */
	public static void downFile(URL url,String... names) {
		try {
			FileUtils.copyURLToFile(url, getFile(names));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void downFile(InputStream source,String ... names) {
		try {
			FileUtils.copyInputStreamToFile(source, getFile(names));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取文件为string
	 * @param encoding
	 * @param names
	 * @return
	 */
	public static String readFileToString(Charset encoding,String... names) {
		try {
			String content = FileUtils.readFileToString(getFile(names), encoding);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 读取文件为List<String>
	 * @param encoding
	 * @param names
	 * @return
	 */
	public static List<String> readFileToLines(Charset encoding,String... names) {
		try {
			List<String> list = FileUtils.readLines(getFile(names), encoding);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
