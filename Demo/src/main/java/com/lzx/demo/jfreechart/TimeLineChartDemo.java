package com.lzx.demo.jfreechart;

import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;

public class TimeLineChartDemo {
	public static void main(String[] args) throws Exception {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		TimeSeriesCollection collection = new TimeSeriesCollection();
		TimeSeries aSeries = new TimeSeries("aaaa");
		long time=System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			Date d = new Date(time+i*1000*60*60*24l*30);
			TimeSeriesDataItem item = new TimeSeriesDataItem(new Second(d), i*10);
			aSeries.add(item);
		}
		collection.addSeries(aSeries);
		JFreeChart chart = ChartFactory.createTimeSeriesChart(null, "a", null,
				collection, true, true, false);
		// 下面几句是对设置折线图数据标示的关键代码
		XYItemRenderer xyitem = chart.getXYPlot().getRenderer();
		xyitem.setBaseItemLabelsVisible(true);
		xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 14));
		chart.getXYPlot().setRenderer(xyitem);
		ChartUtilities.saveChartAsJPEG(new File("d:/时间折线图.jpg"), chart, 1000,
				500);
	}
}
