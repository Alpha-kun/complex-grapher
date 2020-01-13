package com;

public class ComplexNumber {

	final double a;
	final double b;

	public ComplexNumber(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public double Re() {
		return a;
	}

	public double Im() {
		return b;
	}

	public double abs() {
		return Math.hypot(a, b);
	}

	public ComplexNumber neg() {
		return new ComplexNumber(-a, -b);
	}

	public ComplexNumber congu() {
		return new ComplexNumber(a, -b);
	}

	public ComplexNumber inv() {
		if (a == 0.0 && b == 0.0) {
			return new ComplexNumber(Double.NaN, Double.NaN);
		}
		return new ComplexNumber(a / abs() / abs(), -b / abs() / abs());
	}

	public ComplexNumber add(ComplexNumber o) {
		return new ComplexNumber(a + o.a, b + o.b);
	}

	public ComplexNumber sub(ComplexNumber o) {
		return add(o.neg());
	}

	public ComplexNumber mult(ComplexNumber o) {
		return new ComplexNumber(a * o.Re() - b * o.Im(), a * o.Im() + b * o.Re());
	}

	public ComplexNumber div(ComplexNumber o) {
		return mult(o.inv());
	}

	@Override
	public boolean equals(Object z) {
		if (z instanceof ComplexNumber) {
			return Double.compare(this.a, ((ComplexNumber) z).a) == 0
					&& Double.compare(this.b, ((ComplexNumber) z).b) == 0;
		}
		return false;

	}
}
