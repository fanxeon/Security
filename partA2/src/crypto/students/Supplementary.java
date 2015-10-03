package crypto.students;

import java.awt.Dialog.ModalExclusionType;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.Random;

import org.apache.log4j.Logger;

/***
 * In this class all the candidates must implement the methods
 * related to key derivation. You can create auxiliary functions
 * if you need it, using ONLY Java standard classes.
 * 
 * @author Pablo Serrano
 */
public class Supplementary {
	
	private static Logger log = Logger.getLogger(Supplementary.class);
	
	/***
	 * Receives a 2048 bits key and applies a word by word XOR
	 * to yield a 64 bit integer at the end.
	 * 
	 * @param key 2048 bit integer form part A1 DH Key Exchange Protocol
	 * @return A 64 bit integer
	 */
	public static BigInteger parityWordChecksum(BigInteger key) {
	    BigInteger result = new BigInteger("0");

	    BigInteger mask = BigInteger.ZERO;
	    for (int i = 0; i < 64; i++) {
	        mask = mask.setBit(i);
	    }

	    for (int i = 0; i < 2048; i += 64) {
	        result = result.xor(key.shiftRight(i).and(mask));
	    }

	    return result;
	}

	/***
	 * 
	 * @param key 2048 bit integer form part A1 DH Key Exchange Protocol
	 * @param p A random 64 bit prime integer
	 * @return A 64 bit integer for use as a key for a Stream Cipher
	 */
	public static BigInteger deriveSuppementaryKey(BigInteger key, BigInteger p) {
		BigInteger result = BigInteger.ZERO;
		//Result = g^a mod p, a = key , p = p, looking for g
		//Result must in 64 bit/8 byte will not exceed to 255
		final BigInteger max = BigInteger.valueOf(255);
		BigInteger tmp = BigInteger.ZERO;
		Random rnd = new Random();
		int bitLength = 64;
		tmp = tmp.probablePrime(bitLength, rnd);
		for (int i = 0 ; i < 255 ; i++ ){

			if ((tmp.modPow(p, key)).compareTo(max) < 0){ // g^a mod p < 255
				result = tmp.modPow(p, key);
				break;
			}
		}
		return result;
	}
}
