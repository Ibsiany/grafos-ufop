import java.io.BufferedReader;
import java.io.File;
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

        char[][] charMatrix = new char[qtdLinha][qtdCol];

        int i = 0;

        charMatrix[i] = line.toCharArray();
        i++;
        while (bufferedReader.ready()) {

            line = bufferedReader.readLine();
            charMatrix[i] = line.toCharArray();

            i++;

        }

        System.out.println();
        System.out.println("LABIRINTO:\n");
        for (int u = 0; u < charMatrix.length; u++) {
            for (int v = 0; v < charMatrix[u].length; v++) {
                System.out.printf("%c", charMatrix[u][v]);
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
                if (charMatrix[u][v] == ' ' || charMatrix[u][v] == 'S' || charMatrix[u][v] == 'E') {
                    source = u * qtdCol + v;
                    sink = u * qtdCol + v + 1;
                    if (charMatrix[u][v] == 'S') {
                        this.grafo.addEdgeUnoriented((source), (sink), 10);
                        s = source;
                    }

                    if (charMatrix[u][v] == 'E') {
                        this.grafo.addEdgeUnoriented((source), (sink), 100);
                        d = sink;
                    }

                    if (v < qtdCol - 1) {
                        this.grafo.addEdgeUnoriented((source), (sink), 1);
                    }

                    if (u != 0) {
                        this.grafo.addEdgeUnoriented((u * qtdCol + v), ((u - 1) * qtdCol + v), 1);
                    }
                }
            }
        }

        this.grafo.dijkstra(s, d);

        bufferedReader.close();
    }

    public String toString() {
        return this.grafo.toString();
    }

}
