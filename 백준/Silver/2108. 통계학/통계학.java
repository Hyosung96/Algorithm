import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
    	Scanner sc = new Scanner(System.in);
    	int n = sc.nextInt();
    	int[] num = new int[n];
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	double sum = 0;
    	int sum2 = 0;
    	int max = 0;
    	int idx = 0;
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i = 0; i<n; i++) {
    		num[i] = sc.nextInt();
    		sum += num[i];
    		sum2 += num[i];
    		if(map.get(num[i]) != null) {
    			int tmp = map.get(num[i]);
    			tmp++;
    			map.replace(num[i], tmp);
    		}
    		else {
    			map.put(num[i], 1);
    		}
    	}
    	Arrays.sort(num);
    	if(sum > 0) {
    		if(sum/n - sum2/n >= 0.5) {
    			System.out.println(sum2/n +1);
    		}
    		else {
        		System.out.println(sum2/n);
        	}
    	}
    	else if (sum < 0) {
    		if(sum2/n - sum/n >= 0.5) {
    			System.out.println(sum2/n -1);
    		}
    		else {
        		System.out.println(sum2/n);
        	}
    	}
    	else {
    		System.out.println(0);
    	}
    	
    	
    	System.out.println(num[n/2]);
    	
    	for(int i = 0; i<n; i++) {
    		if(max < map.get(num[i])) {
    			max = map.get(num[i]); 
    			idx = i;
    		}
    	}
    	list.add(num[idx]);
    	
    	for(int i = 0; i<n; i++) {
    		if(max == map.get(num[i])) {
    			if(num[idx] != num[i]) {
    				list.add(num[i]);
    			}
    		}
    	}
    	int m = list.size();
    	int[] com = new int[m];
    	for(int i=0; i<m; i++) {
    		com[i] = list.get(i);
    	}
    	Arrays.sort(com);
    	if(com.length > 1) {
    		System.out.println(com[1]);
    	}
    	else {
    		System.out.println(num[idx]);
    	}
    	
    	System.out.println(num[n-1] - num[0]);
    	
    }
}