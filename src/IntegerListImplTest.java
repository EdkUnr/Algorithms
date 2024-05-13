import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private IntegerListImpl list;

    @BeforeEach
    void setUp() {
        list = new IntegerListImpl(10); // начальная вместимость списка 10 элементов
    }

    @Test
    void testAddAndGet() {
        assertTrue(list.isEmpty());
        list.add(5);
        assertEquals(1, list.size());
        assertEquals(Integer.valueOf(5), list.get(0));
    }

    @Test
    void testAddAtPosition() {
        list.add(5);
        list.add(0, 3);
        assertEquals(Integer.valueOf(3), list.get(0));
        assertEquals(Integer.valueOf(5), list.get(1));
    }

    @Test
    void testSet() {
        list.add(5);
        list.set(0, 7);
        assertEquals(Integer.valueOf(7), list.get(0));
    }

    @Test
    void testRemoveByIndex() {
        list.add(5);
        list.add(7);
        assertEquals(Integer.valueOf(7), list.remove(1));
        assertEquals(1, list.size());
    }

    @Test
    void testContains() {
        list.add(5);
        list.add(7);
        list.add(9);
        assertTrue(list.contains(7));
        assertFalse(list.contains(8));
    }

    @Test
    void testSort() {
        list.add(9);
        list.add(7);
        list.add(5);
        list.contains(9); // Вызываем contains для триггера сортировки
        assertEquals(Integer.valueOf(5), list.get(0));
        assertEquals(Integer.valueOf(7), list.get(1));
        assertEquals(Integer.valueOf(9), list.get(2));
    }

    @Test
    void testClearAndIsEmpty() {
        list.add(5);
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testToArray() {
        list.add(5);
        list.add(7);
        Integer[] expectedArray = {5, 7};
        assertArrayEquals(expectedArray, list.toArray());
    }

    @Test
    void testBinarySearchOnSortedArray() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.contains(2); // Сортируем перед поиском
        assertEquals(1, list.binarySearch(2));
    }
}
