package leetcode.string;

public class Compress {
    // a 1 -> a
    // b 2 -> b 2
    // b 12 -> b 1 2
    // int -> char ： (char) (int - '0')
    public static int compress(char[] chars) {
        int n = chars.length;
        int write = 0, left = 0;
        for (int read = 0; read < n; read++) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[read];
                int num = read - left + 1;
                if (num > 1) {
                    int anchor = write;
                    while (num > 0) {
                        chars[write++] = (char) (num % 10 - '0');
                        num /= 10;
                    }
                    reverse(chars, anchor, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] chars = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        System.out.print("" + compress(chars));
    }
}
