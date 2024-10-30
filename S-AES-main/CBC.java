public class CBC {
    // 初始化向量
    static String IV = "1010101010101010"; // 确保为16位

    public static String CBCcipher(String Begin, String Key) {
        int length = Begin.length();
        StringBuilder out = new StringBuilder();
        String currentIV = IV; // 使用当前IV

        for (int i = 0; i < length; i += 16) {
            // 确保每次处理16位块
            String block = Begin.substring(i, Math.min(i + 16, length));
            String binaryWithIV = RoundKey.key_addition(currentIV, block);
            String binaryEncrypted = Cipher.cipher(binaryWithIV, Key);
            out.append(binaryEncrypted);
            currentIV = binaryEncrypted; // 更新IV
        }
        return out.toString();
    }

    public static String CBCdecipher(String Begin, String Key) {
        int length = Begin.length();
        StringBuilder out = new StringBuilder();
        String currentIV = IV; // 使用当前IV

        for (int i = 0; i < length; i += 16) {
            // 确保每次处理16位块
            String block = Begin.substring(i, Math.min(i + 16, length));
            String binaryDecrypted = Decipher.decipher(block, Key);
            String binaryWithIV = RoundKey.key_addition(currentIV, binaryDecrypted);
            out.append(binaryWithIV);
            currentIV = block; // 更新IV为当前加密结果
        }
        return out.toString();
    }
}