package com.lzx.demo.jfreechart;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.NumberFormat;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * 状状图
 * 
 * @author lzx
 * 
 */
public class HistogramDemo {
	public static void main(String[] args) throws Exception {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(0.123, "深圳", "苹果");
		dataset.addValue(0.2, "深圳", "香蕉");
		dataset.addValue(0.3, "深圳", "橘子");
		dataset.addValue(0.4, "深圳", "梨子");
		JFreeChart chart = ChartFactory.createBarChart3D("水果销量统计图", null, null,
				dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.getTitle().setFont(new Font("隶书", Font.ITALIC, 20));// 设置标题
		chart.getLegend().setItemFont(new Font("隶书", Font.ITALIC, 20));// 设置标题栏
		CategoryPlot categoryPlot = chart.getCategoryPlot();// 获得 plot,用于设置显示特性
		categoryPlot.setBackgroundPaint(ChartColor.WHITE);
		categoryPlot.setRangeGridlinePaint(ChartColor.white);
		// 图表的字体设置
		CategoryAxis domainAxis = categoryPlot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("楷体", Font.BOLD, 14)); // X轴标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // x轴下标

		NumberAxis3D rangeAxis = (NumberAxis3D) categoryPlot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("楷体", Font.BOLD, 14)); // Y轴标题
		rangeAxis.setNumberFormatOverride(NumberFormat.getPercentInstance());
		// 显示柱子数值
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",NumberFormat.getPercentInstance()));
		renderer.setBaseItemLabelFont(new Font("楷体", Font.ITALIC, 20) );
		categoryPlot.setRenderer(renderer);
		renderer.setBaseItemLabelsVisible(true);
		// 将柱子显示的数值显示到上方
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10);
		ChartUtilities.saveChartAsJPEG(new File("d:/柱状图.jpg"), chart, 1000, 600);
//		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		// ChartUtilities.writeChartAsJPEG(System.out, chart, 800, 500);
	}
}
