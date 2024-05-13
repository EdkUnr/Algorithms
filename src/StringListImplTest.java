import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    private StringList list;

    @BeforeEach
    void setUp() {
        list = new StringListImpl(10); // предполагаем, что начальная вместимость списка 10 элементов
    }

    @Test
    void testAddAndGetSize() {
        list.add("apple");
        assertEquals(1, list.size());
        list.add("banana");
        assertEquals(2, list.size());
    }

    @Test
    void testAddAtPosition() {
        list.add("apple");
        list.add(0, "banana");
        assertEquals("banana", list.get(0));
        assertEquals("apple", list.get(1));
    }

    @Test
    void testSet() {
        list.add("apple");
        list.set(0, "banana");
        assertEquals("banana", list.get(0));
    }

    @Test
    void testRemoveByElement() {
        list.add("apple");
        list.add("banana");
        list.remove("apple");
        assertEquals(1, list.size());
        assertThrows(IllegalArgumentException.class, () -> list.remove("cherry"));
    }

    @Test
    void testRemoveByIndex() {
        list.add("apple");
        list.add("banana");
        list.remove(1);
        assertEquals(1, list.size());
        assertEquals("apple", list.get(0));
    }

    @Test
    void testContains() {
        list.add("apple");
        assertTrue(list.contains("apple"));
        assertFalse(list.contains("banana"));
    }

    @Test
    void testIndexOfAndLastIndexOf() {
        list.add("apple");
        list.add("banana");
        list.add("apple");
        assertEquals(0, list.indexOf("apple"));
        assertEquals(2, list.lastIndexOf("apple"));
    }

    @Test
    void testClearAndIsEmpty() {
        list.add("apple");
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void testToArray() {
        list.add("apple");
        list.add("banana");
        String[] expected = new String[]{"apple", "banana"};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    void testAddBeyondCapacity() {
        for (int i = 0; i < 10; i++) {
            list.add("item" + i);
        }
        assertThrows(IllegalStateException.class, () -> list.add("overflow"));
    }

    @Test
    void testEquality() {
        StringList otherList = new StringListImpl(10);
        list.add("apple");
        otherList.add("apple");
        assertTrue(list.equals(otherList));
        otherList.add("banana");
        assertFalse(list.equals(otherList));
    }
}
