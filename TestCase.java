import java.util.*;

public class TestCase{
	
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
		for(int i=1;i<=n;i++){
			if(i==start){
				continue;
			}
			int length=searchB(graph,start,i,n+1);
			if(length!=-1)
				length*=6;
			System.out.print((length)+" ");
		}
		t--;
		System.out.println();
		
		}
	}
	
	public static int searchB(int graph[][],int start,int end,int n){
		int length=-1;
		Queue<Integer> q=new LinkedList<>();
		int distance[]=new int[n];
		boolean visited[]=new boolean[n];
		for(int i=0;i<n;i++)
			visited[i]=false;
		q.add(start);
		distance[start]=0;
		while(q.isEmpty()==false){
			int temp=q.peek();
			q.remove(temp);
			visited[temp]=false;
			length++;
			
			if(temp==end)
				return distance[temp];
			
			for(int i=1;i<n;i++){
				if(visited[i]==false && graph[temp][i]==1){
					q.add(i);
					distance[i]=distance[temp]+1;
					
				}
			}
			
		}
			
		return -1;
		
		
		
	}
	
	
}
