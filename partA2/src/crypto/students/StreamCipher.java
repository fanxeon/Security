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
		this.r_i = BigInteger.ZERO; // shift register
	}
	
	/***
	 * Updates the shift register for XOR-ing the next byte.
	 */
	public void updateShiftRegister() {
		// Formula : ri = (ari−1 + b) mod p 
		// Thinking iteration
		// 1. r1 = r0 = 192
		// 2. r2 = (a*r1 + b) mod p = (1 * 192 + 4) mod 11 = 9
		// 3. r3 = ( 1 * 9 + 4 ) mod 11 =  2
		// 4. r4 = ( 1 * 2 + 4 ) mod 11 =  6
		// 5. r5 = ( 1 * 6 + 4 ) mod 11 = 10
		BigInteger r_i_1; // Hold r_i-1 value;
		r_i = ( ( (p1.multiply(r_i)) .add(p2)).mod(prime)); // perform ri-1 to ri
		r_i_1 = r_i.shiftLeft(r_i.intValue()); // Store ri-1 = ri for next loop
	}

	/***
	 * This function returns the shift register to its initial position
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
		final BigInteger min = BigInteger.valueOf(255); // Set a min bound, 
		int bitLength = value.bitLength();	// get value length of bits
		//if value < 2^8 -1 then return directly
		if (value.compareTo(min) < 0 ){
			return (byte)value.intValue();
		}
		else {
			// Implement to find MSB
			for (int i = 0 ; i>= 0 ; i ++ ){
				if (bitLength - i == n ){
					// Loop until there are n bits left
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
	private byte[] _crypt(byte[] msg) { // No matter En or decrypt but make sure shift to right place
		// Get total length of msg
		int bitLength = 0;
		bitLength = msg.length;
		// Declare result array with same length as 
		byte[] result = new byte[bitLength];
		
		// Reset the register to Checksum value at every call
		reset();

		for ( int i = 0 ; i < bitLength; i++ ) {
			// Formula : E(bi) = bi ⊕ MSB[(ari + b) mod p]
			// (ari + b) mod p = r_i
			// 8 has set to default bits exact
			result[i] = (byte) ((msg[i]^(msb(r_i, 8))));
			
			// Update register position in every literate
			updateShiftRegister();
			//System.out.println(result[i]);
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
