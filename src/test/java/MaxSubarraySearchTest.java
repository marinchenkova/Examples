import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Поиск максимального подмассива: тесты.
 */
public class MaxSubarraySearchTest {
    @Test
    public void findMaxSubIndexes() throws Exception {
        int arr[] = new int[5];
        arr[0] = 5;
        arr[1] = -1;
        arr[2] = 4;
        arr[3] = 2;
        arr[4] = -3;

        int expIndexes[] = new int[2];
        expIndexes[0] = 0;
        expIndexes[1] = 3;

        assertArrayEquals(expIndexes, MaxSubarraySearch.findMaxSubIndexes(arr));
    }

    @Test
    public void getDelta() throws Exception {
        int arr[] = new int[5];
        arr[0] = 5;
        arr[1] = -1;
        arr[2] = 4;
        arr[3] = 2;
        arr[4] = -3;

        int delta[] = new int[4];
        delta[0] = -6;
        delta[1] = 5;
        delta[2] = -2;
        delta[3] = -5;

        assertArrayEquals(delta, MaxSubarraySearch.getDelta(arr));
    }

}