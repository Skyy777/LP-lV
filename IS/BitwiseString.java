class BitwiseString {
    public static void main(String[] args) {

        String s = "Hello World";
        int i;

        System.out.println("Bitwise AND with 127:");
        for (i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            System.out.println(ch + " = " + (ch & 127));
        }

        System.out.println("\nBitwise XOR with 127:");
        for (i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            System.out.println(ch + " = " + (ch ^ 127));
        }
    }
}