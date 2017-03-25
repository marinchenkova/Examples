import java.util.Scanner;

/**
 * Алгоритмы поиска в ширину и в длину по графу.
 */
public class GraphSearch {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);

        //Задание размера случайного графа
        System.out.print("Enter amount of vertexes: ");
        int size = scan.nextInt();
        if(size == 0) throw new Exception("0 can't be size of graph");
        else {
            //Отображение графа в виде матрицы смежности
            int graph[][] = randGraph(size);
            System.out.println("\nGraph:");
            printGraph(graph);

            //Проверка связности графа
            try {
                int test = (int) (Math.random() * size);
                for(int i = 0; i < size; i++) {
                    if (bfs(graph, test, i) == -1) {
                        System.out.println("Graph is not connected");
                        System.exit(0);
                        break;
                    }
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }

            //Пусть нужно найти вершину графа, которая содержит маркер.
            //Случайно выберем такую вершину
            int marked = (int) (Math.random() * size);
            System.out.println("\nMarked vertex: " + (marked + 1));


            //Найдем помеченную вершину с помощью поиска в ширину BFS
            System.out.println("\nBFS:");
            try {
                int h;
                for (int i = 0; i < size; i++) {
                    h = i + 1;
                    System.out.print("Starts from " + h + " : distance = ");
                    System.out.println(bfs(graph, marked, i));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Поиск в ширину по графу.
     * @param graph граф
     * @param marked помеченная вершина
     * @param start вершина начала поиска
     * @return расстояние до помеченной вершины. -1 если путь до помеченной вершины не найден
     * @throws IllegalArgumentException если вершина начала поиска не соответствует размеру графа
     */
    public static int bfs(int graph[][], int marked, int start) throws IllegalArgumentException {
        int size = graph.length;
        int group[][] = new int[size + 1][size + 1];
        int path = 0;
        if(start > size - 1)
            throw new IllegalArgumentException("Graph contains only " + size + " vertexes");

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                group[i][j] = 0;

        group[0][start] = 1;
        for(int g = 0; g < size; g++) {
            //Рассматриваем вершины из текущей группы
            for (int i = 0; i < size; i++) {
                if (group[g][i] == 1) {
                    //Если вершина помечена, то завершаем поиск
                    if (i == marked) return path;

                    //Помещение смежных вершин в следующую группу
                    else for (int j = 0; j < size; j++)
                            if ((graph[i][j] == 1) && (j != i)) group[g + 1][j] = 1;
                }
            }

            path++;
        }

        return -1;
    }

    /**
     * Генерация графа в виде матрицы смежности.
     * @param size количество вершин
     * @return матрица смежности графа
     */
    private static int[][] randGraph(int size) {
        int graph[][] = new int[size][size];

        if(size == 1) {
            graph[0][0] = 1;
            return graph;
        }

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                graph[i][j] = (int) (Math.random() + 0.5);

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++) {
                if(i != j) graph[i][j] = graph[j][i];
                else graph[i][j] = 0;
            }
        }

        return graph;
    }

    /**
     * Печать графа в виде матрицы смежности.
     * @param graph граф
     */
    private static void printGraph(int[][] graph){
        for (int[] g : graph) {
            for (int j = 0; j < graph.length; j++)
                System.out.print(g[j] + " ");
            System.out.println();
        }
    }
}
