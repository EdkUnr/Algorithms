public class IntegerListImpl implements IntegerList {
    private Integer[] elements;
    private int size;

    public IntegerListImpl(int capacity) {
        elements = new Integer[capacity];
        size = 0;
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        if (size >= elements.length) throw new IllegalStateException("List is full");
        elements[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        if (size >= elements.length) throw new IllegalStateException("List is full");
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        Integer oldItem = elements[index];
        elements[index] = item;
        return oldItem;
    }

    @Override
    public Integer remove(Integer item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new IllegalArgumentException("Item not found");
    }

    @Override
    public Integer remove(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        Integer item = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // prevent memory leak
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        if (item == null) return -1;
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (item == null) return -1;
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        return elements[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) throw new IllegalArgumentException("Cannot compare to null");
        if (this.size() != otherList.size()) return false;
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    protected int binarySearch(Integer item) {
        int low = 0, high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            Integer midVal = elements[mid];
            int cmp = midVal.compareTo(item);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // элемент найден
            }
        }
        return -1; // элемент не найден
    }
}

