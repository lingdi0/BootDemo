package common.util;

import java.util.Arrays;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.LogAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.json.OptionUtil;
import com.github.abel533.echarts.series.Line;

/** 
* @date 2019-05-28 17:39:31
* @author LEI
* TODO
*/

public class EChartsUtil {
	
	public static void main(String[] args) {
		GsonOption option = new GsonOption();
		LogAxis logAxis = new LogAxis();
		option.setxAxis(Arrays.asList(logAxis));
		logAxis.setType(AxisType.category);
		logAxis.setData(Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
		ValueAxis valueAxis = new ValueAxis();
		option.setyAxis(Arrays.asList(valueAxis));
		valueAxis.setType(AxisType.value);
		Line line = new Line();
		line.setData(Arrays.asList(820, 932, 901, 934, 1290, 1330, 1320));
//		line.setType(SeriesType.line);
		option.setSeries(Arrays.asList(line));
		System.out.println(option.toString());
	}

}
