package util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cifrador implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2438269785803644869L;
	private byte[] digest;
	private byte[] buffer;
	private MessageDigest messageDigest;
	
	public Cifrador(){
		
	}
    public String cifrar(String message) {
        digest=null;
        buffer= message.getBytes();
        String hash = "";
        try {
            messageDigest= MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
  
            for (byte aux : digest) {
                int b = aux & 0xff;
                if (Integer.toHexString(b).length() == 1)
                    hash += "0";
                hash += Integer.toHexString(b);
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        for (int i = hash.length(); i < 256; i++) {
            hash += " ";
        }
  
        return hash;
    }
}
