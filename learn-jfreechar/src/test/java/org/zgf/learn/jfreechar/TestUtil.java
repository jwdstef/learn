package org.zgf.learn.jfreechar;

import java.io.File;
import java.io.IOException;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

public class TestUtil {
	
	/** 输出png图片  */
	public static void printPng(JFreeChart jfreeChart, String fileName){
		try {
			File file = new File("images/" + fileName + ".png");
			ChartUtilities.saveChartAsPNG(file, jfreeChart, 800, 500);
			
//			FileOutputStream fos = new FileOutputStream("images/水果销售图.png");
//			ChartUtilities.writeChartAsPNG(fos, chart, 400, 300);
			System.out.println("图片已经生成：" + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
