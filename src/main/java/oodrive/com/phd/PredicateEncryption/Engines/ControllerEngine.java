package oodrive.com.phd.PredicateEncryption.Engines;

import java.security.NoSuchAlgorithmException;

import it.unisa.dia.gas.jpbc.Element;
import oodrive.com.phd.PredicateEncryption.Parameters.PECiphertextParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEKeyPairParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEMasterKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPrivateKeyParameters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEPublicKeyParamaters;
import oodrive.com.phd.PredicateEncryption.Parameters.PEVectorParamaters;

public class ControllerEngine {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		long keygen, encrypt, decrypt;
		long startTime, endTime;

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

		startTime = System.currentTimeMillis();
		PEPrivateKeyParameters privateKeyParams = privateKeyGenerationEngine.generatePrivateKey(keyVector);
		keygen = System.currentTimeMillis() - startTime;

		// Encrypt
		PEEncryptionEngine encryptionEngine = new PEEncryptionEngine(publicKeyParams);
		Element encryptionData = publicKeyParams.getPairing().getGT().newRandomElement();
		System.out.println("\n Data to encrypt:" + encryptionData);

		startTime = System.currentTimeMillis();
		PECiphertextParameters ciphertextParams = encryptionEngine.encrypt(encryptionData, encryptVector);
		encrypt = System.currentTimeMillis() - startTime;

		// Decrypt
		startTime = System.currentTimeMillis();
		PEDecryptionEngine decryptionEngine = new PEDecryptionEngine(publicKeyParams, privateKeyParams);
		System.out.println("\n The data after decryption: " + decryptionEngine.decrypt(ciphertextParams));
		decrypt = System.currentTimeMillis() - startTime;
		System.out.println("\n KEYGEN: " + keygen + " ENCRYPT: " + encrypt + " DECRYPT:" + decrypt);
	}

}
