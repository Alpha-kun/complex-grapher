package com;

public class ComplexMath {

	static final double PI = Math.PI;
	static final double E = Math.E;
	static final ComplexNumber ZERO = new ComplexNumber(0, 0);
	static final ComplexNumber ONE = new ComplexNumber(1, 0);
	static final ComplexNumber i = new ComplexNumber(0, 1);
	static final ComplexNumber NEG_ONE = new ComplexNumber(-1, 0);
	static final ComplexNumber NEG_i = new ComplexNumber(0, -1);
	static final ComplexNumber INF = new ComplexNumber(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	static ComplexNumber v(double d) {
		return new ComplexNumber(d, 0);
	}

	public static ComplexNumber power(ComplexNumber z, int p) {
		if (p == 0) {
			return ONE;
		}
		ComplexNumber pw = power(z, p >> 1);
		if ((p & 1) == 1) {
			return pw.mult(pw).mult(z);
		} else {
			return pw.mult(pw);
		}
	}

	static ComplexNumber polynomial(ComplexNumber z, double... a) {
		ComplexNumber fx = ZERO;
		for (int i = 0; i < a.length; i++) {
			fx = fx.mult(z);
			fx = fx.add(v(a[i]));
		}
		return fx;
	}

	public static ComplexNumber exp(ComplexNumber z) {
		double r = Math.exp(z.Re());
		ComplexNumber res = new ComplexNumber(Math.cos(z.Im()), Math.sin(z.Im()));
		return v(r).mult(res);
	}

	public static ComplexNumber sin(ComplexNumber z) {
		return exp(z.mult(i)).sub(exp(z.mult(NEG_i))).mult(v(0.5)).mult(NEG_i);
	}

	public static ComplexNumber cos(ComplexNumber z) {
		return exp(z.mult(i)).add(exp(z.mult(NEG_i))).mult(v(0.5));
	}

	// the arg of z in [0,2¦Ð]
	static double arg(ComplexNumber z) {
		double theta = Math.atan2(z.b, z.a);
		if (theta < 0) {
			theta += Math.PI * 2;
		}
		return theta;
	}

	// the magnitude of z
	static double abs(ComplexNumber z) {
		return Math.hypot(z.a, z.b);
	}
}
