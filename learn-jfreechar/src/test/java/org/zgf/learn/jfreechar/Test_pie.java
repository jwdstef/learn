package org.zgf.learn.jfreechar;

import java.awt.Font;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.junit.Test;

public class Test_pie {
	
	/** 基本显示  */
	@Test
	public void test_1(){
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("黑心矿难", 1000);
		dataset.setValue("醉酒驾驶", 800);
		dataset.setValue("城管强拆", 400);
		dataset.setValue("医疗事故", 100);
		dataset.setValue("其他", 29);

		JFreeChart jfreeChart=ChartFactory.createPieChart("非正常死亡人数分布图", dataset, true, true, true);
		
		// 副标题
		jfreeChart.addSubtitle(new TextTitle("2013年度"));
		
		PiePlot pieplot=(PiePlot)jfreeChart.getPlot();
		pieplot.setLabelFont(new Font("宋体",0,11));
		// 设置饼图是圆的（true），还是椭圆的（false）；默认为true  
		pieplot.setCircular(true);
		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1}.{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
		pieplot.setLabelGenerator(standarPieIG); 
		
		TestUtil.printPng(jfreeChart, "2.1.饼状图");
	}
	
	/** 突出显示    */
	@Test
	public void test_2(){
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("黑心矿难", 1000);
		dataset.setValue("醉酒驾驶", 800);
		dataset.setValue("城管强拆", 400);
		dataset.setValue("医疗事故", 100);
		dataset.setValue("其他", 29);

		JFreeChart jfreeChart=ChartFactory.createPieChart("非正常死亡人数分布图", dataset, true, true, true);
		
		// 副标题
		jfreeChart.addSubtitle(new TextTitle("2013年度"));
		
		PiePlot pieplot=(PiePlot)jfreeChart.getPlot();
		pieplot.setLabelFont(new Font("宋体",0,11));
		// 设置饼图是圆的（true），还是椭圆的（false）；默认为true  
		pieplot.setCircular(true);
		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:({1}.{2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());  
		pieplot.setLabelGenerator(standarPieIG); 
		
		pieplot.setExplodePercent("醉酒驾驶", 0.1);
		
		TestUtil.printPng(jfreeChart, "2.2饼状图--突出显示");
	}
	
	/** 3D显示    */
	@Test
	public void test_3(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("黑心矿难", 1000);
		dataset.setValue("醉酒驾驶", 800);
		dataset.setValue("城管强拆", 400);
		dataset.setValue("医疗事故", 100);
		dataset.setValue("其他", 29);

		JFreeChart jfreeChart=ChartFactory.createPieChart3D("非正常死亡人数分布图", dataset, true, true, true);
		
		// 副标题
		jfreeChart.addSubtitle(new TextTitle("2013年度"));
		
		
		PiePlot3D pieplot3d = (PiePlot3D)jfreeChart.getPlot(); 
		//设置开始角度  
		pieplot3d.setStartAngle(120D);  
		//设置方向为”顺时针方向“  
		pieplot3d.setDirection(Rotation.CLOCKWISE);  
		//设置透明度，0.5F为半透明，1为不透明，0为全透明  
		pieplot3d.setForegroundAlpha(0.7F); 
		
		//3D 显示时不能突出显示
		TestUtil.printPng(jfreeChart, "3.2饼状图--3D显示");
	}
	

}
