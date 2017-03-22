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
                int test[] = new int[size];
                for(int i = 0; i < size; i++)
                    test[i] = 0;
                test[0] = 1;
                for(int i = 0; i < size; i++)
                    bfs(graph, test, i);
            } catch (NullPointerException e) {
                System.err.println("Graph is not connected");
                System.exit(0);
            }

            //Пусть нужно найти вершину графа, которая содержит маркер.
            //Случайно выберем такую вершину
            int marks[] = randMarkedVertex(size);
            System.out.print("\nMarked vertex: ");
            printArray(marks);

            //Найдем помеченную вершину с помощью поиска в ширину BFS
            System.out.println("\nBFS:");
            try {
                int h;
                for (int i = 0; i < size; i++) {
                    h = i + 1;
                    System.out.print("Starts from " + h + " : ");
                    System.out.println(bfs(graph, marks, i) + 1);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Поиск в ширину по графу.
     * @param graph граф
     * @param marks массив с выделенным элементом, номер которого соответствует помеченной вершине
     * @param start вершина начала поиска
     * @return номер помеченной вершины
     * @throws Exception если выбранная вершина начала поиска не соответствует размеру графа
     */
    public static int bfs(int graph[][], int[] marks, int start) throws Exception {
        int size = graph.length;
        int group[][] = new int[size + 1][size + 1];
        Integer res = null;
        if(start > size - 1)
            throw new Exception("Graph contains only " + size + " vertexes");

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                group[i][j] = 0;

        group[0][start] = 1;
        all: for(int g = 0; g < size; g++) {

            //Рассматриваем вершины из текущей группы
            for (int i = 0; i < size; i++) {
                if (group[g][i] == 1) {
                    //Если вершина помечена, то завершаем поиск
                    if (marks[i] == 1) {
                        res = i;
                        break all;

                    } else
                        //Помещение смежных вершин в следующую группу
                        for (int j = 0; j < size; j++)
                            if ((graph[i][j] == 1) && (j != i)) group[g + 1][j] = 1;
                }
            }
        }

        return res;
    }

    /**
     * Генерация графа.
     * @param size количество вершин
     * @return случайный граф
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
     * Помещение маркера в одну случайную вершину.
     * @param size количество вершин
     * @return массив boolean с одним помеченным элементом
     */
    private static int[] randMarkedVertex(int size){
        int marks[] = new int[size];
        int marked = (int) (Math.random() * size);

        for(int i = 0; i < size; i++)
            marks[i] = 0;
        marks[marked] = 1;

        return marks;
    }

    /**
     * Печать графа в виде матрицы смежности.
     * @param graph граф
     */
    private static void printGraph(int[][] graph){
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++)
                System.out.print(graph[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Печать массива в строку.
     * @param arr массив
     */
    private static void printArray(int[] arr){
        for(int i : arr) System.out.print(i + " ");
        System.out.println();
    }
}
