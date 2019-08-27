package common.util;

import java.util.Arrays;
import java.util.List;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.LogAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.json.OptionUtil;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.style.AreaStyle;

/** 
* @date 2019-05-28 17:39:31
* @author LEI
* TODO
*/

public class EChartsUtil {
	
	public static GsonOption getLineOption(List xAxis,List data) {
		GsonOption option = new GsonOption();
		CategoryAxis categoryAxis = new CategoryAxis();
		categoryAxis.setType(AxisType.category);
		categoryAxis.setData(xAxis);
		categoryAxis.setBoundaryGap(false);
		option.setxAxis(Arrays.asList(categoryAxis));
		ValueAxis valueAxis = new ValueAxis();
		option.setyAxis(Arrays.asList(valueAxis));
		Line line = new Line();
		line.setData(data);
		line.setAreaStyle(new AreaStyle());
		option.setSeries(Arrays.asList(line));
		return option;
	}
	
	public static void main(String[] args) {
		List xAxis = Arrays.asList(1,2,3,4,5,6,7);
		List data = Arrays.asList(100,200,50,450,268,487,120);
		System.out.println(getLineOption(xAxis, data).toString());
	}

}
