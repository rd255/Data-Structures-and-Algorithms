import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class ArraysAndStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArraysAndStrings a = new ArraysAndStrings();
		System.out.println(a.isUniqueOne("abcd") == true);
		System.out.println(a.isUniqueOne("abacd") == false);
		System.out.println(a.isUniqueTwo("abcd") == true);
		System.out.println(a.isUniqueTwo("abacd") == false);
		System.out.println(a.checkPermutation("abcd", "bcda") == true);
		System.out.println(a.checkPermutation("abcd", "bcdd") == false);
		System.out.println(a.checkPermutation("abcd", "bcd") == false);
		System.out.println(a.checkPermutation("abcda", "bacda") == true);
		System.out.println(a.URLify("Mr John Smith    ", 13).equals("Mr%20John%20Smith"));
		System.out.println(a.palindromePermutation("Tact Coa") == true);
		System.out.println(a.palindromePermutation("Tact Co") == false);
		System.out.println(a.oneAway("pale", "ple") == true);
		System.out.println(a.oneAway("pales", "pale") == true);
		System.out.println(a.oneAway("pale", "bale") == true);
		System.out.println(a.oneAway("pale", "bake") == false);
		System.out.println(a.stringCompression("aabbbccccddddd").equals("a2b3c4d5"));
		System.out.println(a.stringCompression("abcd").equals("abcd"));
		int[][] matrix = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
		int[][] rotatedMatrix = { { 4,8,12,16}, {3,7,11,15}, {2,6,10,14}, {1,5,9,13}};
		a.rotateMatrix(matrix);
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(Arrays.deepToString(rotatedMatrix));
		int[][] zeroMatrix = {{1,2,3,4}, {5,6,7,8}, {0,10,0,12}, {13,14,15,16}};
		int[][] zeroMatrix2 = {{0,2,0,4},{0,6,0,8},{0,0,0,0},{0,14,0,16}};
		a.zeroMatrix(zeroMatrix);
		System.out.println(Arrays.deepToString(zeroMatrix));
		System.out.println(Arrays.deepToString(zeroMatrix2));
		System.out.println(a.stringRotation("erbottlewat", "waterbottle"));
	}
	public boolean isUniqueOne(String s) {
		// Time : O(n^2)
		// Space : O(1)
		int len = s.length();
		for(int i=0;i<len;i++) {
			for(int j=0;j<len;j++) {
				if(j!=i) {
					if(s.charAt(i) == s.charAt(j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	public boolean isUniqueTwo(String s) {
		//Time : O(n)
		//Space : O(n)
		HashSet<Character> hs = new HashSet<Character>();
		for(char c : s.toCharArray()) {
			if(hs.contains(c))
				return false;
			hs.add(c);
		}
		return true;
	}
	public boolean checkPermutation(String s1, String s2) {
		// Time : O(n)
		// Space : O(1)
		int len1 = s1.length();
		int len2 = s2.length();
		if(len1!=len2)
			return false;
		HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
		for(char c : s1.toCharArray()) 
			hm.put(c, hm.getOrDefault(c, 0)+1);
		for(char c: s2.toCharArray()) {
			int count = hm.getOrDefault(c,0);
			if(count == 0)
				return false;
			else if(count == 1)
				hm.remove(c);
			else
				hm.put(c, count-1);
		}
		return true;
	}
	public String URLify(String s,int actualLength) {
		int len = s.length();
		int j = len-1;
		char[] c = s.toCharArray();
		for(int i=(actualLength-1);i>=0;i--) {
			if(c[i]== ' ') {
				c[j] = '0';
				c[j-1]='2';
				c[j-2]='%';
				j-=3;
			}
			else {
				c[j] = c[i];
				j--;
			}
		}
		return new String(c);
	}
	public boolean palindromePermutation(String s) {
		HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
		for(char c : s.toCharArray()) {
			c = Character.toLowerCase(c);
			if(c!=' ') hm.put(c,hm.getOrDefault(c, 0)+1);
		}
		boolean oddExists = false;
		for(Entry<Character,Integer> e : hm.entrySet() ) {
			int count = e.getValue();
			if(count%2!=0) {
				if(!oddExists) oddExists = true;
				else return false;
			}
		}
		return true;
	}
	public boolean oneAway(String s1, String s2) {
		if(Math.abs(s1.length() - s2.length())>1)
			return false;
		else if(s1.length() >= s2.length())
			return oneAwayHelper(s1,s2);
		else
			return oneAwayHelper(s2,s1);
	}
	public boolean oneAwayHelper(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();
		boolean replaced = false;
		int index1 = 0;
		int index2 = 0;
		while(index2<s2.length()) {
			char c1 = s1.charAt(index1);
			char c2 = s2.charAt(index2);
			if(c1!=c2) {
				if(replaced)
					return false;
				if(len1==len2)
					replaced = true;
				else if(index1!=index2)
					return false;
				else 
					index2--;
			}
			index1++;
			index2++;
		}
		return true;
	}
	public String stringCompression(String s) {
		StringBuilder temp = new StringBuilder();
		int i =0;
		while(i<s.length()) {
			temp.append(s.charAt(i));
			i++;
			int count = 1;
			while(i>0 && i<s.length() && s.charAt(i-1) == s.charAt(i)) {
				i++;
				count++;
			}
			temp.append(count);
		}
		return s.length() > temp.length() ? new String(temp) : s;
	}
	public void rotateMatrix(int[][] matrix) {
		int m = matrix.length - 1;
		int n = matrix[0].length - 1;
		for(int i=0;i<(m+1)/2;i++) {
			for(int j=i;j<(n-i);j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][n-i];
				matrix[j][n-i] = matrix[m-i][n-j];
				matrix[m-i][n-j] = matrix[m-j][i];
				matrix[m-j][i] = temp;
			}
		}
		return ;
	}
	public void zeroMatrix(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		boolean rowZero = false;
		boolean colZero = false;
		for(int i=0;i<m;i++) {
			if(matrix[i][0] == 0) {
				colZero = true;
				break;
			}
		}
		for(int i=0;i<n;i++) {
			if(matrix[0][i] == 0) {
				rowZero = true;
				break;
			}
		}
		for(int i=1;i<m;i++) {
			for(int j=1;j<n;j++) {
				if(matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		for(int i=1;i<m;i++) {
			if(matrix[i][0] == 0) {
				for(int j=1;j<n;j++) {
					matrix[i][j] = 0;
				}
			}
		}
		for(int i=1;i<n;i++) {
			if(matrix[0][i] == 0) {
				for(int j=1;j<m;j++) {
					matrix[j][i] = 0;
				}
			}
		}
		if(colZero) {
			for(int i=0;i<m;i++) {
				matrix[i][0] = 0;
			}
		}
		if(rowZero) {
			for(int i=0;i<n;i++) {
				matrix[0][i] = 0;
			}
		}
	}
	public boolean stringRotation(String s1, String s2) {
		s1 = s1 + s1;
		return s1.indexOf(s2) != -1; 
	}
