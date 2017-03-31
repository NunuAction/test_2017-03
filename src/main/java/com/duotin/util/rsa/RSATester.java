package com.duotin.util.rsa;

import com.duotin.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 16/6/6.
 */
public class RSATester {

    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        Integer s1=4392;

        Integer s2=4392;

        System.out.print(s1==s2);

        test1();
    }

    static void test1() throws Exception {

        publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRPw8LXRMu+ASZrdoaBv4FKj6QWNbPXtH1PwAwWGVvhrR10Yz+/91eL6zgwxOHbxpjUQDZn2/AsHL1iTL4EFbYvlWB8vWt8ylN8MjA+S0lO443n0iqPHE5yL7j//nnklW7WvI3RxnU/I/sF+gf7R/Jt9biA9yRVMjXJ6kJfwpEbQIDAQAB";

        privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANE/DwtdEy74BJmt2hoG/gUqPpBY1s9e0fU/ADBYZW+GtHXRjP7/3V4vrODDE4dvGmNRANmfb8CwcvWJMvgQVti+VYHy9a3zKU3wyMD5LSU7jjefSKo8cTnIvuP/+eeSVbta8jdHGdT8j+wX6B/tH8m31uID3JFUyNcnqQl/CkRtAgMBAAECgYB9HbqOLeoxlIWTqruWAoZTak4cm7JeLrFWdoRTZBfLFv3C0TkApNel4NX7U0nTGqDpr97VQZtd2WPz40TBxWtXevesSQJRPZKRTdltWChhTz81WfbeKmrJ3i/k552pP9URKet9N6SCZfI2FbWuT9oXt06hdYLagQ9HaysgumJmQQJBAPkXsGMt8ZCJ0PqYswHObxz2wKL6Sh/vR8sRgXNpI1SsGjPdYMTRA0FyhdcvLZ49Rp8sx+q2rTtSPBUo7QTF9VECQQDXDIBBuqsvZpajqS8fc0sLDHnb3pXu3QX+lJKnM9UG4hlPZb7YqaKAos4a3Ch2HjfNr3yKLRVgdNhmKYNuTEZdAkANKIky1Ax8vCsD8ekS4VrRRt+qPFqKEVPTkFssQ5OQN38y2jDwdLIdVPVpBp78SvReGDl/VqMUDSYLYBwVMnfhAkEAuGNWoYUt/KGo7kFFT2Eq6hlTbyvGQR4wYp3/tO/ja1h4PTTPU7T3yfZOZId7BfRZArrCPTP0zVTCq65SrEMoXQJAVNgEMS+H4TNqeZyRRM4RUEEUWqdJoyro8v8E8WqqH3v8EH6hwDU5MH/K5uLs0N6GqilmjshNFJa6tKlO8IHLkg==";

        String url="http://localhost:8082/login?platform=111&version=2.6.4&device_key=12313&package=123123";
//        String url="http://localhost:8082/api/userinfo/password/mobile/find?carradio_mac=&device_key=864103025119410&platform=android&source=car&user_key=a47f2397e5ac0c234c1ac5e458d29f34&package=com.duotin.car&longitude=120.019295&latitude=30.282052&channel=duotin&version=3.2.1";

//        String p=Base64Utils.encode(RSAUtils.encryptByPublicKey("device_key=864103025119410&platform=android&source=car&user_key=a47f2397e5ac0c234c1ac5e458d29f34&package=com.duotin.car&longitude=120.019295&latitude=30.282052&channel=duotin&version=3.2.1&mobile=13757156549&password=123456".getBytes("UTF-8"), publicKey));
        String p=Base64Utils.encode(RSAUtils.encryptByPublicKey("useranme=123@qq.com&code=5484&new_password=111111&password=111111&mobile=15921712891".getBytes("UTF-8"), publicKey));

        String urlEncodeP=HttpUtils.encodeUrl(p);


        url+="&p="+p;

        String result= HttpUtils.getHtmlGet(url);
        System.out.println(result);




    }

    static void test2() throws Exception {

        publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDRPw8LXRMu+ASZrdoaBv4FKj6QWNbPXtH1PwAwWGVvhrR10Yz+/91eL6zgwxOHbxpjUQDZn2/AsHL1iTL4EFbYvlWB8vWt8ylN8MjA+S0lO443n0iqPHE5yL7j//nnklW7WvI3RxnU/I/sF+gf7R/Jt9biA9yRVMjXJ6kJfwpEbQIDAQAB";

        privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANE/DwtdEy74BJmt2hoG/gUqPpBY1s9e0fU/ADBYZW+GtHXRjP7/3V4vrODDE4dvGmNRANmfb8CwcvWJMvgQVti+VYHy9a3zKU3wyMD5LSU7jjefSKo8cTnIvuP/+eeSVbta8jdHGdT8j+wX6B/tH8m31uID3JFUyNcnqQl/CkRtAgMBAAECgYB9HbqOLeoxlIWTqruWAoZTak4cm7JeLrFWdoRTZBfLFv3C0TkApNel4NX7U0nTGqDpr97VQZtd2WPz40TBxWtXevesSQJRPZKRTdltWChhTz81WfbeKmrJ3i/k552pP9URKet9N6SCZfI2FbWuT9oXt06hdYLagQ9HaysgumJmQQJBAPkXsGMt8ZCJ0PqYswHObxz2wKL6Sh/vR8sRgXNpI1SsGjPdYMTRA0FyhdcvLZ49Rp8sx+q2rTtSPBUo7QTF9VECQQDXDIBBuqsvZpajqS8fc0sLDHnb3pXu3QX+lJKnM9UG4hlPZb7YqaKAos4a3Ch2HjfNr3yKLRVgdNhmKYNuTEZdAkANKIky1Ax8vCsD8ekS4VrRRt+qPFqKEVPTkFssQ5OQN38y2jDwdLIdVPVpBp78SvReGDl/VqMUDSYLYBwVMnfhAkEAuGNWoYUt/KGo7kFFT2Eq6hlTbyvGQR4wYp3/tO/ja1h4PTTPU7T3yfZOZId7BfRZArrCPTP0zVTCq65SrEMoXQJAVNgEMS+H4TNqeZyRRM4RUEEUWqdJoyro8v8E8WqqH3v8EH6hwDU5MH/K5uLs0N6GqilmjshNFJa6tKlO8IHLkg==";

        System.err.println("公钥加密——私钥解密");
        String source = "123456";
        System.out.println("\r加密前文字：\r\n" + source);
        byte[] data = source.getBytes();
        byte[] encodedData = RSAUtils.encryptByPublicKey(data, publicKey);
        System.out.println("加密后文字：\r\n" + new String(encodedData));
        String t= "aFVJSkZDeUVINjhVOXVIYS9SMW00ekptYjNUU2dMQTNqcUgvZ3lSZzdXWXhzbm5FOHlOM1dnTDB5\n" +
                "UHpRMFZpa3d3ODZONDhXS1FaMgpYT0dhWUp6aFVZK0c2dThHL3R0Q2ZSWWJBeExMcGx0Tkg4YmZu\n" +
                "OXVVNDVSckhLb3BGek90U1poaUhkSUJuRXNFaUVRSlFlMU9wYWg1CnNwbG1TUkd3YlpiSjBMNVBR\n" +
                "WmM9Cg==";
        encodedData=Base64Utils.decode(t);
        byte[] decodedData = RSAUtils.decryptByPrivateKey(encodedData, privateKey);
        String target = new String(decodedData);
        System.out.println("解密后文字: \r\n" + target);
    }

}
