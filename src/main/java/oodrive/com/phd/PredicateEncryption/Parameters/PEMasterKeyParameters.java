package oodrive.com.phd.PredicateEncryption.Parameters;

import java.math.BigInteger;
import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;

public class PEMasterKeyParameters {
	public BigInteger p, q, r;
	public Element g_q;
	public Element h_gama;
	public ArrayList<Element> h1List;
	public ArrayList<Element> h2List;

	public PEMasterKeyParameters(BigInteger p, BigInteger q, BigInteger r, Element g_q, Element h_gama, ArrayList<Element> h1List, ArrayList<Element> h2List) {
		this.p = p;
		this.q = q; 
		this.r = r; 
		this.g_q = g_q.duplicate();
		this.h_gama = h_gama;
		this.h1List = h1List;
		this.h2List = h2List;
	}

	
	public Element getH_gama() {
		return h_gama;
	}

	public BigInteger getP() {
		return p;
	}

	public BigInteger getQ() {
		return q;
	}

	public BigInteger getR() {
		return r;
	}

	public Element getG_q() {
		return g_q;
	}

	public ArrayList<Element> geth1List() {
		return h1List;
	}
	
	public ArrayList<Element> geth2List() {
		return h2List;
	}

}
