import org.junit.Test;

import static org.junit.Assert.*;


public class BinaryTreeTest {
    @Test
    public void remove() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(5);
        tree.add(7);
        tree.add(3);
        tree.add(1);
        tree.add(4);
        tree.add(8);
        tree.add(15);
        tree.add(20);

        // Попытка удалить несуществующую вершину
        assertFalse(tree.remove(100));

        // Удаление вершины со значением "3"
        assertTrue(tree.contains(3));
        assertTrue(tree.remove(3));

        // Удаление корня дерева
        assertTrue(tree.contains(10));
        assertTrue(tree.remove(10));

        // Проверка корректного удаления вершин
        assertFalse(tree.contains(3));
        assertFalse(tree.contains(10));

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(4));

        BinaryTree<Integer> tree1 = new BinaryTree<>();
        tree1.add(10);

        // Удаление единственной вершины дерева
        assertTrue(tree1.contains(10));
        assertTrue(tree1.remove(10));
        assertFalse(tree1.contains(10));

    }
}