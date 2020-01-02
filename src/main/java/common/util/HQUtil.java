package common.util;

import java.net.Proxy;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;

/** 
* @date 2019-12-06 13:14:47
* @author LEI
* TODO
*/

public class HQUtil {
	
	public static final Pattern DATA_PATTERN = Pattern.compile("\"(.*?)\"");
//	public List
	
	private static final String urlPath = "http://hq.sinajs.cn/list={0}";
	
	public static String getHq(String tscode) {
		return HttpUtil.get(MessageFormat.format(urlPath, tscode));
	}
	
	public static String getParamsByMap(Map<String,Object> map) {
		StringBuffer sb = new StringBuffer(50);
		if(map!=null&&map.size()!=0) {
			for(Entry<String, Object> entry : map.entrySet()) {
				sb.append(entry.getKey()+"="+entry.getValue()+"&");
			}
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	public static String sendByProxy(String url,Method method,Proxy proxy) {
		HttpRequest httpRequest = new HttpRequest(url);
		httpRequest.setMethod(method);
		httpRequest.setProxy(proxy);
		httpRequest.setConnectionTimeout(5000);
//		Map<String,String> headerMap = new HashMap<String, String>();
//		String user = "appuser";
//		String pw = "Repeatlink2019";
//		Authenticator.setDefault(new Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(user,pw.toCharArray());
//			}
//		});
		HttpResponse resp = httpRequest.execute();
		return resp.body();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String id = "Y512410210";
//		String id = "Y513451171";
		HttpRequest hr = new HttpRequest("https://salonboard.com/KLP/reserve/ext/extReserveDetail/?reserveId="+id);
		hr.addHeaders(getHeader());
		String html = hr.execute().body();
		Map<String, Object> map = getStaffAndEquipmentInfos(html);
		System.out.println(map);
	}
	
	private static Map<String,String> getHeader(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("Cookie", "JSESSIONID=AEF5F2E6A727A00377C0AA67DBB07AC1.ap264_073; rbkk=58cdaedc9544acb1f2668dc9022c75313673d84756bc4acd5fdb03a9db94600f5724c3ef685ce8ad5f25adf858b6f63c2a52d8a42ae484c1e0391ca39c7e4ddca7891edd24f61ec3; rbkkS=4b96004c9602433231c5594dd26c7695; rps=e8066467c2a95085a1a70ce962570cba80ee0d079a70fee0; rpss=201b25b261649510a1a70ce962570cba80ee0d079a70fee0; GalileoCookie=XfcmYqwaLskAADWBhG8AAAAN; _ga=GA1.2.1732704758.1576478309; __zlcmid=vmilTghCzdGOec; _abck=01E232A97361FD4C96581064D70CA263~0~YAAQtvEPF6W1ucduAQAAFIfrEgN/ufDWxKXvkePmr+izfkBscdsXPVX/+BQNY72gQhDaYZv2CNfQgTEkCy5t7ljYu4Yu/dGJwK6o+KvtHkHHZ+6tKjHFgnPB5zTH+YhPkjsXUnYZvBKtVXCeK58BEbTvkZBvWJVFDk/yl5Gu0WjNMKw3MaUDykQ7ZeDzpC1zmHFtMobxwZ5264Awu6Z/LhjVEMHt5lIFNvMQEjqJQ2Wg0KLXvphasVWWsx5dopwAPdX9rjor8MUv9zrLYRVRCr5Gat6dPrfCqWukZHiF3GbsrmfofZ78DpGge9IbazpfQgIfYNyr6QB6mvg=~-1~-1~-1; HPB_ID_POSSESSION=false; _gid=GA1.2.1665582335.1577167558; bm_sz=7879E335D377F9B5EE46D075DC8647C4~YAAQ5nKeQXZluzhvAQAAJYalOwbJXZz4Qe8h7d/WubU4uvWcaZxf3noOKn5tiosfFkZu6UL/jJ9JMwa4S9drJ36bB/iARF6EYleUAkMESsVc7unIsGAo0UO4H2ejxAXwCwmawIw0QVphCmH61QsYjmIuR12zvCk0FyEJWinq5d3N2dbl1qeFPOGRJaEM0aFu3blXKg==; ak_bmsc=6943275103C1766FA8C7B5BDEEF764A2419E72E6DF03000045FB025E38DCEA4A~plKwgpZpe45dncmDZZBIDtyvC+/FIML3I2tkuDQZbEU7O4mRm9n9+4rqEbHu/WI3WX5YWddfqTvGV4gE/3HfM7ozcwObj1kOxZvwJEhD4CvClC8T5odHA8E9oT6k9eUtu3fTOs6SYEV5XQs/7xQZL13YZh8jC/v1Xi0PN1ZmMpX5Lag+6h4k6KeJ4V1xpbAvqZ8apoyK8p7hn22G314f1nmTjLVEUi1oJHuaJE40VxIkZaucoSj4g2ZhBVXKdAcfxR; R2SESSIONID_CNC=9D2511A1463DFBA60F92; _gat_MyTracker=1; bm_sv=AE9FCDB7AAFF040501AB9EAA4FE46D36~6KCuez1zyvRrpi+Wk8iUsSy87QcgZhvZUZqL+WKtJfYgDWFnVd1maMF2tx2rri+D6Uvx4f8QucnEGItfxbZS7Y2k7llUMzl1FOqKCelX1n6AREMOKz5FR22CBC64xVGGpMET/6FwWrzsQDbU3b4X/HrA1bBz4VKG0Td7heXM0Vg=; _gali=reserveItemButton");
		return map;
	}
	
	private static Map<String,Object> getStaffAndEquipmentInfos(String html){
		Map<String,Object> map = new HashMap<String, Object>();
		Document doc = Jsoup.parse(html);
		//获取预约情报
		String rvTimeInfo = doc.getElementById("rsvDate").html();
		parseTimeInfo(rvTimeInfo, map);
		
		Elements allTable = doc.getElementsByTag("table");
		//获取担当或者设备信息
		List<Map<String,Object>> staffMapList = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> equipmentMapList = new ArrayList<Map<String,Object>>();
		map.put("staffInfo", staffMapList);
		map.put("equipmentInfo", equipmentMapList);
		Element staffAndEquipmentElement = allTable.get(2);
		Elements staffOrEquipmentTh = staffAndEquipmentElement.getElementsByTag("th");
		for (Element element : staffOrEquipmentTh) {
			boolean isStaff = "スタッフ".equals(element.text());
			Elements trs = null;
			try {
				trs = element.nextElementSibling().getElementsByTag("tr");
			} catch (Exception e) {
				continue;
			}
			for (Element tr : trs) {
				if(tr.getElementsByTag("td").size()<2) {
					continue;
				}
				Map<String,Object> tempMap = new HashMap<String, Object>();
				
				tempMap.put("name", tr.getElementsByTag("div").get(0).text());
				String timeInfo = null;
				if(isStaff) {
					timeInfo = tr.getElementsByTag("div").get(1).text();
				}else {
					timeInfo = tr.getElementsByTag("td").get(1).text();
				}
				String[] staffTimeInfos = StrUtil.split(timeInfo, "～");
				StrUtil.trim(staffTimeInfos);
				tempMap.put("start", LocalTime.parse(staffTimeInfos[0]));
				tempMap.put("end", LocalTime.parse(staffTimeInfos[1]));
				Element appoint = null;
				try {
					appoint = tr.getElementsByTag("div").get(0);
				} catch (Exception e) {
					continue;
				}
				if(appoint!=null) {
					tempMap.put("appoint", appoint.html());
				}
				if(isStaff) {
					staffMapList.add(tempMap);
				}else {
					equipmentMapList.add(tempMap);
				}
			}
		}
		
		return map;
	}
	
//	private static Element findElementsByTag(Element ele,int index,String ... tags) {
//		Element result = ele.clone();
//		for(int i=1;i<=tags.length;i++) {
//			if(i>tags.length) {
//				return null;
//			}
//			Elements eles = result.getElementsByTag(tags[i-1]);
//			if(i==tags.length) {
//				if(index>=eles.size()) {
//					return null;
//				}
//				result = eles.get(index);
//			}else {
//				result = eles.get(0);
//			}
//		}
//		return result;
//	}
	
	private static void parseTimeInfo(String dateInfo,Map<String,Object> map) {
		Pattern p = Pattern.compile("(.+?)（.+?）(.+?)～(.+?)所要時間\\[(.+?)\\].+");
		Matcher m = p.matcher(dateInfo);
		if(m.find()==true) {
			m.matches();
			LocalDate date = LocalDate.parse(StrUtil.trim(m.group(1)), DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
			LocalTime start = LocalTime.parse(StrUtil.trim(m.group(2)));
			LocalTime end = LocalTime.parse(StrUtil.trim(m.group(3)));
			LocalTime zero = LocalTime.parse("00:00");
			long mins = ChronoUnit.MINUTES.between(zero, LocalTime.parse(StrUtil.trim(m.group(4))));
			
			map.put("date", date);
			map.put("start", start);
			map.put("end", end);
			map.put("minutes", mins);
		}
	}
	

}
