package partA2;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

public class Des {

	    public static final String KEY_ALGORITHM = "DES";
	    //DES ECB working mode
	    public static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";
	    
	    public static void main(String[] args) throws Exception {
	        String source = "amigoxie";
	        System.out.println("Plain text: " + source);
	        String key = "A1B2C3D4E5F60708";
	        String encryptData = encrypt(source, key);
	        System.out.println("Cipher text: " + encryptData);
	        String decryptData = decrypt(encryptData, key);
	        System.out.println("Decrypt text: " + decryptData);
	    }
	    /**
	     *   
	     * Generate key
	     * @param KeyStr key string
	     * @return Key object 
	     * @throws InvalidKeyException   
	     * @throws NoSuchAlgorithmException   
	     * @throws InvalidKeySpecException   
	     * @throws Exception 
	     */
	    private static SecretKey keyGenerator(String keyStr) throws Exception {
	        byte input[] = HexString2Bytes(keyStr);
	        DESKeySpec desKey = new DESKeySpec(input);
	        //Creat a key factroy
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(desKey);
	        return securekey;
	    }

	    private static int parse(char c) {
	        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
	        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
	        return (c - '0') & 0x0f;
	    }

	    // Convert hex to array
	    public static byte[] HexString2Bytes(String hexstr) {
	        byte[] b = new byte[hexstr.length() / 2];
	        int j = 0;
	        for (int i = 0; i < b.length; i++) {
	            char c0 = hexstr.charAt(j++);
	            char c1 = hexstr.charAt(j++);
	            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
	        }
	        return b;
	    }

	    /** 
	     * Encrypt
	     * @param data plain text
	     * @param key Key
	     * @return Cipher text
	     */
	    public static String encrypt(String data, String key) throws Exception {
	        Key deskey = keyGenerator(key);
	        // Get cipher instance
	        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
	        SecureRandom random = new SecureRandom();
	        // Initial
	        cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
	        byte[] results = cipher.doFinal(data.getBytes());
	        // 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对
	        for (int i = 0; i < results.length; i++) {
	            System.out.print(results[i] + " ");
	        }
	        System.out.println();
	        // 执行加密操作。加密后的结果通常都会用Base64编码进行传输 
	        return Base64.getEncoder().encodeToString(results);
	    }

	    /** 
	     * 解密数据 
	     * @param data 待解密数据 
	     * @param key 密钥 
	     * @return 解密后的数据 
	     */
	    public static String decrypt(String data, String key) throws Exception {
	        Key deskey = keyGenerator(key);
	        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
	        //初始化Cipher对象，设置为解密模式
	        cipher.init(Cipher.DECRYPT_MODE, deskey);
	        // 执行解密操作
	        return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes("UTF-8"))));
	    }

	    public static void des_encypt (){
	        final byte[] IP = { //Input Permutation
	            58, 50, 42, 34, 26, 18, 10, 2,
	            60, 52, 44, 36, 28, 20, 12, 4,
	            62, 54, 46, 38, 30, 22, 14, 6,
	            64, 56, 48, 40, 32, 24, 16, 8,
	            57, 49, 41, 33, 25, 17, 9,  1,
	            59, 51, 43, 35, 27, 19, 11, 3,
	            61, 53, 45, 37, 29, 21, 13, 5,
	            63, 55, 47, 39, 31, 23, 15, 7
	        };
	        
	    }
	    

}


