package com.wf.ew.task.util;


import java.io.File;
import java.util.ArrayList;


public class Main {
	public static void main(String[] args) {
		File file = new File("咯.xls");
		ArrayList<Object> list =new ArrayList<Object>();
		for(int i = 0 ;i < 7 ;i++){//列
			list.add("流");
		}
		//ArrayList<ArrayList<Object>> result1 = ExcelUtil.readExcel(file);
		ArrayList<ArrayList<Object>> result =new ArrayList<ArrayList<Object>>();
		System.out.println(result);
		for(int i = 0 ;i < 8 ;i++){//行
			result.add(list);
		}
		ExcelUtil.writeExcel(result, "咯.xls");
		//ArrayList<ArrayList<Object>> list=new ArrayList<ArrayList<Object>>();
		//System.out.println(list.get(0).get(0));
	}
	
}

