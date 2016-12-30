/**����*/
public class Partition {

    /**50. Pow(x, n)
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
    	if (n >= 0) {
    		return Power(x, n);
    	} else{
    		return 1/Power(x, -n);
    	} 
    }
    public double Power(double x, int n) {
    	if (n==0)
    		return 1;
    	double p = myPow(x, n/2);
		if (n%2 == 0) 
			return p*p;
		else 
			return p*p*x;
    }
	//���ո�ȥ��Ϊʲôһֱ��AC�������
    
    public double power(double x, int n) {
        if (n == 0)
            return 1;
 
        double v = power(x, n / 2);
 
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
 
    public double myPow1(double x, int n) {
        if (n < 0) {
            return 1 / power(x, -n);
        } else {
            return power(x, n);
        }
    }
	//ת���������������������wc
	
	
    /**69. Sqrt(x)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int low = 0;
        int high = x;
        while (low <= high) {
        	double mid = (low + high)/2;
        	if (mid*mid == x) 
        		return (int)mid;
        	else if (mid*mid < x)
        		low = (int)mid+1;
        	else 
        		high = (int)mid-1;
        }
        return high;
    }
	//ľ��ֱ�ӵ���Math.sqrt()�찡
	
	
	
	
	
}
