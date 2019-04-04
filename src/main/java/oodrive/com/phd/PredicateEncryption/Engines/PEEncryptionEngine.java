package oodrive.com.phd.PredicateEncryption.Engines;

import java.util.ArrayList;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import oodrive.com.phd.PredicateEncryption.Parameters.PECiphertextParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEVectorParamaters;

public class PEEncryptionEngine {

	public PEPublicKeyParamaters publicKeyParams;

	public PEEncryptionEngine(PEPublicKeyParamaters publicKeyParams) {
		this.publicKeyParams = publicKeyParams;
	}

	public PECiphertextParameters encrypt(Element data, PEVectorParamaters x) {
		Element C, C_0;
		ArrayList<Element> C1, C2;
		TypeA1Pairing pairing;

		pairing = publicKeyParams.getPairing();
		C_0 = pairing.getG1().newRandomElement();
		C_0 = publicKeyParams.getG_p().duplicate();

		Element s = pairing.getZr().newRandomElement();
		Element alpha = pairing.getZr().newRandomElement();
		Element beta = pairing.getZr().newRandomElement();

		C_0 = publicKeyParams.getG_p().duplicate();
		C_0.powZn(s);

		C = pairing.getGT().newRandomElement();
		C = publicKeyParams.getP().duplicate();
		C.powZn(s);
		C.mul(data);

		C1 = new ArrayList<Element>();
		C2 = new ArrayList<Element>();

		int vectorSize = x.getVector().size();
		for (int i = 0; i < vectorSize; i++) {
			Element c1 = pairing.getG1().newRandomElement();
			c1 = publicKeyParams.getG_r().duplicate();
			Element delta1 = pairing.getZr().newRandomElement();
			c1.powZn(delta1); // Ri

			Element h1 = pairing.getG1().newRandomElement();
			h1 = publicKeyParams.getH1List().get(i).duplicate();
			h1.powZn(s);
			c1.mul(h1);

			Element Q1 = pairing.getG1().newRandomElement();
			Q1 = publicKeyParams.getQ().duplicate();
			Q1.powZn(alpha);
			Q1.powZn(x.getVector().get(i));
			c1.mul(Q1);
			C1.add(c1);

			Element c2 = pairing.getG1().newRandomElement();
			c2 = publicKeyParams.getG_r().duplicate();
			Element delta2 = pairing.getZr().newRandomElement();
			c2.powZn(delta2);

			Element h2 = pairing.getG1().newRandomElement();
			h2 = publicKeyParams.getH2List().get(i).duplicate();
			h2.powZn(s);
			c2.mul(h2);

			Element Q2 = pairing.getG1().newRandomElement();
			Q2 = publicKeyParams.getQ().duplicate();
			Q2.powZn(beta);
			Q2.powZn(x.getVector().get(i));

			c2.mul(Q2);
			C2.add(c2);
		}
		PECiphertextParameters cipherParams = new PECiphertextParameters(C, C_0, C1, C2);

		return cipherParams;
	}
}
