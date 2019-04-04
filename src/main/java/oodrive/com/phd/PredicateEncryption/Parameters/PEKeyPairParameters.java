package oodrive.com.phd.PredicateEncryption.Parameters;

public class PEKeyPairParameters {
	
	public PEPublicKeyParamaters publicKeyParams;
	public PEMasterKeyParameters masterKeyParams;
	
	public PEKeyPairParameters(PEPublicKeyParamaters publicKey, PEMasterKeyParameters masterKey) {
		this.publicKeyParams = publicKey;
		this.masterKeyParams = masterKey;
	}

	public PEPublicKeyParamaters getPublicKeyParams() {
		return publicKeyParams;
	}

	public PEMasterKeyParameters getMasterKeyParams() {
		return masterKeyParams;
	}
	
	
	
}
