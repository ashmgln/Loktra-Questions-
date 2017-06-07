import java.util.*;
public class BetterTestCase{
	
	public static void main(String[] args) {
		int t;
		Scanner sc=new Scanner(System.in);
		t=sc.nextInt();
		while(t>0){
		int n,m;
		n=sc.nextInt();
		m=sc.nextInt();
		
		int graph[][]=new int[n+1][n+1];
		
		for(int i=0;i<=n;i++)
			for(int j=0;j<=n;j++)
				graph[i][j]=0;
		
		
		for(int i=0;i<m;i++){
		
			int x,y;
			x=sc.nextInt();
			y=sc.nextInt();
			graph[x][y]=1;
		}
		int start=sc.nextInt();
		
			
			
			int length[]=new int[n+1];
			length=searchB(graph,start,n+1);
			for(int i=1;i<=n;i++){
				if(length[i]!=999999)
					length[i]*=6;
				else
					length[i]=-1;
				if(i==start)
					continue;
				System.out.print((length[i])+" ");
			}
		
		t--;
		System.out.println();
		
		}
	}
	
	public static int[] searchB(int graph[][],int start,int n){
		int length=-1;
		Queue<Integer> q=new LinkedList<>();
		int distance[]=new int[n];
		
		boolean visited[]=new boolean[n];
		for(int i=0;i<n;i++){
			visited[i]=false;
			distance[i]=999999;
		}
		q.add(start);
		distance[start]=0;
		while(q.isEmpty()==false){
			int temp=q.peek();
			q.remove(temp);
			visited[temp]=false;
			length++;
			
			
			
			for(int i=1;i<n;i++){
				if(visited[i]==false && graph[temp][i]==1  && distance[i]>distance[temp]+1){
					q.add(i);
					distance[i]=distance[temp]+1;
					
				}
			}
			
		}
			
		return distance;
		
		
		
	}
	
	
}
