public class ArrayList<T> implements List<T> {

    T[] arr;
    int size;

    /**
     * Constructor
     */
    public ArrayList() {
        arr = (T[]) new Object[10];
        size = 0;
    }

    /**
     * Doubles the length of the array; called by add methods when the array is full
     */
    private void growArray() {
        T[] newArr = (T[]) new Object[size * 2];
        for (int i = 0; i < size; i++)
            newArr[i] = arr[i];
        arr = newArr;
    }

    /**
     * Gets an element specified by position
     * @param pos index of the desired element
     * @return element
     */
    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        return arr[pos];
    }

    /**
     * Adds a new element to the end of the ArrayList
     * @param item new element
     * @return true if add was successful
     */
    @Override
    public boolean add(T item) {
        if (size == arr.length)
            growArray();
        arr[size++] = item;
        return true;
    }

    /**
     * Adds a new element to a specified position in the ArrayList
     * @param pos index of the new element
     * @param item new element
     */
    @Override
    public void add(int pos, T item) {
        if (pos < 0 || pos > size)
            throw new IndexOutOfBoundsException();
        if (size == arr.length)
            growArray();
        for (int i = size++; i > pos; i--)
            arr[i] = arr[i - 1];
        arr[pos] = item;
    }

    /**
     * Removes an element specified by position
     * @param pos index of the element to be removed
     * @return removed element
     */
    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size)
            throw new IndexOutOfBoundsException();
        T returnData = arr[pos];
        for (int i = pos; i < size - 1; i++)
            arr[i] = arr[i + 1];
        size--;
        return returnData;
    }

    /**
     * @return the number of items in the ArrayList
     */
    @Override
    public int size() {
        return size;
    }
}
