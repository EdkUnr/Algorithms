public class StringListImpl implements StringList {
    private String[] elements;
    private int size;

    public StringListImpl(int capacity) {
        elements = new String[capacity];
        size = 0;
    }

    @Override
    public String add(String item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        if (size >= elements.length) throw new IllegalStateException("List is full");
        elements[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        if (index > size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        if (size >= elements.length) throw new IllegalStateException("List is full");
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        String oldItem = elements[index];
        elements[index] = item;
        return oldItem;
    }

    @Override
    public String remove(String item) {
        if (item == null) throw new IllegalArgumentException("Null values are not allowed");
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new IllegalArgumentException("Item not found");
    }

    @Override
    public String remove(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        String item = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // prevent memory leak
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        if (item == null) return -1;
        for (int i = 0; i < size; i++) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) return -1;
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("Index out of bounds");
        return elements[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        String[] array = new String[size];
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }
}
