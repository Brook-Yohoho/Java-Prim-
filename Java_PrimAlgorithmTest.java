import java.util.*;

public class PrimAlgorithmTest {
    static final int INF = Integer.MAX_VALUE;

    // テストケース1: 単一のノード
    static void test1() {
        List<List<Edge>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        int expected = 0;
        assert prim(graph, 0) == expected;
    }

    // テストケース2: 単一の辺
    static void test2() {
        List<List<Edge>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));
        int expected = 2;
        assert prim(graph, 0) == expected;
    }

    // テストケース3: 複数の辺
    static void test3() {
        List<List<Edge>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>());
        graph.get(0).add(new Edge(1, 2));
        graph.get(1).add(new Edge(0, 2));
        graph.get(0).add(new Edge(3, 5));
        graph.get(3).add(new Edge(0, 5));
        graph.get(1).add(new Edge(2, 3));
        graph.get(2).add(new Edge(1, 3));
        graph.get(2).add(new Edge(3, 4));
        graph.get(3).add(new Edge(2, 4));
        int expected = 7;
        assert prim(graph, 0) == expected;
    }

    // Primアルゴリズムによる最小全域木の重みを求める
    static int prim(List<List<Edge>> graph, int start) {
        int N = graph.size();
        int[] dist = new int[N];
        boolean[] used = new boolean[N];
        Arrays.fill(dist, INF);
        Arrays.fill(used, false);
        dist[start] = 0;
        int res = 0;
        while (true) {
            int v = -1;
            for (int i = 0; i < N; i++) {
                if (!used[i] && (v == -1 || dist[i] < dist[v])) {
                    v = i;
                }
            }
            if (v == -1) break;
            used[v] = true;
            res += dist[v];
            for (Edge e : graph.get(v)) {
                dist[e.to] = Math.min(dist[e.to], e.cost);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        System.out.println("All test cases passed.");
    }
}

class Edge {
    int to, cost;
    Edge(int t, int c) { to = t; cost = c; }
}
