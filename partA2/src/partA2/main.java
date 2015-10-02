package partA2;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.Random;
import java.util.Scanner;

import org.omg.CORBA.INTERNAL;

/**
 *
 * @author FanX
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	BigInteger key;
    	BigInteger share = null;
    	Scanner scanner;
    	Scanner scan=new Scanner(System.in); 
    	share = scan.nextBigInteger();
    	key = scan.nextBigInteger();
        //System.out.println(parityWordChecksum(key));
    	System.out.println(deriveSuppementaryKey(share,key));
    }
    
    
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
    
    public static BigInteger deriveSupplementaryKey(BigInteger key, BigInteger pn){
    	BigInteger result = null;
    	result = key.mod(pn);
    	
    	return result;
    }
    
	private static byte msb2(BigInteger value, int n) {
		BigInteger mask = BigInteger.ONE.setBit(n);
		final BigInteger tmp = BigInteger.valueOf(255);
		
		if (value.compareTo(tmp) < 0 ){
			return (byte)value.intValue();
		}
		else {
			for(int bitIndex = n; bitIndex >= 0; bitIndex--){
				if((value.and(mask)) != BigInteger.ZERO){
				  System.out.println((byte) bitIndex);
			      return (byte) bitIndex;
			    }
				// Problem mask >>>= 1
			    mask.shiftRight(1);
			  }
		}
		return (byte)0x00;
	}
	
	private static byte msb(BigInteger value, int n) {
		BigInteger mask = BigInteger.ONE.setBit(n - 1);
		//System.out.println(mask);
		final BigInteger tmp = BigInteger.valueOf(255);
		String bin = null;
		int bitLength = 0;
		bitLength = value.bitLength();	
		//bin = value.toString(2);
		if (value.compareTo(tmp) < 0 ){
			return (byte)value.intValue();
		}
		else {
			//System.out.println(bitLength);
			for (int i = 0 ; i>= 0 ; i ++ ){
				if (bitLength - i == n ){
					//bin = value.shiftRight(i).toString(2);
					//System.out.println(value.shiftRight(i).intValue());
					int int_value = value.shiftRight(i).intValue();
					// fix negative number + 256
					return (byte)int_value;
				}
			}
		}
		return (byte)0x00;
	}
	
	public static BigInteger deriveSuppementaryKey(BigInteger key, BigInteger p) {
		BigInteger result = BigInteger.ZERO;
		//Result = g^a mod p, a = key , p = p, looking for g
		//Result must in 64 bit/8 byte will not exceed to 255
		final BigInteger max = BigInteger.valueOf(255);
		BigInteger tmp = BigInteger.ZERO;
		Random rnd = new Random();
		int bitLength = 64;
		
		for (int i = 0 ; i < 255 ; i++ ){
			tmp = tmp.probablePrime(bitLength, rnd);
			if ((tmp.modPow(key, p)).compareTo(max) < 0){ // g^a mod p < 255
				result = tmp.modPow(key, p);
				break;
			}
		}
		return result;
		
	}
	private byte[] _crypt(byte[] msg) {
		String plain_text = null;
		String cipher_text = null;
		//E(bi) = bi ⊕ MSB[(ari + b) mod p]
		//Encryption
		
		
		
		//D(bi) = bi ⊕ MSB[(ari + b) mod p]
		//Decryption
		
		return null;
	}
}
	

