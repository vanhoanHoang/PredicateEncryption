package oodrive.com.phd.PredicateEncryption.Engines;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEVectorParamaters;
import oodrive.com.phd.PredicateEncryption.Sources.SystemConfiguration;

public class PEVectorGenerationEngine {

	public PEPublicKeyParamaters publicKeyParams;

	public PEVectorGenerationEngine(PEPublicKeyParamaters publicKeyParams) {
		this.publicKeyParams = publicKeyParams;
	}

	public PEVectorParamaters autoGenerate() {
		PEVectorParamaters vectorParams = new PEVectorParamaters();
		for (int i = 0; i < SystemConfiguration.IN_DEPHT; i++) {
			TypeA1Pairing pairing = publicKeyParams.getPairing();
			Element Vi = pairing.getZr().newRandomElement();
			vectorParams.getVector().add(Vi);
		}
		return vectorParams;
	}

	public PEVectorParamaters generateInverseScalarVector(PEVectorParamaters V) {
		PEVectorParamaters X = new PEVectorParamaters();
		TypeA1Pairing pairing = publicKeyParams.getPairing();
		int vectorSize = V.getVector().size();

		for (int i = 0; i < vectorSize - 1; i++) {
			Element xi = pairing.getZr().newRandomElement();
			X.getVector().add(xi);
		}
		Element S = pairing.getZr().newZeroElement();
		for (int i = 0; i < vectorSize - 1; i++) {
			Element si = pairing.getZr().newRandomElement();
			si = V.getVector().get(i).duplicate();
			si.mul(X.getVector().get(i));

			S.add(si);
		}
		Element si = pairing.getZr().newRandomElement();
		si = S.duplicate();
		si.negate();
		si.div(V.getVector().get(vectorSize - 1));

		X.getVector().add(si);

		return X;
	}

	public boolean testVectorScalar(PEVectorParamaters X, PEVectorParamaters V) {
		int n = X.getVector().size();
		TypeA1Pairing pairing = publicKeyParams.getPairing();
		Element S = pairing.getZr().newZeroElement();
		for (int i = 0; i < n; i++) {
			Element x, v;
			x = pairing.getZr().newRandomElement();
			v = pairing.getZr().newRandomElement();

			x = X.getVector().get(i).duplicate();
			v = V.getVector().get(i).duplicate();
			
			x.mul(v);
			S.add(x);
			
		}
		System.out.println("\n Gia tri tong la: S: "+S);
		return S.isZero();
	}
}
