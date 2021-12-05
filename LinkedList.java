public class LinkedList<T> implements List<T> {

    Node<T> head;
    int size;

    public class Node<T> {
        T data;
        Node<T> next;

        /**
         * Constructor
         * @param initial initial value
         */
        public Node(T initial) {
            data = initial;
            next = null;
        }
    }

    /**
     * Constructor
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Gets an element's data specified by position
     * @param pos index of the desired element
     * @return data of element
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        Node<T> prev = head;
        for (int i = 0; i < pos; i++)
            prev = prev.next;
        return prev.data;
    }

    /**
     * Adds a new element with specified data to the end of the LinkedList
     * @param item data of the new element
     * @return true if add was successful
     */
    @Override
    public boolean add(T item) {
        Node<T> newNode = new Node<T>(item);
        if (head == null)
            head = newNode;
        else {
            Node<T> prev = head;
            while (prev.next != null)
                prev = prev.next;
            prev.next = newNode;
        }
        size++;
        return true;
    }

    /**
     * Adds a new element with specified data to a specified position in the LinkedList
     * @param pos index of the new element
     * @param item data of the new element
     */
    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();
        Node<T> newNode = new Node<T>(item);
        if (pos == 0) {
            newNode.next = head;
            head = newNode;
        }
        else {
            Node<T> prev = head;
            for (int i = 0; i < pos - 1; i++)
                prev = prev.next;
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    /**
     * Removes an element specified by position
     * @param pos index of the element to be removed
     * @return data of removed element
     */
    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        T returnData;
        if (pos == 0) {
            returnData = head.data;
            head = head.next;
        }
        else {
            Node<T> prev = head;
            for (int i = 0; i < pos - 1; i++)
                prev = prev.next;
            returnData = prev.next.data;
            prev.next = prev.next.next;
        }
        size--;
        return returnData;
    }

    /**
     * @return the number of items in the LinkedList
     */
    @Override
    public int size() {
        return size;
    }
}
