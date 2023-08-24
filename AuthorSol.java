import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class AuthorSol {
	
	static ArrayList<Integer>[] adj;
	static int[] l, r;
	static long[][] dp;
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			l = new int[n];
			r = new int[n];
			adj = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				l[i] = fs.nextInt();
				r[i] = fs.nextInt();
				adj[i] = new ArrayList<>();
			}
			for (int i = 0; i < n - 1; i++) {
				int u = fs.nextInt(), v = fs.nextInt();
				--u; --v;
				adj[u].add(v);
				adj[v].add(u);
			}
			dp = new long[n][2];
			dfs(0, -1);
			System.out.println(Math.max(dp[0][0], dp[0][1]));
		}
		out.close();
	}
	
	static void dfs(int u, int parent) {
		dp[u][0] = dp[u][1] = 0;
		for (int v : adj[u]) { //this is the base case (basically, the leaf nodes)
			if (v == parent) { //to avoid visiting the parent again (since this is an undirected graph)
				continue;
			}
			dfs(v, u);
			//set value of node u to l[u]
			dp[u][0] += Math.max(Math.abs(l[u] - l[v]) + dp[v][0], Math.abs(l[u] - r[v]) + dp[v][1]);
			//set value of node u to r[u]
			dp[u][1] += Math.max(Math.abs(r[u] - l[v]) + dp[v][0], Math.abs(r[u] - r[v]) + dp[v][1]);
		}
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
