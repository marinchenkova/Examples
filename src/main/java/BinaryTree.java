import com.sun.istack.internal.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> {

    private Node<T> root = null;
    private int size = 0;

    private static class Node<T> {
        final T value;
        Node<T> left = null;
        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }


    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) return false;

        Node<T> newNode = new Node<>(t);

        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }

        size++;
        return true;
    }

    boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    @Override
    public boolean remove(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> removing = find(t);

        if(removing != null && t.compareTo(removing.value) == 0) {
            int newSize = size - 1;

            Node<T> parent = findParent(removing, root);
            Node<T> left = removing.left;
            Node<T> right = removing.right;

            Boolean hasParent = parent != null;
            Boolean leftChild = false;
            if (hasParent && parent.left != null) leftChild = parent.left.equals(removing);

            if (left != null && right != null) {
                // Удаляемая вершина имеет две дочерние вершины
                Node<T> min = findMin(removing.right);
                remove(min.value);

                min.left = removing.left;
                min.right = removing.right;

                if(hasParent) {
                    if(leftChild) parent.left = min;
                    else parent.right = min;
                } else root = min;

            } else if (left != null) {
                // Удаляемая вершина имеет только левую дочернюю вершину
                if (hasParent) {
                    if(leftChild) parent.left = left;
                    else parent.right = left;
                } else root = left;

            } else if (right != null){
                // Удаляемая вершина имеет только правую дочернюю вершину
                if (hasParent) {
                    if(leftChild) parent.left = right;
                    else parent.right = right;
                } else root = right;

            } else {
                // Удаляемая вершина не имеет дочерних вершин
                if (hasParent) {
                    if(leftChild) parent.left = null;
                    else parent.right = null;
                } else root = null;
            }


            size = newSize;
            return true;
        }
        else return false;
    }

    /**
     * Поиск родителя вершины.
     * @param child вершина, для которой нужно найти родителя
     * @param start вершина, с которой начинается поиск
     * @return родитель вершины
     */
    private Node<T> findParent(Node<T> child, Node<T> start) {
        int comparison = child.value.compareTo(start.value);
        if (comparison == 0) return null;
        else if(comparison < 0) {
            if (start.left.equals(child)) return start;
            else return findParent(child, start.left);
        }
        else {
            if (start.right.equals(child)) return start;
            else return findParent(child, start.right);
        }
    }

    /**
     * Добавление всех вершин, начиная от данной.
     * @param extraRoot новая вершина
     */
    private void addTree(Node<T> extraRoot) {
        add(extraRoot.value);
        if(extraRoot.left != null) addTree(extraRoot.left);
        if(extraRoot.right != null) addTree(extraRoot.right);
    }


    private Node<T> findMin(Node<T> start) {
        return start.left == null ? start : findMin(start.left);
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }


    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;

        private BinaryTreeIterator() {}

        private Node<T> findNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }
    }


    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }
}
