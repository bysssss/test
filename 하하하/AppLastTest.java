package com.leebs.test.hc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Deque;
import java.util.Set;
import java.util.stream.Collectors;

public class AppLastTest {
	static Deque<String> gQueue = new ArrayDeque<String>();
	
	public static void main( String[] args ) throws Exception {
    	
		System.out.println("countBranch() : " + AppLastTest.countBranch(new int[] {1, 3, 1, -1, 3}));
		/*
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d H:m");
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie(sdf.parse("2015-01-01 20:00"), sdf.parse("2015-01-01 21:30")));
		movies.add(new Movie(sdf.parse("2015-01-01 23:10"), sdf.parse("2015-01-01 23:30")));
		movies.add(new Movie(sdf.parse("2015-01-01 21:30"), sdf.parse("2015-01-01 23:00")));
		// 8시~9시반_9시반~11시_11시1분~11시반
		System.out.println("all : " + all(movies));
		Date time5 = sdf.parse("2015-01-01 17:00");
		Date tima5 = sdf.parse("2015-01-01 17:00");
		Date time6 = sdf.parse("2015-01-01 18:00");
		System.out.println("time5.before(tima5) : " + time5.before(tima5));
		System.out.println("time5.after(tima5) : " + time5.after(tima5));
		System.out.println("time5.before(time6) : " + time5.before(time6));
		System.out.println("time5.after(time6) : " + time5.after(time6));
		System.out.println("time6.before(time5) : " + time6.before(time5));
		System.out.println("time6.after(time5) : " + time6.after(time5));
		*/
    	/*
    	app.enterQueue(null);
    	app.enterQueue("AAA");
    	app.enterQueue("BBB");
    	app.enterQueue("CCC");
    	System.out.println("leaveQueue : " + app.leaveQueue());
    	System.out.println("leaveQueue : " + app.leaveQueue());
    	System.out.println("leaveQueue : " + app.leaveQueue());
    	System.out.println("leaveQueue : " + app.leaveQueue());
    	System.out.println("leaveQueue : " + app.leaveQueue());
    	Stack<Integer> stack = new Stack<Integer>();
		stack.push(3);
		stack.push(2);
		stack.push(1);
		System.out.println("stack : " + stack.pop());
		System.out.println("stack : " + stack.pop());
		System.out.println("stack : " + stack.pop());
		*/
    }
	
	public static int countBranch(int tree[]) {
		int count = 0;
		int length = tree.length;
		for(int i=0; i<length; ++i) {
			for(int j=0; j<length; ++j) {
				if( i==j) {
					continue;
				}
				if( tree[j] == i) {
					count++;
					break;
				}
			}
		}
		
		//count = explore(tree, 3);
		
		Set<Integer> set = Arrays.stream(tree).filter(x-> x>0).boxed().collect(Collectors.toSet());
		count = set.size();
		System.out.println("set : " + set);
		
		return count;
	}
	
	public static int explore(int tree[], int node) {
		int count = 0;
		int length = tree.length;
		for(int i=0; i<length; ++i) {
			if( tree[i]==node) {
				count++;
				count += explore(tree, i);
			}
		}
		
		return count;
	}
	
	public static boolean all(ArrayList<Movie> movieList) {
		for(int i=0; i<movieList.size(); ++i) {
			for(int j=i+1; j<movieList.size(); ++j) {
				Movie src = movieList.get(i);
				Movie tar = movieList.get(j);
				if( isOverlap(src, tar)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static boolean isOverlap(Movie src, Movie tar) {
		if( src.getStart().before(tar.getStart()) && src.getEnd().after(tar.getStart())) {
			return true;
        }
		if(src.getStart().before(tar.getEnd()) && src.getEnd().after(tar.getEnd())) {
        	return true;
        }
		if(tar.getStart().before(src.getStart()) && tar.getEnd().after(src.getStart())) {
			return true;
        }
		if(tar.getStart().before(src.getEnd()) && tar.getEnd().after(src.getEnd())) {
			return true;
        }
		
		return false;
	}
	
	public void enterQueue(String passportNumber) {
		if( passportNumber==null) {
			return;
		}
		gQueue.add(passportNumber);
	}
	public String leaveQueue() {
		return gQueue.poll();
		
		/*
		ArrayList - 넣기 : 38ms
		ArrayList - 빼기 : 36851ms
		
		HashMap - 넣기 : 170ms
		HashMap - 빼기 : 17ms
		
		ConcurrentLinkedQueue - 넣기 : 156ms
		ConcurrentLinkedQueue - 빼기 : 17ms
		
		LinkedList - 넣기 : 41ms
		LinkedList - 빼기 : 9ms
		
		ArrayBlockingQueue - 넣기 : 32ms
		ArrayBlockingQueue - 빼기 : 22ms
		
		ArrayDeque - 넣기 : 17ms
		ArrayDeque - 빼기 : 5ms
		
		LinkedBlockingQueue - 넣기 : 47ms
		LinkedBlockingQueue - 빼기 : 32ms
		
		LinkedBlockingDeque - 넣기 : 100ms
		LinkedBlockingDeque - 빼기 : 24ms
		
		PriorityBlockingQueue - 넣기 : 68ms
		PriorityBlockingQueue - 빼기 : 188ms
		
		SynchronousQueue - 넣기 : 7ms
		SynchronousQueue - 빼기 : 4ms
		
		PriorityQueue - 넣기 : 16ms
		PriorityQueue - 빼기 : 164m
		*/
	}
}

class Movie {
	private Date start, end;
	
	public Movie(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	public Date getStart() {
		return this.start;
	}
	public Date getEnd() {
		return this.end;
	}
}
