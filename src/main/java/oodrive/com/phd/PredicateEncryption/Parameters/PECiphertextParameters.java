package oodrive.com.phd.PredicateEncryption.Parameters;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class PECiphertextParameters {
	public Element C;
	public Element C_0;
	public ArrayList<Element> C1;
	public ArrayList<Element> C2;

	public PECiphertextParameters(Element C, Element C_0, ArrayList<Element> C1, ArrayList<Element> C2) {
		this.C = C.duplicate();
		this.C_0 = C_0.duplicate();
		this.C1 = C1;
		this.C2 = C2;
	}

	public Element getC() {
		return C;
	}

	public PECiphertextParameters() {
	
	}

	public Element getC_0() {
		return C_0;
	}

	public ArrayList<Element> getC1() {
		return C1;
	}

	public ArrayList<Element> getC2() {
		return C2;
	}

}
