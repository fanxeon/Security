package crypto.students;

import java.awt.Dialog.ModalExclusionType;
import java.awt.print.Printable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Base64;

import org.apache.log4j.Logger;

public class StreamCipher {
	
	private static Logger log = Logger.getLogger(StreamCipher.class);
	
	private BigInteger key;
	private BigInteger prime;
	private BigInteger p1;
	private BigInteger p2;
	private BigInteger r_i;
	
	public StreamCipher(BigInteger share, BigInteger prime, BigInteger p, BigInteger q) {
		this.key = share; // shared key from DH
		this.prime = prime; // DH prime modulus
		this.p1 = Supplementary.deriveSuppementaryKey(share, p);
		this.p2 = Supplementary.deriveSuppementaryKey(share, q);
		System.out.println(p2);
		this.r_i = BigInteger.ZERO; // shift register
	}
	
	/***
	 * Updates the shift register for XOR-ing the next byte.
	 */
	public void updateShiftRegister() {
		BigInteger r_i_1 = BigInteger.ZERO;
		if (r_i.equals(Supplementary.parityWordChecksum(key))){
			r_i_1 = Supplementary.parityWordChecksum(key);
		}
		else {
			r_i_1 = r_i_1.shiftRight(8);
			//System.out.println(false);
		}
		r_i = ( ( (p1.multiply(r_i_1)) .add(p2)).mod(prime));
		//r_i = ((prime.add(r_i.shiftRight(1))).subtract(p2)).divide(p1);
	}

	/***
	 * This function returns the shift register to its initial possition
	 */
	public void reset() {
		r_i = Supplementary.parityWordChecksum(key);
	}
	
	/***
	 * Gets N numbers of bits from the MOST SIGNIFICANT BIT (inclusive).
	 * @param value Source from bits will be extracted
	 * @param n The number of bits taken
	 * @return The n most significant bits from value
	 */
	byte msb(BigInteger value, int n) {
		final BigInteger tmp = BigInteger.valueOf(255);
		int bitLength = 0;
		bitLength = value.bitLength();	

		if (value.compareTo(tmp) < 0 ){
			return (byte)value.intValue();
		}
		else {
			for (int i = 0 ; i>= 0 ; i ++ ){
				if (bitLength - i == n ){
					int int_value = value.shiftRight(i).intValue();
					return (byte)int_value;
				}
			}
		}
		return (byte)0x00;
	}
	
	/***
	 * Takes a cipher text/plain text and decrypts/encrypts it.
	 * @param msg Either Plain Text or Cipher Text.
	 * @return If PT, then output is CT and vice-versa.
	 */
	private byte[] _crypt(byte[] msg) {
		int bitLength = 0;
		bitLength = msg.length;
		byte[] result = new byte[bitLength];
		//String s = new String(msg);
		//System.out.println(s);
		reset();


		for ( int i = 0 ; i < bitLength; i++ ) {
			result[i] = (byte) ((msg[i]^(msb(r_i, 8))));
			// Formula : E(bi) = bi ⊕ MSB[(ari + b) mod p]
			updateShiftRegister();
			//System.out.print(cipher_text[i]);
			//result[i] = msg[i];
		}
	

		return result;
	}
	
	//-------------------------------------------------------------------//
	// Auxiliary functions to perform encryption and decryption          //
	//-------------------------------------------------------------------//
	public String encrypt(String msg) {
		// input: plaintext as a string
		// output: a base64 encoded ciphertext string
		log.debug("line to encrypt: [" + msg + "]");
		String result = null;
		try {
			byte[] asArray = msg.getBytes("UTF-8");
			result = Base64.getEncoder().encodeToString(_crypt(asArray));
			log.debug("encrypted text: [" + result + "]");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String decrypt(String msg) {
		// input: a base64 encoded ciphertext string 
		// output: plaintext as a string
		log.debug("line to decrypt (base64): [" + msg + "]");
		String result = null;
		try {
			byte[] asArray = Base64.getDecoder().decode(msg.getBytes("UTF-8"));
			result = new String(_crypt(asArray), "UTF-8");
			log.debug("decrypted text; [" + result + "]");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
