package com.lzx.demo.jfreechart;

import java.awt.Font;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * 折线图
 * @author lzx
 *
 */
public class LineChartDemo {
	public static void main(String[] args) throws Exception {
		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		   //设置标题字体  
		   standardChartTheme.setExtraLargeFont(new Font("隶书",Font.BOLD,20));  
		   //设置图例的字体  
		   standardChartTheme.setRegularFont(new Font("宋书",Font.PLAIN,15));  
		   //设置轴向的字体  
		   standardChartTheme.setLargeFont(new Font("宋书",Font.PLAIN,15));  
		   //应用主题样式  
		   ChartFactory.setChartTheme(standardChartTheme);
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		// 各曲线名称
		String series1 = "冰箱";
		String series2 = "彩电";
		String series3 = "洗衣机";
		// 横轴名称(列名称)
		String type1 = "1月";
		String type2 = "2月";
		String type3 = "3月";
		linedataset.addValue(0.0, series1, type1);
		linedataset.addValue(4.2, series1, type2);
		linedataset.addValue(3.9, series1, type3);
		linedataset.addValue(1.0, series2, type1);
		linedataset.addValue(5.2, series2, type2);
		linedataset.addValue(7.9, series2, type3);
		linedataset.addValue(2.0, series3, type1);
		linedataset.addValue(9.2, series3, type2);
		linedataset.addValue(8.9, series3, type3);
		linedataset.addValue(4.2, series1, type2);
		linedataset.addValue(3.9, series1, type3);
		JFreeChart chart = ChartFactory.createLineChart("一季度销售曲线", // 折线图名称
				"时间", // 横坐标名称
				"销售额(百万)", // 纵坐标名称
				linedataset, // 数据
				PlotOrientation.VERTICAL, // 水平显示图像
				true, // include legend
				false, // tooltips
				false // urls
				);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinesVisible(true); // 是否显示格子线
		plot.setBackgroundAlpha(0.3f); // 设置背景透明度
		LineAndShapeRenderer renderer=(LineAndShapeRenderer) plot.getRenderer();
		//数值的显示
        renderer.setBaseItemLabelsVisible(true);//基本项标签显示
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);
		rangeAxis.setUpperMargin(0.20);
		rangeAxis.setLabelAngle(Math.PI / 2.0);
		ChartUtilities.saveChartAsJPEG(new File("d:/折线图.jpg"), chart, 800, 500);
	}
}
