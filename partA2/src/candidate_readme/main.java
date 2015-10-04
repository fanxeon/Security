package candidate_readme;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

import org.omg.CORBA.INTERNAL;

import crypto.students.Supplementary;

/**
 *
 * @author FanX
 */
public class main {
	static BigInteger key = BigInteger.valueOf(4);
	static BigInteger prime = BigInteger.valueOf(11);
	static BigInteger p1 = BigInteger.valueOf(3);
	static BigInteger p2 = BigInteger.valueOf(5);
	//static BigInteger r_i = BigInteger.ZERO;
	static BigInteger r_i = BigInteger.valueOf(192);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


		
    	BigInteger key2;
    	BigInteger share = null;
    	Scanner scanner;
    	Scanner scan=new Scanner(System.in); 
    	String string = "{\"created_at\":\"Thu May 14 14:37:57 +0000 2015\",\"id\":598859802400268289,\"id_str\":\"598859802400268289\",\"text\":\"Good morning! ?\",\"source\":\"<a href=\"http://twitter.com/download/android\" rel=\"nofollow\">Twitter for Android</a>\",\"truncated\":false,\"in_reply_to_status_id\":null,\"in_reply_to_status_id_str\":null,\"in_reply_to_user_id\":null,\"in_reply_to_user_id_str\":null,\"in_reply_to_screen_name\":null,\"user\":{\"id\":73727137,\"id_str\":\"73727137\",\"name\":\"Amber Bruns?\",\"screen_name\":\"ColourQueenAB\",\"location\":\"SAN ANTONIO, TEXAS\",\"url\":null,\"description\":\"COLOUR Queen B. #Hair Stylist in the Making. I LOVE COLOR! By Any Means. Anything Is Possible.\",\"protected\":false,\"verified\":false,\"followers_count\":319,\"friends_count\":442,\"listed_count\":12,\"favourites_count\":378,\"statuses_count\":5247,\"created_at\":\"Sat Sep 12 20:29:07 +0000 2009\",\"utc_offset\":-18000,\"time_zone\":\"Central Time (US & Canada)\",\"geo_enabled\":true,\"lang\":\"en\",\"contributors_enabled\":false,\"is_translator\":false,\"profile_background_color\":\"000000\",\"profile_background_image_url\":\"http://abs.twimg.com/images/themes/theme14/bg.gif\",\"profile_background_image_url_https\":\"https://abs.twimg.com/images/themes/theme14/bg.gif\",\"profile_background_tile\":false,\"profile_link_color\":\"0B4040\",\"profile_sidebar_border_color\":\"000000\",\"profile_sidebar_fill_color\":\"000000\",\"profile_text_color\":\"000000\",\"profile_use_background_image\":false,\"profile_image_url\":\"http://pbs.twimg.com/profile_images/490955924693995520/uY90KDh0_normal.jpeg\",\"profile_image_url_https\":\"https://pbs.twimg.com/profile_images/490955924693995520/uY90KDh0_normal.jpeg\",\"profile_banner_url\":\"https://pbs.twimg.com/profile_banners/73727137/1386261952\",\"default_profile\":false,\"default_profile_image\":false,\"following\":null,\"follow_request_sent\":null,\"notifications\":null},\"geo\":null,\"coordinates\":null,\"place\":{\"id\":\"3df4f427b5a60fea\",\"url\":\"https://api.twitter.com/1.1/geo/id/3df4f427b5a60fea.json\",\"place_type\":\"city\",\"name\":\"San Antonio\",\"full_name\":\"San Antonio, TX\",\"country_code\":\"US\",\"country\":\"United States\",\"bounding_box\":{\"type\":\"Polygon\",\"coordinates\":[[[-98.778559,29.141956],[-98.778559,29.693046],[-98.302744,29.693046],[-98.302744,29.141956]]]},\"attributes\":{}},\"contributors\":null,\"retweet_count\":0,\"favorite_count\":0,\"entities\":{\"hashtags\":[],\"trends\":[],\"urls\":[],\"user_mentions\":[],\"symbols\":[]},\"favorited\":false,\"retweeted\":false,\"possibly_sensitive\":false,\"filter_level\":\"low\",\"lang\":\"en\",\"timestamp_ms\":\"1431614277826\"}";
		byte[] msg = string.getBytes(Charset.forName("UTF-8"));
    	share = scan.nextBigInteger();
    	key = scan.nextBigInteger();
        //System.out.println(parityWordChecksum(key));
    	System.out.println(deriveSuppementaryKey(share,key));
    	//byte[] msg = scan.next;
    	//_crypt(msg);
		//updateShiftRegister();
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
			if ((tmp.modPow(p, key)).compareTo(max) < 0){ // g^a mod p < 255
				result = tmp.modPow(p, key);
				break;
			}
		}
		return result;
		
	}
	private static byte[] _crypt(byte[] msg) {
		String plain_text = null;
		BigInteger cipher_text[] = null;
		int bitLength = 0;
		bitLength = msg.length;
		byte[] result = null;
		
		reset();

		//Encryption
		
		
		for ( int i = 0 ; i < bitLength; i++ ) {
			result[i] = (byte) (msg[i]^(msb(r_i, 8)));
			//result[i] = (byte) (msg[i]^(msb((p1.multiply(r_i).add(p2).mod(prime)), 64)));
			updateShiftRegister();
			System.out.print(cipher_text[i]);
		}
		//D(bi) = bi ⊕ MSB[(ari + b) mod p]
		//Decryption
		System.out.print(result);
		return result;
	}
	public static void updateShiftRegister() {
		r_i = (p1.multiply(r_i).shiftRight(8)).add( p2);
		System.out.println(r_i);
	}

	/***
	 * This function returns the shift register to its initial possition
	 */
	public static void reset() {
		r_i = Supplementary.parityWordChecksum(key);
	}
}
	

