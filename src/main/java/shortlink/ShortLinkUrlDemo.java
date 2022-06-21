package shortlink;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShortLinkUrlDemo {

    public static void main(String[] args) {
        String sLongUrl = "http://www.baidu.com/121244/ddd";
        String key = "dwz";

        String md5Result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((key + sLongUrl).getBytes());
            byte[] digest = md.digest();
            System.out.println("digest size:"+digest.length);
            System.out.println("digest:"+digest);
            md5Result = DatatypeConverter.printHexBinary(digest);
            System.out.println("md5Result size:"+md5Result.length());
            System.out.println("md5Result:"+md5Result);
            String subString = md5Result.substring(0, 8);
            System.out.println(subString);
            System.out.println(Long.parseLong(subString, 16));
            long lhexLong = 0x3FFFFFFF & Long.parseLong(subString, 16);
            System.out.println(lhexLong);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
