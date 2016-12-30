import java.util.Scanner;


public class AddDigital256 {
	
	public static int addDigits(int num){
		int n = 0;
		while(num!=0){
			n = n + num%10;
			num = num - num%10;
			num = num/10;
		}
		if(n/10 == 0){
			return n;
		}
		else{
			return addDigits(n);
		} 
    }
	public static void main(String args[]){
//		Scanner scan = new Scanner(System.in);
//		int read = scan.nextInt();
//		System.out.println(addDigits(read));
		int x=1<<31-3;
		System.out.println(x&-x);
	}
}
