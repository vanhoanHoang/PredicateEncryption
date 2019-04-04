package oodrive.com.phd.PredicateEncryption.Parameters;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class PEVectorParamaters {
	public ArrayList<Element> vector;

	public PEVectorParamaters() {
		vector = new ArrayList<Element>();
	}

	public PEVectorParamaters(ArrayList<Element> v) {
		this.vector = v;
	}

	public ArrayList<Element> getVector() {
		return vector;
	}

	
	

}
