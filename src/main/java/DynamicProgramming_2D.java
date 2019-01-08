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
        int length = DpSourceData.volume.length;
        int width = DpSourceData.maxVolume + 1;
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
                    if (j >= DpSourceData.volume[i]) {
                        nxt = table[i - 1][j - DpSourceData.volume[i]] + DpSourceData.values[i];
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
                System.out.println(DpSourceData.values[i]);
                right -= DpSourceData.volume[i]; // 回退空间到之前
            }
        }
    }

    private static Integer max(Integer i, Integer j) {
        return i > j ? i : j;
    }
}
