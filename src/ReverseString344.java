
public class ReverseString344 {
	public static void main(String args[]) {
		System.out.println(reverseString("hello"));
	}
	public static String reverseString(String s) {
        char [] array = s.toCharArray();
        int len = array.length;
        for (int i = 0; i < len/2; i++) {
            char temp = array[i];
            array[i] = array[len - i - 1];
            array[len - i - 1] = temp;
        }
        return  String.valueOf(array);
    }
}
