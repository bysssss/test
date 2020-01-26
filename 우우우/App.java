package com.leebs.test.wwb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class App {
    public static void main( String[] args ) {
    	// Iteration, Array, TimeComplexity, Counting, PrefixSum, Sorting, Stack&Queue, Leader, MaxSliceProblem, Prime/Composite Number, 
        //int[] A = {-1, -3};
    	//int N = 1041;
    	//int[] A = {9, 3, 9, 3, 9, 7, 9};
    	//int X=10; int Y=85; int D=30;
    	//int[] A = {1};
    	//int N = 99999999; int[] A = {3,4,4,6,1,4,4};
    	//String S="CAGCCTA"; int[] P={2,5,0}; int[] Q={4,5,6};
    	//int[] A = {4,2,2,5,1,5,8};
    	//int A=6; int B=11; int K=2;
    	//int[] A = {1,5,2,1,4,0};
    	//int[] H  = {8,8,5,7,9,8,7,4,8};
    
    	//String S = "011100";
    	String S = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n"
    			+ "john.png, London, 2015-06-20 15:13:22\n"
    			+ "myFriends.png, Warsaw, 2013-09-05 14:07:13\n"
    			+ "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n"
    			+ "pisatower.jpg, Paris, 2015-07-22 23:59:59\n"
    			+ "BOB.jpg, London, 2015-08-05 00:02:03\n"
    			+ "notredame.png, Paris, 2015-09-01 12:00:00\n"
    			+ "me.jpg, Warsaw, 2013-09-06 15:40:22\n"
    			+ "a.png, Warsaw, 2016-02-13 13:33:50\n"
    			+ "b.jpg, Warsaw, 2016-01-02 15:12:22\n"
    			+ "c.jpg, Warsaw, 2016-01-02 14:34:30\n"
    			+ "d.jpg, Warsaw, 2016-01-02 15:15:01\n"
    			+ "e.png, Warsaw, 2016-01-02 09:49:09\n"
    			+ "f.png, Warsaw, 2016-01-02 10:55:32\n"
    			+ "g.jpg, Warsaw, 2016-02-29 22:13:11\n";
    	//int[] A={40,40,100,80,20}; int[] B={3,3,2,2,3}; int M=3; int X=5; int Y=200;
    	
    	Solution Solution = new Solution();    	
    	String res = Solution.solution(S);
        System.out.println( "res = " + res);

        // https://app.codility.com/c/feedback/4E5FN5-SRB/

        //Solution.stack();
        //Solution.queue();
        //Solution.iterator();
    }
}

class Solution {
	
	class Photo implements Comparable<Photo> {
		public int index;
		public String photoname;
		public String extension;
		public String city_name;
		public Date time;
		public String newName;
		
		@Override
		public String toString() {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return "[index=" + index + ", newName=" + newName + ", photoname=" + photoname + ", extension=" + extension + ", city_name=" + city_name + ", time="+ df.format(time) + "]";
		}

		public int compareTo(Photo photo) {
			return this.time.compareTo(photo.time);
		}
	}
	
	public String solution(String S) {
		// jpg png jpeg
		// photo.jpg, Warsaw, 2013-09-05 14:08:15
		// <<photoname>>.<<extension>>, <<city_name>>, yyyy-mm-dd hh:mm:ss
		//
		// 드물게, 다른장소라도 time 및 date 가 같을수있다.
		// 그룹 : city + time순번호 + 확장자
		//System.out.println( "S = " + S);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Photo> list = new ArrayList<Photo>();
		
		String[] lines = S.split("\n");
		int index = 0;
		for(String line : lines) {
			String[] elements = line.split(", ");
			
			Photo photo = new Photo();
			photo.index = index++;
			photo.photoname = elements[0].split("\\.")[0];
			photo.extension = elements[0].split("\\.")[1];
			photo.city_name = elements[1];
			try {
				photo.time = sdf.parse(elements[2]);
			} catch (ParseException e) {
				photo.time = null;
			}
			list.add(photo);
		}
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		Collections.sort(list);
		for(Photo photo : list) {
			
			if( map.containsKey(photo.city_name)) {
				map.put(photo.city_name, map.get(photo.city_name)+1);
			} else {
				map.put(photo.city_name, 1);
			}
			photo.newName = photo.city_name + map.get(photo.city_name) + "." + photo.extension;
			//System.out.println( "photo = " + photo);
		}
		
		StringBuffer sb = new StringBuffer();  
		for(int i=0; i<list.size(); ++i) {
			for(Photo photo : list) {
				if( i == photo.index) {
					String convertNewName = convert(photo.newName, map.get(photo.city_name));
					sb.append(convertNewName).append("\n");
				}
			}
		}
		
		return sb.toString();
    }
	
	private String convert(String newName, int topSeq) {
		String seq = newName.replaceAll("[^0-9]+", "");
		int seqLength = String.valueOf(seq).length();
		int topSeqLength = String.valueOf(topSeq).length();
		int diff = topSeqLength - seqLength;
		
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<diff; ++i) {
			sb.append("0");
		}
		
		String convertNewName = newName.replaceAll("[0-9]+", sb.toString()+seq);
		return convertNewName;
	}
	
	
	
	
	
	
	
	
	
	
	public int solution000(int[] A, int[] B, int M, int X, int Y) {
		Set<Integer> set = new HashSet<Integer>();
		int stop = 0;
		int count = 0;
		int weight = 0;
		for(int i=0; i<A.length; ++i) {
			if( (count + 1) <= X && (weight + A[i]) <= Y) {
				set.add(B[i]);
				System.out.println( "B[i] = " + B[i]);
				count++;
				weight += A[i];
			} else {
				System.out.println( "set = " + set);
				stop += (set.size() + 1);
				set.clear();
				set.add(B[i]);
				System.out.println( "B[i] = " + B[i]);
				count=1;
				weight = A[i];
			}
		}
		
		if( !set.isEmpty()) {
			System.out.println( "set = " + set);
			stop += (set.size() + 1);
			set.clear();
		}
		
		return stop;
    }
	
	public int solution0(String S) {
		int step = 0;
		int val = Integer.parseInt(S, 2);
		
		while( val != 0) {
			System.out.println( "val = " + val);
			step++;
			if( val % 2 == 1) {
				val = (val-1);
			} else {
				val = (val/2);
			}
		}
		
        
		return step;
    }
	
	public int solution7(int[] H) {
		int block = 0;
		for(int i=H.length-1; i>=0; --i) {
			int src = H[i];

			boolean isBlock = true;
			for(int j=i-1; j>=0; --j) {
                int tar = H[j];

                if(src>tar){
                	isBlock = true;
                	break;
                }
                if(src==tar) {
                	isBlock = false;
                	break;
                }
            }
            if(isBlock) {
            	block++;
            }
        }
		
		return block;
    }
	
	public int solution6(int[] A) {
		int intersect = 0;
		for(int i=0; i<A.length; ++i) {
			int srcFrom = i - A[i];
			int srcTo = i + A[i];

			for(int j=i+1; j<A.length; ++j) {
				int tarFrom = j - A[j];
				int tarTo = j + A[j];

				if(srcFrom <= tarFrom && tarFrom <= srcTo) {
					intersect++;
                } else if(srcFrom <= tarTo && tarTo <= srcTo) {
                	intersect++;
                } else if(tarFrom <= srcFrom && srcFrom <= tarTo) {
                	intersect++;
                } else if(tarFrom <= srcTo && srcTo <= tarTo) {
                	intersect++;
                }
				
	            if( intersect > 10000000) {
	            	return -1;
	            }
            }
        }

        return intersect;
    }
	
	public int solution555(int A, int B, int K) {
        int res = 0;
        for(int i=A; i<=B; ++i) {
            if( i%K == 0) {
                res++;
            }
        }

        return res;
    }
	
	public int solution55(int[] A) {
		int res = -1;
		double minAvg = Double.MAX_VALUE;
        for(int i=0; i<A.length; ++i) {
        	int count = 1;
        	double sum = A[i];

            for(int j=i+1; j<A.length; ++j) {
            	count++;
            	sum += A[j];

                double avg = sum / count;
                System.out.println( "avg = " + avg);
                if( avg < minAvg) {
                	res = i;
                	minAvg = avg;
                }
            }
        }

        return res;
    }
	
	public void iterator( ){
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("22");
		list.add("33");

		Iterator<String> iterator = list.iterator();
		while( iterator.hasNext()) {
			System.out.println("iterator : " + iterator.next());
		}
	}
	
	public void queue( ){
		Queue<String> queue = new ArrayDeque<String>();
		//Queue<String> queue = new LinkedList<String>();
		queue.add("111");
		queue.add("222");
		queue.add("333");
		  
		while(!queue.isEmpty())  {
			System.out.println("queue : " + queue.poll());
		}
	}
	
	public void stack( ){
		Stack<String> stack = new Stack<String>();
		stack.push("111");
		stack.push("222");
		stack.push("333");
		  
		while(!stack.isEmpty())  {
			System.out.println("stack : " + stack.pop());
		}
	}
	
	private int factor(String s){
        /*switch(c) {
        case 'A':
        	return 1;
        case 'C':
        	return 2;
        case 'G':
        	return 3;
        case 'T':
        	return 4;
        }*/
		if( s.contains("A")) {
			return 1;
		}
		if( s.contains("C")) {
			return 2;
		}
		if( s.contains("G")) {
			return 3;
		}
        return 4;
    }
    public int[] solution5(String S, int[] P, int[] Q) {
    	int[] res = new int[P.length];
        for(int i=0; i<P.length; ++i) {

        	String s = S.substring(P[i], Q[i]+1);
        	res[i] = factor(s);
        }

        return res;
    }
	
	public int[] solution44(int N, int[] A) {
		int max = 0;
		int[] counters = new int[N];		
		
		for( int a : A) {
			if( a <= N) {
				counters[a-1]++;
				if( counters[a-1] > max) {
					max = counters[a-1]; 
				}
			}
			if( a > N) {
				Arrays.fill(counters, max);
			}
			
			/*for( int counter : counters) {
				System.out.println( "counter = " + counter);
			}
			System.out.println( "~~~~~~~~~~~~~~~~~~~~");*/
		}
		
		
		return counters;
    }
	 
	public int solution4(int[] A) {
		Arrays.sort(A);
		
		int b = 0;
		for(int a : A) {
			if( a-b != 1) {
				return 0;
			}
			b=a;
		}
		
		return 1;
    }
	
	public int solution3(int X, int Y, int D) {
		int diff = Y - X;
		
		int nums = diff / D;
		if( diff % D != 0 ) {
			nums++;
		}
		
		return nums;
    }
	
	public int solution2(int[] A) {
		Arrays.sort(A);
		
		int count = 0;
		int b = A[0];
		for(int a : A) {
			
			if( b==a) {
				count++;
			} else {
				if( count%2==1) {
					break;
				}
				count = 1;
			}
			b = a;
		}
        
		return b;
    }
	
	public int solution1(int N) {
		String str = Integer.toBinaryString(N);
		
		int longest = 0;
		int count = 0;
		for(int i=0; i<str.length(); ++i) {
			char c = str.charAt(i);
			
			if( c=='1') {
				if(count > longest) {
					longest = count;
				}
				count = 0;
			} else {
				count++;
			}
			
		}
		
		return longest;
    }
	
    public int solution0(int[] A) {
    	Arrays.sort(A);
    	
    	int b = 0;
    	for(int a : A) {
    		if( a<1) {
    			continue;
    		}
    		if( a-b > 1) {
    			break;
    		}
    		
    		b=a;
    	}
    	
        return b + 1;
    }
}
