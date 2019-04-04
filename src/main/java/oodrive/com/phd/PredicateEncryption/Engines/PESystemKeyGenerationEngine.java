package oodrive.com.phd.PredicateEncryption.Engines;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import oodrive.com.phd.PredicateEncryption.Parameters.PEKeyPairParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEMasterKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Parameters.PESystemParameters;
import oodrive.com.phd.PredicateEncryption.Sources.SystemConfiguration;

public class PESystemKeyGenerationEngine {

	public PESystemParameters systemParams;

	public PESystemKeyGenerationEngine(PESystemParameters systemParams) {
		this.systemParams = systemParams;
	}

	public PEKeyPairParameters generateKeyPair() {
		TypeA1Pairing pairing = systemParams.getPairing();
		ArrayList<Element> h1 = new ArrayList<Element>(); // Group Gp
		ArrayList<Element> h2 = new ArrayList<Element>(); // Group Gp
		ArrayList<Element> H1 = new ArrayList<Element>(); // Group Gr * Gp
		ArrayList<Element> H2 = new ArrayList<Element>(); // Group Gr * Gp
		Element Q; // Group Q*R
		Element exp, exp0, gamma;
		Element P; // Group GT
		Element g_p, g_q, g_r, h, h_gamma;

		g_p = pairing.getG1().newRandomElement();
		g_q = pairing.getG1().newRandomElement();
		g_r = pairing.getG1().newRandomElement();

		g_p = systemParams.getG_p().duplicate();
		g_q = systemParams.getG_q().duplicate();
		g_r = systemParams.getG_r().duplicate();

		exp0 = pairing.getZr().newRandomElement();
		h = pairing.getG1().newRandomElement();
		h = g_p.duplicate();
		h.powZn(exp0);

		h_gamma = pairing.getG1().newRandomElement();
		h_gamma = h.duplicate();

		gamma = pairing.getZr().newRandomElement();
		P = pairing.getGT().newRandomElement();
		P = pairing.pairing(g_p, h).duplicate();
		P.powZn(gamma);
		gamma.negate();
		h_gamma.powZn(gamma);

		for (int i = 0; i < SystemConfiguration.IN_DEPHT; i++) {
			Element h1_i, h2_i, H1_i, H2_i;
			Element exp1, exp2, exp3, exp4;

			h1_i = pairing.getG1().newRandomElement();
			h1_i = g_p.duplicate();
			exp1 = pairing.getZr().newRandomElement();
			h1_i.powZn(exp1);

			h2_i = pairing.getG1().newRandomElement();
			h2_i = g_p.duplicate();
			exp2 = pairing.getZr().newRandomElement();
			h2_i.powZn(exp2);

			H1_i = pairing.getG1().newRandomElement();
			H1_i = g_r.duplicate();
			exp3 = pairing.getZr().newRandomElement();
			H1_i.powZn(exp3);
			H1_i.mul(h1_i);

			H2_i = pairing.getG1().newRandomElement();
			H2_i = g_r.duplicate();
			exp4 = pairing.getZr().newRandomElement();
			H2_i.powZn(exp4);
			H2_i.mul(h2_i);

			h1.add(h1_i);
			h2.add(h2_i);
			H1.add(H1_i);
			H2.add(H2_i);
		}

		Q = pairing.getG1().newRandomElement();
		Q = g_r.duplicate();
		exp = pairing.getZr().newRandomElement();
		Q.powZn(exp);
		Q.mul(g_q);

		PEPublicKeyParamaters publicKeyParams = new PEPublicKeyParamaters(pairing, g_p, g_r, Q, P, H1, H2);

		PEMasterKeyParameters masterKeyParams = new PEMasterKeyParameters(systemParams.getP(), systemParams.getQ(),
				systemParams.getR(), g_q, h_gamma, h1, h2);

		PEKeyPairParameters keyPairParameters = new PEKeyPairParameters(publicKeyParams, masterKeyParams);
		return keyPairParameters;
	}

}
