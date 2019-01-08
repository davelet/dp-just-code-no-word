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
public class DynamicProgramming_OptSpace {

    public static void main(String[] args) {
        int length = DpSourceData.volume.length;
        int width = DpSourceData.maxVolume + 1;
        int[] table = new int[width];
        for (int i = 0; i < length; i++) {// 一次一次的刷
            for (int j = width-1; j >= 0; j--) {
                if (j >= DpSourceData.volume[i]){  // 每一次看能替换就替换
                    table[j] = max(table[j], table[j - DpSourceData.volume[i]] + DpSourceData.values[i]);
                }
            }
        }

        for (int col : table) {
            System.out.print(col + "   ");
        }
    }

    private static Integer max(Integer i, Integer j) {
        return i > j ? i : j;
    }
}
