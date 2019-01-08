/**
 * <pre>
 * <b>Description</b>
 * </pre>
 * <pre>
 * 创建时间 2019-01-08 17:18
 * 所属工程： first  </pre>
 *
 * @author sheldon yhid: 80752866
 */
public class DynamicProgramming_2D {

    public static void main(String[] args) {
        final int[] volume = {0, 2, 3, 4, 5};// 4个物品的占用
        final int[] values = {0, 3, 4, 5, 6};// 4个物品的价值
        final int maxVolume = 9; // 最大体积 可以用其他值尝试 比如8

        int length = volume.length;
        int width = maxVolume + 1;
        Integer[][] table = new Integer[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0) { // 第一行全是0
                    table[i][j] = 0;
                } else if (j == 0) {  // 第一列也是0
                    table[i][j] = 0;
                } else { // 从第二行第二列开始
                    Integer pre = table[i - 1][j];
                    int nxt = pre; // 放不下就去之前的
                    if (j >= volume[i]) {
                        nxt = table[i - 1][j - volume[i]] + values[i];
                    }
                    table[i][j] = max(pre, nxt);
                }
            }
        }

        for (Integer[] row : table) {
            for (Integer col : row) {
                System.out.print(col + "   ");
            }
            System.out.println();
        }
        System.out.println("最优解的组成---------------");

        int right = width - 1;
        for (int i = length - 1; i > 0; i--) { // 逐行回退
            if (!table[i][right].equals(table[i - 1][right])) { // 相等就继续回退，否则输出当前元素
                System.out.println(values[i]);
                right -= volume[i]; // 回退空间到之前
            }
        }
    }

    private static Integer max(Integer i, Integer j) {
        return i > j ? i : j;
    }
}
