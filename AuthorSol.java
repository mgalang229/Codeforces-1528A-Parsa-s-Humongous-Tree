import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class AuthorSol {
	
	static ArrayList<Integer>[] adj;
	static int[][] a;
	static long[][] dp;
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt();
			a = new int[2][n];
			adj = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				a[0][i] = fs.nextInt();
				a[1][i] = fs.nextInt();
				adj[i] = new ArrayList<>();
			}
			for (int i = 0; i < n - 1; i++) {
				int u = fs.nextInt(), v = fs.nextInt();
				--u; --v;
				adj[u].add(v);
				adj[v].add(u);
			}
			dp = new long[2][n];
			dfs(0, -1);
			System.out.println(Math.max(dp[0][0], dp[1][0]));
		}
		out.close();
	}
	
	static void dfs(int u, int p) {
		dp[0][u] = dp[1][u] = 0;
		for (int v : adj[u]) {
			if (v == p) { //to avoid visiting the parent again (since this is an undirected graph)
				continue;
			}
			dfs(v, u);
			dp[0][u] += Math.max(Math.abs(a[0][u] - a[1][v]) + dp[1][v], dp[0][v] + Math.abs(a[0][u] - a[0][v]));
			dp[1][u] += Math.max(Math.abs(a[1][u] - a[1][v]) + dp[1][v], dp[0][v] + Math.abs(a[1][u] - a[0][v]));
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
