import java.util.ArrayList;

public class Graph {
  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public Graph(int numNodes) {
    this.countNodes = numNodes;
    this.countEdges = 0;
    this.adjMatrix = new int[numNodes][numNodes];
  }

  public void addEdge(int source, int sink, int weight) {
    if (source < 0 || source >= this.adjMatrix.length || sink < 0 || sink >= this.adjMatrix.length || weight <= 0) {
      System.err.println("Invalid edge!\nsource: " + source + "\nsink: " + sink + "\nweight: " + weight);

      return;
    }

    this.countEdges++;
    this.adjMatrix[source][sink] = weight;
  }

  public void addEdgeUnoriented(int source, int sink, int weight) {
    if (source < 0 || source >= this.adjMatrix.length || sink < 0 || sink >= this.adjMatrix.length || weight <= 0) {
      System.err.println("Invalid edge!\nsource: " + source + "\nsink: " + sink + "\nweight: " + weight);

      return;
    }

    this.countEdges += 2;
    this.adjMatrix[source][sink] = weight;
    this.adjMatrix[sink][source] = weight;
  }

  public String toString() {
    String str = "";

    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; j++) {
        str += this.adjMatrix[i][j] + "\t";
      }
      str += "\n";
    }

    return str;
  }

  public int degree(int node) {
    // Retorna o grau do nó node;

    // Retorna a quantidade de pesos dentro daquela linha, exemple:
    // 0 5 4 6 => degree = 3, pois de 4 colunas 3 estão preenchidas(estão != 0)

    int degree = 0;

    for (int j = 0; j < this.adjMatrix[node].length; j++) {
      if (this.adjMatrix[node][j] != 0) {
        degree++;
      }
    }

    return degree;
  }

  public int highestDegree() {
    int hightTest2 = 0;

    for (int i = 0; i < this.adjMatrix.length; i++) {
      if (this.degree(i) > hightTest2) {
        hightTest2 = this.degree(i);
      }
    }

    return hightTest2;

    /*
     * int values[] = new int[this.adjMatrix.length];
     * 
     * for (int i = 0; i < this.adjMatrix.length; i++) {
     * int value = 0;
     * for (int j = 0; j < this.adjMatrix[i].length; j++) {
     * if (this.adjMatrix[i][j] != 0) {
     * value++;
     * }
     * }
     * values[i] = value;
     * }
     * 
     * int hight = values[0];
     * 
     * for (int x = 0; x < values.length; x++) {
     * if (values[x] > hight) {
     * hight = values[x];
     * }
     * }
     */
  }

  public int lowestDegree() {
    int hightTest2 = this.adjMatrix.length + 1;

    for (int i = 0; i < this.adjMatrix.length; i++) {
      if (this.degree(i) < hightTest2) {
        hightTest2 = this.degree(i);
      }
    }

    return hightTest2;
  }

  public Graph complement() {
    Graph graph = new Graph(this.countNodes);

    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; j++) {
        if (this.adjMatrix[i][j] == 0 && i != j) {
          graph.addEdge(i, j, 1);
        }
      }
    }

    return graph;
  }

  public boolean subgraph(Graph g2) {
    if (g2.countEdges > this.countEdges || g2.countNodes > this.countNodes) {
      return false;
    }

    for (int i = 0; i < g2.adjMatrix.length; i++) {
      for (int j = 0; j < g2.adjMatrix[i].length; j++) {
        if (g2.adjMatrix[i][j] != 0 && this.adjMatrix[i][j] == 0) {
          return false;
        }
      }
    }

    return true;
  }

  public float density() {
    // E = quantidade de arestas que tem
    // M = maximo de arestas possíveis()
    // E / (M*(M-1))

    float e = this.countEdges;
    float m = this.countNodes;

    return e / (m * (m - 1));
  }

  public boolean oriented() {
    // retorna true se o grafo é orientado

    for (int i = 0; i < this.adjMatrix.length / 2; i++) {
      for (int j = 0; j < this.adjMatrix[i].length / 2; j++) {
        if (this.adjMatrix[i][j] != this.adjMatrix[j][i]) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean verifyFlow(int desc[], int u, int v) {
    if (this.adjMatrix[u][v] != 0 && desc[v] == 0) {
      return true;
    }

    return false;
  }

  public ArrayList<Integer> busca_largura(int origem) {
    int desc[] = new int[this.adjMatrix.length];
    ArrayList<Integer> Q = new ArrayList<Integer>();
    ArrayList<Integer> R = new ArrayList<Integer>();

    Q.add(origem);
    R.add(origem);
    desc[origem] = 1;

    while (Q.size() > 0) {
      int u = Q.get(0);
      Q.remove(0);

      for (int v = 0; v < desc.length; v++) {
        if (this.verifyFlow(desc, u, v)) {
          Q.add(v);
          R.add(v);
          desc[v] = 1;
        }
      }

    }

    return R;
  }

  public boolean connected() {
    return this.busca_largura(0).size() == this.adjMatrix.length;
  }

  public ArrayList<Integer> busca_profundidade(int s) {
    int desc[] = new int[this.adjMatrix.length];
    ArrayList<Integer> S = new ArrayList<Integer>();
    ArrayList<Integer> R = new ArrayList<Integer>();

    S.add(s);
    R.add(s);
    desc[s] = 1;

    while (S.size() > 0) {
      int u = S.get(S.size() - 1);

      boolean in = false;

      for (int v = 0; v < desc.length; v++) {
        if (this.verifyFlow(desc, u, v)) {
          S.add(v);
          R.add(v);
          desc[v] = 1;
          in = true;

          break;
        }
      }

      if (in == false) {
        S.remove(S.size() - 1);
      }

    }

    return R;
  }

}