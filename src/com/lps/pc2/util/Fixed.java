package com.lps.pc2.util;

import java.text.DecimalFormat;

public class Fixed {
	private DecimalFormat df;
	public Fixed(int fixedNum) {
		StringBuffer sb = new StringBuffer("#.");
		for (int i = 0; i < fixedNum; i++) {
			sb.append("0");
		}
		this.df = new DecimalFormat(sb.toString());
	}
	public double fixed(double num) {
		return Double.parseDouble(df.format(num));
	}
}
