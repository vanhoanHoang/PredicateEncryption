package oodrive.com.phd.PredicateEncryption;

import java.security.NoSuchAlgorithmException;

import it.unisa.dia.gas.jpbc.Element;
import oodrive.com.phd.PredicateEncryption.Engines.PEDecryptionEngine;
import oodrive.com.phd.PredicateEncryption.Engines.PEEncryptionEngine;
import oodrive.com.phd.PredicateEncryption.Engines.PEPrivateKeyGenerationEngine;
import oodrive.com.phd.PredicateEncryption.Engines.PESystemEngine;
import oodrive.com.phd.PredicateEncryption.Engines.PESystemKeyGenerationEngine;
import oodrive.com.phd.PredicateEncryption.Engines.PEVectorGenerationEngine;
import oodrive.com.phd.PredicateEncryption.Parameters.PECiphertextParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEKeyPairParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEMasterKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPrivateKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEVectorParamaters;

public class PredicateEncryptionScheme {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println("\n Initializing the system....");
		PESystemEngine systemEngine = new PESystemEngine();
		systemEngine.systemInitializing();

		PESystemKeyGenerationEngine systemKeyGenerationEngine = new PESystemKeyGenerationEngine(
				systemEngine.getSystemParams());
		PEKeyPairParameters keyPairParams = systemKeyGenerationEngine.generateKeyPair();
		PEMasterKeyParameters masterKeyParams = keyPairParams.getMasterKeyParams();
		PEPublicKeyParamaters publicKeyParams = keyPairParams.getPublicKeyParams();

		PEVectorGenerationEngine vectorGenerationEngine = new PEVectorGenerationEngine(publicKeyParams);
		PEVectorParamaters keyVector = vectorGenerationEngine.autoGenerate();
		PEVectorParamaters encryptVector = vectorGenerationEngine.generateInverseScalarVector(keyVector);

		PEPrivateKeyGenerationEngine privateKeyGenerationEngine = new PEPrivateKeyGenerationEngine(masterKeyParams,
				publicKeyParams);
		PEPrivateKeyParameters privateKeyParams = privateKeyGenerationEngine.generatePrivateKey(keyVector);

		// Encrypt
		PEEncryptionEngine encryptionEngine = new PEEncryptionEngine(publicKeyParams);
		Element encryptionData = publicKeyParams.getPairing().getGT().newRandomElement();
		System.out.println("\n Data to encrypt:" + encryptionData);
		PECiphertextParameters ciphertextParams = encryptionEngine.encrypt(encryptionData, encryptVector);

		// Decrypt
		PEDecryptionEngine decryptionEngine = new PEDecryptionEngine(publicKeyParams, privateKeyParams);
		System.out.println("\n The data after decryption: " + decryptionEngine.decrypt(ciphertextParams));

	}

}
