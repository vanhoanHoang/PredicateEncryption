package oodrive.com.phd.PredicateEncryption.Parameters;

import java.math.BigInteger;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;

public class PESystemParameters {

	public TypeA1Pairing pairing;
	public Element g; // Generator
	public Element g_p;
	public Element g_q;
	public Element g_r;
	public BigInteger p, q, r;

	public PESystemParameters(TypeA1Pairing pairing, Element g, Element g_p, Element g_q, Element g_r, BigInteger p, BigInteger q, BigInteger r) {
		this.pairing = pairing;
		this.g= g;
		this.g_p = g_p;
		this.g_q = g_q;
		this.g_r = g_r;
		this.p = p;
		this.q = q;
		this.r = r;
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

	public TypeA1Pairing getPairing() {
		return pairing;
	}

	public Element getG() {
		return g;
	}

	public Element getG_p() {
		return g_p;
	}

	public Element getG_q() {
		return g_q;
	}

	public Element getG_r() {
		return g_r;
	}

}
