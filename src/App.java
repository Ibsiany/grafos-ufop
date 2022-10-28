import java.io.IOException;
import java.util.Scanner;

// matAdj -> mais rápido, mas ocupa um espaço maior O(n).
// listAdj -> ocupa menos espaço O(v + e).

// isomorfo -> contem no outro

// Passeio -> qualquer conjunto de nós.
// Trilha -> não é permitido repetir arestas.
// Circuito -> não é permitido repetir arestas, começa e termina no mesmo nó.
// Caminho -> Não pode repetir arestas ou nó.
// Ciclo -> Não pode repetir arestas ou nó, começa e termina no mesmo nó.

// Apresente uma trilha que não é caminho.

// aresta -> ---------
// nó -> (5)

// Apresente um circuito que não é um ciclo.

// Conexidade

// Não orientado
// Conexo -> consigo encontrar caminho para todos os nós.
// Desconexo -> Tem pelo menos um nó que não consigo encontrar caminho para todos os nós.
// aresta ponto | nó de ligação -> remoção aumenta numeros de componentes conexos(conjunto).

// Orientado
// f-conexo -> Existe caminho para todos pares de nós em ambos sentidos
// sf-conexo -> é conexo ao desconsiderar a orientação de arestas.
// desconexo -> é desconexo ao desconsiderar a orientação das arestas

//conectividade de vertices -> minimo de nós q devo remover pra ser um grafo desconexo. k(G) =
//conectividade de arestas -> minimo de arestas q devo remover pra ser um grafo desconexo.λ(G) =

class App {
    public static void main(String[] args) throws IOException {
        String arquivos[] = new String[6];
        arquivos[0] = "files/cm/toy.txt";
        arquivos[1] = "files/cm/rg300_4730.txt";
        arquivos[2] = "files/cm/rome99c.txt";
        arquivos[3] = "files/cm/facebook_combined.txt";
        arquivos[4] = "files/cm/USA-road-dt.DC.txt";
        arquivos[5] = "files/cm/USA-road-dt.NY.txt";

        Scanner sc = new Scanner(System.in);

        int option = 0;

        while (option != 3) {
            System.out.println();
            System.out.println("Informe a tarefa:");
            System.out.println("1- Caminho Minimo");
            System.out.println("2- Labirinto");
            System.out.println("3- Sair");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Escolha o arquivo para testar:");
                    System.out.println("0 - toy.txt");
                    System.out.println("1 - rg300_4730.txt");
                    System.out.println("2 - rome99c.txt");
                    System.out.println("3 - facebook_combined.txt");
                    System.out.println("4 - USA-road-dt.DC.txt");
                    System.out.println("5 - USA-road-dt.NY.txt");
                    int arqOption = sc.nextInt();

                    System.out.println("Insira a origem:");
                    int s = sc.nextInt();
                    System.out.println("Insira o destino:");
                    int d = sc.nextInt();

                    GraphList g1 = new GraphList(arquivos[arqOption]);
                    GraphMatrix g2 = new GraphMatrix(arquivos[arqOption]);

                    System.out.println();
                    System.out.println("[Dijkstra]");
                    System.out.println();
                    long startTime = System.currentTimeMillis();
                    g1.dijkstra(s, d);
                    float totalTime = System.currentTimeMillis() - startTime;
                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");

                    System.out.println();
                    System.out.println("[Bellman Ford]");
                    System.out.println();
                    startTime = System.currentTimeMillis();
                    g1.bellmanford(s, d);
                    totalTime = System.currentTimeMillis() - startTime;
                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");

                    System.out.println();
                    System.out.println("[Bellman Ford Melhorado]");
                    System.out.println();
                    startTime = System.currentTimeMillis();
                    g1.bellmanford_melhorado(s, d);
                    totalTime = System.currentTimeMillis() - startTime;
                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");

                    System.out.println();
                    System.out.println("[Floyd Warshall]");
                    System.out.println();
                    startTime = System.currentTimeMillis();
                    g2.floydWarshall(s, d);
                    totalTime = System.currentTimeMillis() - startTime;
                    System.out.println("O tempo total foi de " + totalTime / 1000 + " segundos.");
                    break;

                case 2:
                    System.out.println("Informe o nome do arquivo:(Exemplo: files/maze/toy.txt)");
                    String filename = sc.next();
                    Labirinto labirinto = new Labirinto(filename);
                    break;
                case 3:
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }

        }

        sc.close();
    }
}