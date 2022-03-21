package sat;
import java.util.*;
public class Ggraph {
    private int[][] sides;
    private int[] flag;
    private int sideN;
    private int nodeN;
    private int dfsClock;
    private int sccCount;
    private int[] color;
    private int[] dfn;
    private int[] low;
    private boolean[] vis;
    private Stack<Integer> stk;

    public Ggraph(int m, int n){
        sides = new int[n+1][m];
        flag = new int[n+1];
        color = new int[n+1];
        dfn = new int[n+1];
        low = new int[n+1];
        vis = new boolean[n+1];
        stk = new Stack<Integer>();
        dfsClock = 0;
        sideN = m;
        nodeN = n;
    }

    public Ggraph(){this(0,0);}

    public void calibrate(){
        dfsClock = 0;
    }

    public void addSide(int a,int b){//Create a side linking a to b ( a -> b )
        if(a == b)return;
        sides[a][flag[a]] = b;
        flag[a] += 1;
    }

    public void addDoubleSide(int a,int b){//Create both sides linking a to b and b to a( a <-> b )
        addSide(a,b);
        addSide(b,a);
    }

    public int getNodeN() {
        return nodeN;
    }

    @Override
    public String toString() {
        String output = "side num=" + sideN + ", node num=" + nodeN + "\n";
        for(int i = 1;i<=nodeN;i++){
            if(flag[i]>=1){
                for(int j = 0; j<flag[i]; j++ ){
                    output = output + i + " -> " + sides[i][j] + "\n";
                }
            }
        }
        return output;
    }

    public void tarjan(int curr){
        low[curr] = dfn [curr] = ++dfsClock;
        stk.push(curr);
        vis[curr]= true;
        for (int next:sides[curr]){
            if(dfn[next]<=0&&next!=curr){
                tarjan(next);
                low[curr]=Math.min(low[curr],low[next]);
            }
            else if(vis[next]){low[curr]=Math.min(low[curr],low[next]);}
        }
        if (low[curr] == dfn[curr]) {
            ++sccCount;
            do {
                color[curr] = sccCount;
                curr = stk.pop();
                vis[curr] = false;
            } while (low[curr] != dfn[curr]);
        }
    }

    public void solveSCC(){
        for (int i = 1; i <= nodeN; ++i){
            if (dfn[i]<=0){
                tarjan(i);
            }
        }
    }

    public int[] getColor() {
        return color;
    }
    /*// 注意所有东西都要开两倍空间，因为每个变量存了两次
void tarjan(int u) {
    low[u] = dfn[u] = ++dfsClock;
    stk.push(u); ins[u] = true;
    for (const auto &v : g[u]) {
        if (!dfn[v]) tarjan(v), low[u] = std::min(low[u], low[v]);
        else if (ins[v]) low[u] = std::min(low[u], dfn[v]);
    }
    if (low[u] == dfn[u]) {
        ++sccCnt;
        do {
            color[u] = sccCnt;
            u = stk.top(); stk.pop(); ins[u] = false;
        } while (low[u] != dfn[u]);
    }
}
// 笔者使用了 Tarjan 找环，得到的 color[x] 是 x 所在的 scc 的拓扑逆序。
for (int i = 1; i <= (n << 1); ++i) if (!dfn[i]) tarjan(i);*/
}
