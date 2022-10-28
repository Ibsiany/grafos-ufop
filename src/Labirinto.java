import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class Labirinto {

    private GraphList grafo;
    private static final int INF = 999999;

    public Labirinto(String fileName) throws IOException {
        LineNumberReader lnr = new LineNumberReader(new FileReader(fileName));
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        lnr.skip(INF);
        int qtdLinha = lnr.getLineNumber();
        String line;
        line = bufferedReader.readLine();
        int qtdCol = line.length();
        this.grafo = new GraphList(qtdLinha * qtdCol);

        char[][] labirinto = new char[qtdLinha][qtdCol];

        labirinto[0] = line.toCharArray();

        int i = 1;

        while (bufferedReader.ready()) {
            line = bufferedReader.readLine();
            labirinto[i] = line.toCharArray();

            i++;
        }

        System.out.println();
        System.out.println("LABIRINTO:\n");
        for (int u = 0; u < labirinto.length; u++) {
            for (int v = 0; v < labirinto[u].length; v++) {
                System.out.printf("%c", labirinto[u][v]);
            }
            System.out.println();
        }
        System.out.println();

        int source;
        int sink;

        int s = 0;
        int d = 0;

        for (int u = 0; u < qtdLinha; u++) {
            for (int v = 0; v < qtdCol; v++) {

                if (labirinto[u][v] == ' ' || labirinto[u][v] == 'S' || labirinto[u][v] == 'E') {
                    source = u * qtdCol + v;
                    sink = source + 1;
                    if (labirinto[u][v] == 'S') {
                        s = source;
                    }
                    if (labirinto[u][v] == 'E') {
                        d = source;
                    }
                    if (v < qtdCol - 1
                            && (labirinto[u][v + 1] == ' ' || labirinto[u][v + 1] == 'S'
                                    || labirinto[u][v + 1] == 'E')) {
                        this.grafo.addEdgeUnoriented((source), (sink), 1);
                    }
                    if (u < qtdLinha - 1
                            && (labirinto[u + 1][v] == ' ' || labirinto[u + 1][v] == 'S'
                                    || labirinto[u + 1][v] == 'E')) {
                        this.grafo.addEdgeUnoriented((source), ((u + 1) * qtdCol + v), 1);
                    }
                }
            }
        }

        this.grafo.bellmanford(s, d);

        bufferedReader.close();
    }

    public String toString() {
        return this.grafo.toString();
    }

}
