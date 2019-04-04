package oodrive.com.phd.PredicateEncryption.Engines;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.pairing.a1.TypeA1Pairing;
import oodrive.com.phd.PredicateEncryption.Parameters.PECiphertextParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPrivateKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Sources.SystemConfiguration;

public class PEDecryptionEngine {
	public PEPrivateKeyParameters privateKeyParams;
	public PEPublicKeyParamaters publicKeyParamas;

	public PEDecryptionEngine(PEPublicKeyParamaters publicKeyParams, PEPrivateKeyParameters privateKeyParams) {
		this.privateKeyParams = privateKeyParams;
		this.publicKeyParamas = publicKeyParams;

	}

	public Element decrypt(PECiphertextParameters ciphertextParams) {
		Element data;
		TypeA1Pairing pairing = publicKeyParamas.getPairing();

		data = pairing.getGT().newRandomElement();
		data = pairing.pairing(ciphertextParams.getC_0(), privateKeyParams.getK()).duplicate();
		data.mul(ciphertextParams.getC());

		for (int i = 0; i < SystemConfiguration.IN_DEPHT; i++) {
			Element C1, C2;
			C1 = pairing.pairing(ciphertextParams.getC1().get(i), privateKeyParams.getK1().get(i)).duplicate();
			C2 = pairing.pairing(ciphertextParams.getC2().get(i), privateKeyParams.getK2().get(i)).duplicate();
			data.mul(C1);
			data.mul(C2);
		}
		return data;
	}
}
