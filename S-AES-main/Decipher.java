public class Decipher {
    public static String decipher(String input, String Key) {
        // 获取轮密钥2
        int K = Integer.parseInt(Key, 2);
        int[] w = KeyExpansion.key_Expansion(K);
        String w4 = Integer.toBinaryString(w[4]);
        String w5 = Integer.toBinaryString(w[5]);
        while (w4.length() < 8) {// 位数不够八位补0
            w4 = "0" + w4;
        }
        while (w5.length() < 8) {
            w5 = "0" + w5;
        }
        String Key_2 = w4 + w5;

        String S0 = RoundKey.key_addition(input, Key_2);//轮密钥加
        String S1 = Round_1(S0, Key);
        String S2 = Round_2(S1, Key);
        return S2;
    }

    public static String decipher2(String input, String key){
        String key1 = key.substring(0,16);
        String key2 = key.substring(16,32);
        String middle = decipher(input,key2);
        String out = decipher(middle,key1);
        return out;
    }

    public static String decipher3(String input, String key){
        String key1 = key.substring(0,16);
        String key2 = key.substring(16,32);
        String key3 = key.substring(32,48);
        String middle1 = decipher(input,key3);
        String middle2 = Cipher.cipher(middle1,key2);
        String out = decipher(middle2,key1);
        return out;
    }

    // 第一轮加密
    public static String Round_1(String a, String Key) {
        String l = RoundKey.Line_Shift(a);// 逆行位移
        String h = RoundKey.Half_Byte_Replace_1(l);// 逆半字节代替
        // 获取轮密钥1
        int K = Integer.parseInt(Key, 2);
        int[] w = KeyExpansion.key_Expansion(K);
        String w2 = Integer.toBinaryString(w[2]);
        String w3 = Integer.toBinaryString(w[3]);
        while (w2.length() < 8) {// 位数不够八位补0
            w2 = "0" + w2;
        }
        while (w3.length() < 8) {
            w3 = "0" + w3;
        }
        String Key_1 = w2 + w3;

        String c = RoundKey.key_addition(h, Key_1);
        String out = RoundKey.Column_Confusion_1(c);// 逆列混淆
        return out;
    }

    // 第二轮加密
    public static String Round_2(String a, String Key) {
        String l = RoundKey.Line_Shift(a);// 逆行位移
        String h = RoundKey.Half_Byte_Replace_1(l);// 半字节代替
        String out = RoundKey.key_addition(h, Key);
        return out;
    }
}
