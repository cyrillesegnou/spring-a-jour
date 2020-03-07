package fr.gtm.bovoyages.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {
	public static String toSha256(String plain) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] octets = digest.digest(plain.getBytes());
		StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<octets.length ; i++) {
			sb.append(String.format("%02x", octets[i]));
		}
		return sb.toString();
	}

}
