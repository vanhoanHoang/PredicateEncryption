package oodrive.com.phd.PredicateEncryption.Engines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import it.unisa.dia.gas.jpbc.Element;

public class PreProcessingEngine {
	public PreProcessingEngine() {

	}

	public static void elementFromString(Element h, String data) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("SHA-1");
		byte[] digest = md.digest(data.toString().getBytes());
		h.setFromHash(digest, 0, digest.length);
	}

}
