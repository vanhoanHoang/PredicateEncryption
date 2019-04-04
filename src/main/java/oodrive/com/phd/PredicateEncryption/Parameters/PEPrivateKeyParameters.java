package oodrive.com.phd.PredicateEncryption.Parameters;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class PEPrivateKeyParameters {
	public Element K;
	public ArrayList<Element> K1; 
	public ArrayList<Element> K2; 
	
	
	public PEPrivateKeyParameters(Element K, ArrayList<Element> K1, ArrayList<Element> K2){
		this.K = K.duplicate();
		this.K1 = K1;
		this.K2 = K2;
	}


	public Element getK() {
		return K;
	}


	public ArrayList<Element> getK1() {
		return K1;
	}


	public ArrayList<Element> getK2() {
		return K2;
	}
	
	
}
