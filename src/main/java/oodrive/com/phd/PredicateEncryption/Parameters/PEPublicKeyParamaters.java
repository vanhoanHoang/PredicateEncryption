package oodrive.com.phd.PredicateEncryption.Parameters;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;

public class PEPublicKeyParamaters{
	
	public Element g_p;
	public Element g_r;
	public Element Q;
	public TypeA1Pairing pairing; 
	public Element P;
	public ArrayList<Element> H1List;
	public ArrayList<Element> H2List;
	
	public PEPublicKeyParamaters(TypeA1Pairing pairing, Element g_p, Element g_r, Element Q, Element P, ArrayList<Element> H1List , ArrayList<Element> H2List){
		this.g_p = g_p;
		this.g_r = g_r;
		this.Q = Q;
		this.pairing = pairing;
		this.P = P;
		this.H1List = H1List;
		this.H2List = H2List;
	}

	public Element getP() {
		return P;
	}

	public TypeA1Pairing getPairing() {
		return pairing;
	}

	public Element getG_p() {
		return g_p;
	}

	public Element getG_r() {
		return g_r;
	}

	public Element getQ() {
		return Q;
	}

	public ArrayList<Element> getH1List() {
		return H1List;
	}
	
	public ArrayList<Element> getH2List() {
		return H2List;
	}
}