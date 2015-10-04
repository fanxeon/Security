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
		// Initial some BigInteger
	    BigInteger result = BigInteger.ZERO;
	    BigInteger mask = BigInteger.ZERO;
	    
	    // 1 Byte = 8 bits
	    // Set a mask for further use
	    for (int i = 0; i < 64; i++) {
	        mask = mask.setBit(i);
	    }

	    // Income key is 2048 bits as bound
	    // It shift 8 * 8 = 64 ' steps' in each round
	    for (int i = 0; i < 2048; i += 64) {
	    	//Computes the exclusive or (XOR) of all its words, including the checksum
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
		// Dervie key simple use mod operation
		result = key.mod(p);
		
		return result;
	}
}
