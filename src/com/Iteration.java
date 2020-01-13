package com;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Iteration {

	static ComplexNumber[][] paint() {
		ComplexNumber[][] lv = new ComplexNumber[res][res];
		double inc = (2 * r) / res;
		double x = lx;
		for (int i = 0; i < res; i++) {
			System.out.println("computing line " + i);
			double y = ly;
			for (int j = 0; j < res; j++) {
				lv[i][j] = iterate(new ComplexNumber(x, y));
				y += inc;
			}
			x += inc;
		}
		return lv;
	}

	static double cx = -0.5;
	static double cy = 0.0;

	static double r = 1.0;

	static double lx = cx - r;
	static double ly = cy - r;

	static ComplexNumber iterate(ComplexNumber z) {
		ComplexNumber n = new ComplexNumber(0, 0);
		for (int i = 1; i <= 255; i++) {
			n = n.mult(n).add(z);
			if (n.abs() > 2) {
				return ComplexMath.INF;
			}
		}
		return n;
	}

	static int color(int l) {
		if (l >= 256) {
			return 0;
		}
		int a = 0b11111111 << 24;
		int r = (l) << 16;
		int b = 255 - (l);
		return a | r | b;
	}

	static int res = 1000;

	public static void main(String[] args) throws IOException {
		ComplexNumber[][] lv = paint();
		BufferedImage bi = new BufferedImage(res, res, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < res; i++) {
			for (int j = 0; j < res; j++) {
				bi.setRGB(i, j, Grapher.toColor(lv[i][j]).getRGB());
			}
		}
		ImageIO.write(bi, "JPEG", new File("D:\\Math\\graph\\resultFractal5.jpg"));
	}
}
