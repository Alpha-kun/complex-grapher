package com;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grapher {

	public static void main(String[] args) throws IOException {
		double u = -2;
		double d = 2;
		double l = -2;
		double r = 2;
		int resH = 2000;
		int resV = 2000;
		double dim = (d - u) / resV;
		double dre = (r - l) / resH;
		BufferedImage bi = new BufferedImage(resH, resV, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < resV; i++) {
			for (int j = 0; j < resH; j++) {
				ComplexNumber z = new ComplexNumber(u + dim * i, l + dre * j);
				bi.setRGB(i, j, toColor(f(z)).getRGB());
			}
		}
		ImageIO.write(bi, "JPEG", new File("D:\\Math\\graph\\result#41.jpg"));
	}

	static Color toColor(ComplexNumber z) {
		if (z.equals(ComplexMath.INF)) {
			return new Color(127, 127, 127);
		}
		return Color.getHSBColor(tof(ComplexMath.arg(z) / (PI * 2)), 1.0f, tof(2 / PI * Math.atan(5*ComplexMath.abs(z))));
	}

	static final double PI = Math.PI;

	static ComplexNumber f(ComplexNumber z) {
		return z.mult(ComplexMath.exp(z));
	}

	static float tof(double d) {
		return (float) d;
	}

	static ComplexNumber vi(double d) {
		return new ComplexNumber(0, d);
	}
}
