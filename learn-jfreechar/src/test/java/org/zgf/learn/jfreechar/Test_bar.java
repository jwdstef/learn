package org.zgf.learn.jfreechar;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;
import org.junit.Test;

public class Test_bar {

	/** 默认数据集*/
	@Test
	public void test_1() {

		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.addValue(100, "北京", "苹果");
		dataSet.addValue(200, "北京", "香蕉");
		dataSet.addValue(300, "北京", "鸭梨");
		dataSet.addValue(400, "北京", "橘子");

		JFreeChart jfreeChart = ChartFactory.createBarChart3D("水果销售图", "水果", "销售", dataSet, PlotOrientation.VERTICAL, true,true, true);

		TestUtil.printPng(jfreeChart,"1.1 柱状图显示_默认数据集");
	}
	
	/** 自定义数据集   */
	@Test
	public void test_2(){
		
		double[][] data = new double[][]{
			{150,200,250,500},
			{200,250,300,550},
			{175,275,375,475},
			{225,200,325,525}
		};
		
		String[] rowKeys = new String[]{"北京","上海","香港","澳门"};
		String[] columnKeys = new String[]{"苹果","鸭梨","橘子","香蕉"};
		CategoryDataset dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
		JFreeChart jfreeChart = ChartFactory.createBarChart3D("水果销售图", "水果", "销售", dataSet, PlotOrientation.VERTICAL, true, true, true);
		
		TestUtil.printPng(jfreeChart, "1.2 柱状图显示_自定义数据集");
	}
	
	
	/** 自定义 样式   */
	@Test
	public void test_3(){
		
		double[][] data = new double[][]{
			{150,200,250,500},
			{200,250,300,550},
			{175,275,375,475},
			{225,200,325,525}
		};
		
		String[] rowKeys = new String[]{"北京","上海","香港","澳门"};
		String[] columnKeys = new String[]{"苹果","鸭梨","橘子","香蕉"};
		CategoryDataset dataSet = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
		JFreeChart jfreeChart = ChartFactory.createBarChart3D("水果销售图", "水果", "销售", dataSet, PlotOrientation.VERTICAL, true, true, true);
		
		CategoryPlot plot=jfreeChart.getCategoryPlot();
		// 设置网格背景颜色
		plot.setBackgroundPaint(Color.white);
		// 设置网格竖线颜色
		plot.setDomainGridlinePaint(Color.pink);
		// 设置网格横线颜色
		plot.setRangeGridlinePaint(Color.pink);
		
		// 显示每个柱的数值，并修改该数值的字体属性
		BarRenderer3D renderer=new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);  
		
		// 设置平行柱的之间距离
		renderer.setItemMargin(0.4);
		
		plot.setRenderer(renderer);
		
		TestUtil.printPng(jfreeChart, "1.3 柱状图显示_自定义样式");
	}
}
