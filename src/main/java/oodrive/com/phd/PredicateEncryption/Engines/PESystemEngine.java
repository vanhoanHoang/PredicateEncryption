package oodrive.com.phd.PredicateEncryption.Engines;

import java.math.BigInteger;
import java.security.SecureRandom;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1CurveGenerator;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import oodrive.com.phd.PredicateEncryption.Parameters.PESystemParameters;

public class PESystemEngine {
	public PESystemParameters systemParameters;

	public PESystemEngine() {

	}

	public void systemInitializing() {
		Element g, g_p, g_q, g_r;
		BigInteger p, q, r;
		TypeA1Pairing pairing;

		TypeA1CurveGenerator paramGenerator = new TypeA1CurveGenerator(new SecureRandom(), 3, 128);
		PairingParameters pairingParam = paramGenerator.generate();
		pairing = new TypeA1Pairing(pairingParam);

		g = pairing.getG1().newRandomElement();
		g_p = pairing.getG1().newRandomElement();
		g_q = pairing.getG1().newRandomElement();
		g_r = pairing.getG1().newRandomElement();

		p = pairingParam.getBigInteger("n0");
		q = pairingParam.getBigInteger("n1");
		r = pairingParam.getBigInteger("n2");

		g.mul(pairingParam.getBigInteger("l"));

		g_p.mul(q);
		g_p.mul(r);

		g_q.mul(p);
		g_q.mul(r);

		g_r.mul(p);
		g_r.mul(q);

		this.systemParameters = new PESystemParameters(pairing, g, g_p, g_q, g_r, p, q, r);
	}

	public PESystemParameters getSystemParams() {
		return systemParameters;
	}
}
