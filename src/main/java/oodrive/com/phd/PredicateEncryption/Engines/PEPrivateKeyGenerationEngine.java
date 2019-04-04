package oodrive.com.phd.PredicateEncryption.Engines;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import oodrive.com.phd.PredicateEncryption.Parameters.PEMasterKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPrivateKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEVectorParamaters;
import oodrive.com.phd.PredicateEncryption.Sources.SystemConfiguration;

public class PEPrivateKeyGenerationEngine {

	public PEMasterKeyParameters masterKeyParams;
	public PEPublicKeyParamaters publicKeyParams;

	public PEPrivateKeyGenerationEngine(PEMasterKeyParameters masterKeyParameters,
			PEPublicKeyParamaters publicKeyParameters) {
		this.masterKeyParams = masterKeyParameters;
		this.publicKeyParams = publicKeyParameters;
	}

	public PEMasterKeyParameters getMasterKeyParams() {
		return masterKeyParams;
	}

	public PEPrivateKeyParameters generatePrivateKey(PEVectorParamaters v) {
		Element R5, Q6;
		Element f1, f2;
		Element exp1, exp2;
		Element K;
		ArrayList<Element> K1 = new ArrayList<Element>();
		ArrayList<Element> K2 = new ArrayList<Element>();
		TypeA1Pairing pairing;

		pairing = publicKeyParams.getPairing();
		R5 = pairing.getG1().newRandomElement();
		R5 = publicKeyParams.getG_r().duplicate();
		Q6 = pairing.getG1().newRandomElement();
		Q6 = masterKeyParams.getG_q().duplicate();

		exp1 = pairing.getZr().newRandomElement();
		R5.powZn(exp1);
		exp2 = pairing.getZr().newRandomElement();
		Q6.powZn(exp2);

		K = pairing.getG1().newRandomElement();
		K = R5.duplicate();
		K.mul(Q6);
		K.mul(masterKeyParams.getH_gama());

		f1 = pairing.getZr().newRandomElement();
		f2 = pairing.getZr().newRandomElement();

		for (int i = 0; i < SystemConfiguration.IN_DEPHT; i++) {
			Element r1, r2;
			Element g_qf1, g_qf2;
			Element K1_i, K2_i;
			Element h1, h2;

			r1 = pairing.getZr().newRandomElement();
			r2 = pairing.getZr().newRandomElement();

			K1_i = pairing.getG1().newRandomElement();
			K1_i = publicKeyParams.getG_p().duplicate();
			K1_i.powZn(r1);

			g_qf1 = pairing.getG1().newRandomElement();
			g_qf1 = masterKeyParams.getG_q().duplicate();
			g_qf1.powZn(f1);
			g_qf1.powZn(v.getVector().get(i));

			K1_i.mul(g_qf1);
			K1.add(K1_i);

			K2_i = pairing.getG1().newRandomElement();
			K2_i = publicKeyParams.getG_p().duplicate();
			K2_i.powZn(r2);

			g_qf2 = pairing.getG2().newRandomElement();
			g_qf2 = masterKeyParams.getG_q().duplicate();
			g_qf2.powZn(f2);
			g_qf2.powZn(v.getVector().get(i));

			K2_i.mul(g_qf2);
			K2.add(K2_i);

			h1 = pairing.getG1().newRandomElement();
			h1 = masterKeyParams.geth1List().get(i).duplicate();
			h2 = pairing.getG2().newRandomElement();
			h2 = masterKeyParams.geth2List().get(i).duplicate();
			r1.negate();
			r2.negate();

			h1.powZn(r1);
			h2.powZn(r2);
			K.mul(h1);
			K.mul(h2);
		}

		PEPrivateKeyParameters privateKeyParams = new PEPrivateKeyParameters(K, K1, K2);
		return privateKeyParams;
	}

}
