import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Поиск максимального подмассива: тесты.
 */
public class MaxSubarraySearchTest {
    @Test
    public void findMaxSubIndexes() throws Exception {
        int arr[] = new int[] { 5, -1, 4, 2, -3 };

        int expIndexes[] = new int[2];
        expIndexes[0] = 0;
        expIndexes[1] = 3;

        assertArrayEquals(expIndexes, MaxSubarraySearch.findMaxSubIndexes(arr));
    }

    @Test
    public void getDelta() throws Exception {
        int arr[] = new int[] { 5, -1, 4, 2, -3 };

        int delta[] = new int[] { -6, 5, -2, -5 };

        assertArrayEquals(delta, MaxSubarraySearch.getDelta(arr));
    }
}