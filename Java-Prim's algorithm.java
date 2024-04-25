import java.util.*;

public class PrimAlgorithm {
    static final int INF = Integer.MAX_VALUE;

    // 隣接リストによるグラフ表現
    static class Edge {
        int to, cost;
        Edge(int t, int c) { to = t; cost = c; }
    }
    static List<List<Edge>> graph;

    // Primアルゴリズムによる最小全域木の重みを求める
    static int prim(int start) {
        int N = graph.size();
        int[] dist = new int[N];  // startからの最短距離
        boolean[] used = new boolean[N];  // ノードが最小全域木に含まれているかどうか
        Arrays.fill(dist, INF);
        Arrays.fill(used, false);

        // ノードstartを最初に選択
        dist[start] = 0;
        int res = 0;

        while (true) {
            int v = -1;
            // 最小距離のノードを選択する
            for (int i = 0; i < N; i++) {
                if (!used[i] && (v == -1 || dist[i] < dist[v])) {
                    v = i;
                }
            }
            if (v == -1) break;
            used[v] = true;
            res += dist[v];

            // ノードvと未選択のノードとの距離を更新する
            for (Edge e : graph.get(v)) {
                dist[e.to] = Math.min(dist[e.to], e.cost);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();  // ノード数
        int E = sc.nextInt();  // エッジ数
        int start = sc.nextInt();  // 開始ノード

        graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();
            graph.get(from).add(new Edge(to, cost));
            graph.get(to).add(new Edge(from, cost));
        }

        int result = prim(start);
        System.out.println(result);
    }
}
