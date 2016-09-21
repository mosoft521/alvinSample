package com.gmail.mosoft521.lottery;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Alvin on 2016/5/18 0018.
 */
public abstract class LotteryNumber {
    /**
     *
     */
    private static int DEF_DIV_SCALE;
    /**
     * 默认开始数
     */
    private static int START = 100000000;
    /**
     * 随机种子
     */
    private static long SEED = 6190297937143L;//指定种子，可重放摇号
    /**
     * 随机数
     */
    private static Random rand = new Random(SEED);

    // Between 1 and max, inclusive [1,max]
    private static int diceRoll(int max) {
        return rand.nextInt(max) + 1;
    }

    // Between 1 and max, inclusive [1,max]
    private static long diceRoll(long max) {
        return randLong(0, max) + 1;
    }

    // Between min and max: [min,max]
    private static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    //(int) Math.round(Math.random()*(max-min)+min); 
    private static long randLong(long min, long max) {
        return Math.round(rand.nextDouble() * (max - min) + min);
    }

    public static void lotNumber(final int prizeCount, final int poolCount) {
        System.out.println("SEED: " + SEED);
        if (prizeCount >= poolCount) {
            System.out.println("all prize");
            return;
        }

        boolean hasSplit = false;

        //计算中签率：且截断至poolNum位数
        DEF_DIV_SCALE = Integer.toString(poolCount).length();
        System.out.println("defDivScale: " + DEF_DIV_SCALE);
        BigDecimal ratePlan = new BigDecimal(prizeCount).divide(new BigDecimal(poolCount), DEF_DIV_SCALE, BigDecimal.ROUND_DOWN);
        System.out.println("预计中签率ratePlan: " + ratePlan);
        String tmpRatePlanString = ratePlan.toString();
        String ratePlanString = StringUtils.stripEnd(tmpRatePlanString, "0");
        System.out.println("ratePlanString: " + ratePlanString);
        String ratePlanString2 = ratePlanString.substring(2);//去掉前面0.两位字符
        System.out.println("ratePlanString2: " + ratePlanString2);
        final int ratePlanLength = ratePlanString.length();
        System.out.println("ratePlanLength: " + ratePlanLength);
        final int ratePlanLength2 = ratePlanString2.length();
        System.out.println("ratePlanLength2: " + ratePlanLength2);

        // 中签号码
        Map<Integer, List<Integer>> lotNumberActualMap = new HashMap<Integer, List<Integer>>();
        // 中签配号
        SortedSet<Integer> prizeActualSet = new TreeSet<Integer>();

        for (int i = 0; i < ratePlanLength2; i++) {
            int bit = i + 1;
            System.out.println("bit: " + bit);
            int b = Integer.valueOf(ratePlanString2.substring(i, bit));
            System.out.println("b: " + b);
            if (b != 0) {
                lotNumberActualMap.put(bit, new ArrayList<Integer>());
                hasSplit = singleLotNumber(prizeCount, poolCount, b, bit, lotNumberActualMap, prizeActualSet);
                if (hasSplit) {
                    break;
                }
            }
        }

        System.out.println("=================校验并输出中签号码列表========================");
        System.out.println("prizeActualSetSize: " + prizeActualSet.size());
        System.out.println("prizeCount: " + prizeCount);
        if (prizeActualSet.size() == prizeCount) {
            //不用补差
            System.out.println("=================不用补差========================");
            System.out.println("hasSplit: " + hasSplit);
        } else {
            if (prizeActualSet.size() < prizeCount) {
                System.out.println("=================补差前中签公告========================");
                for (Map.Entry<Integer, List<Integer>> entry : lotNumberActualMap.entrySet()) {
                    System.out.print(entry.getKey() + " : ");
                    List<Integer> tmpList = entry.getValue();
                    List<String> tmpStringList = new ArrayList<String>();
                    for (Integer ti : tmpList) {
                        tmpStringList.add(StringUtils.leftPad(ti.toString(), entry.getKey(), "0"));
                    }
                    System.out.println(Arrays.toString(tmpStringList.toArray()));
                }
                //补差
                System.out.println("=================补差（补签）同时考虑排除重号========================");
                System.out.println("hasSplit: " + hasSplit);
                do {
                    singleLotNumber(prizeCount, poolCount, prizeCount - prizeActualSet.size(), ratePlanLength2, lotNumberActualMap, prizeActualSet);
                } while (prizeCount - prizeActualSet.size() > 0);

                System.out.println("=================补签后：========================");
                System.out.println("prizeActualSetSize: " + prizeActualSet.size());
                System.out.println("prizeCount: " + prizeCount);
            } else {
                System.out.println("====================err:肯定不会到这个分支===========================");
                System.out.println("====================err:抽签大于结果？？？？===========================");
                System.out.println("hasSplit: " + hasSplit);
            }
        }


        System.out.println("=================中签公告========================");
        for (Map.Entry<Integer, List<Integer>> entry : lotNumberActualMap.entrySet()) {
            System.out.print(entry.getKey() + " : ");
            List<Integer> tmpList = entry.getValue();
            List<String> tmpStringList = new ArrayList<String>();
            for (Integer ti : tmpList) {
                tmpStringList.add(StringUtils.leftPad(ti.toString(), entry.getKey(), "0"));
            }
            System.out.println(Arrays.toString(tmpStringList.toArray()));
        }
        System.out.println("=================中签号码一览（按大小排序后）========================");
        System.out.println("size: " + prizeActualSet.size());
        System.out.println(Arrays.toString(prizeActualSet.toArray()));

    }

    /**
     * 单次摇号
     *
     * @param prizeCount         需要抽出的签数
     * @param poolCount          配号池子签数
     * @param b                  当前数字：几个
     * @param bit                当前位数：末bit位
     * @param lotNumberActualMap 既存摇号结果（无重复的）key：末bit位；value：该bit位末bit位数列表
     * @param prizeActualSet     既存中签配号
     * @return 是否有舍弃
     */
    private static boolean singleLotNumber(final int prizeCount, final int poolCount, final int b, final int bit, Map<Integer, List<Integer>> lotNumberActualMap, SortedSet<Integer> prizeActualSet) {

        //todo：seed不能超出poolCount，否则肯定是废号！（只有b=poolCount位数时有此问题）
        List<Integer> tmpLotNumberList;//摇出的本次摇号(依次匹配配号，如果某号多了，舍弃本号及以后的号)
        boolean hasSplit;//舍弃标志：即添加数字少于b

        //计算基数：用于骰子的最大数,如果是最后一位的话，基数就是总配号数
        int base = bit == DEF_DIV_SCALE ? poolCount : (int) Math.pow(10, bit);
        System.out.println("base: " + base);

        boolean repeatFlag;//重复标志


        int seed;

        if (bit == DEF_DIV_SCALE) {
            hasSplit = false;
            tmpLotNumberList = new ArrayList<Integer>(b);
            //摇tmp次:b和差额 较小的一个
            int tmp = b < prizeCount - prizeActualSet.size() ? b : prizeCount - prizeActualSet.size();
            for (int i = 0; i < tmp; i++) {
                do {
                    repeatFlag = false;
                    seed = diceRoll(base);
                    for (Map.Entry<Integer, List<Integer>> entry : lotNumberActualMap.entrySet()) {//从位数少摇到位数多的，所以新摇号的尾数不是既存的即可
                        if (repeatFlag) {
                            break;
                        }
                        int tmpBit = entry.getKey();
                        for (Integer lotNum : entry.getValue()) {
                            if (StringUtils.leftPad(Integer.toString(seed), DEF_DIV_SCALE, "0").endsWith(StringUtils.leftPad(lotNum.toString(), tmpBit, "0"))) {//重号逻辑：比较时要考虑位数，补零
                                System.out.println("tmpLotNum: " + seed + " ,bit: " + bit + " endsWith lotNum: " + lotNum + " ,bit: " + tmpBit);
                                repeatFlag = true;
                                break;
                            }
                        }
                    }
                } while (repeatFlag);
                tmpLotNumberList.add(seed);
            }
            //有可能是新加，有可能是合并
            List<Integer> tmpExistList = lotNumberActualMap.get(DEF_DIV_SCALE);
            if (null != tmpExistList) {
                tmpExistList.addAll(tmpLotNumberList);
            } else {
                lotNumberActualMap.put(bit, tmpLotNumberList);
            }
            prizeActualSet.addAll(tmpLotNumberList);
        } else {
            do {
                hasSplit = false;
                repeatFlag = false;
                tmpLotNumberList = new ArrayList<Integer>(b);
                //根据b来分解：
                switch (b) {
                    case 0:
                        //没必要抽奖，直接返回
                        return hasSplit;
                    case 1:
                        //摇一次：
                        seed = diceRoll(base);
                        System.out.println("seed: " + seed);
                        tmpLotNumberList.add(seed);
                        break;
                    case 2:
                        //摇一次：
                        seed = diceRoll(base);
                        System.out.println("seed: " + seed);
                        tmpLotNumberList.add(seed);
                        //计算出另N-1个（N=2）
                        for (int j = 0; j < b - 1; j++) {
                            tmpLotNumberList.add((seed + base / b * (j + 1)) % base);
                        }
                        break;
                    case 3:
                        //拆成1和2：即摇2次
                        if (singleLotNumber(prizeCount, poolCount, 1, bit, lotNumberActualMap, prizeActualSet)) {
                            hasSplit = true;
                        } else {
                            hasSplit = singleLotNumber(prizeCount, poolCount, 2, bit, lotNumberActualMap, prizeActualSet);
                        }
                        break;
                    case 4:
                        //摇一次：
                        seed = diceRoll(base);
                        System.out.println("seed: " + seed);
                        tmpLotNumberList.add(seed);
                        //计算出另N-1个（N=4）
                        for (int j = 0; j < b - 1; j++) {
                            tmpLotNumberList.add((seed + base / b * (j + 1)) % base);
                        }
                        break;
                    case 5:
                        //摇一次：
                        seed = diceRoll(base);
                        System.out.println("seed: " + seed);
                        tmpLotNumberList.add(seed);
                        //计算出另N-1个（N=5）
                        for (int j = 0; j < b - 1; j++) {
                            tmpLotNumberList.add((seed + base / b * (j + 1)) % base);
                        }
                        break;
                    case 6:
                        //拆成1和5：即摇2次
                        if (singleLotNumber(prizeCount, poolCount, 1, bit, lotNumberActualMap, prizeActualSet)) {
                            hasSplit = true;
                        } else {
                            hasSplit = singleLotNumber(prizeCount, poolCount, 5, bit, lotNumberActualMap, prizeActualSet);
                        }

                        break;
                    case 7:
                        //拆成2和5：即摇2次
                        if (singleLotNumber(prizeCount, poolCount, 2, bit, lotNumberActualMap, prizeActualSet)) {
                            hasSplit = true;
                        } else {
                            hasSplit = singleLotNumber(prizeCount, poolCount, 5, bit, lotNumberActualMap, prizeActualSet);
                        }
                        break;
                    case 8:
                        //摇一次：
                        seed = diceRoll(base);
                        System.out.println("seed: " + seed);
                        tmpLotNumberList.add(seed);
                        //计算出另N-1个（N=8）
                        for (int j = 0; j < b - 1; j++) {
                            tmpLotNumberList.add((seed + base / b * (j + 1)) % base);
                        }
                        break;
                    case 9:
                        //拆成8和1：即摇2次
                        if (singleLotNumber(prizeCount, poolCount, 8, bit, lotNumberActualMap, prizeActualSet)) {
                            hasSplit = true;
                        } else {
                            hasSplit = singleLotNumber(prizeCount, poolCount, 1, bit, lotNumberActualMap, prizeActualSet);
                        }
                        break;
                    default:
                        break;
                }
                //检查签号是否重号：有一个重号，即重摇
                //新摇签号位数一定大于既存摇号
                //临时摇签号个数一般少于既存摇号列表
                for (int tmpLotNum : tmpLotNumberList) {//临时摇号
                    if (repeatFlag) {
                        break;
                    }
                    for (Map.Entry<Integer, List<Integer>> entry : lotNumberActualMap.entrySet()) {//从位数少摇到位数多的，所以新摇号的尾数不是既存的即可
                        if (repeatFlag) {
                            break;
                        }
                        int tmpBit = entry.getKey();
                        for (Integer lotNum : entry.getValue()) {
                            if (StringUtils.leftPad(Integer.toString(tmpLotNum), bit, "0").contains(StringUtils.leftPad(lotNum.toString(), tmpBit, "0"))) {//重号逻辑：比较时要考虑位数，补零
                                System.out.println("tmpLotNum: " + tmpLotNum + " ,bit: " + bit + " contains lotNum: " + lotNum + " ,bit: " + tmpBit);
                                repeatFlag = true;
                                break;
                            }
                        }
                    }
                }

                if (!repeatFlag) {
                    //检查摇号匹配到配号是否超额：
                    List<Integer> tmpLotNumberList2 = new ArrayList<Integer>();
                    for (Integer tmpSeed : tmpLotNumberList) {
                        if (checkAndAdd(tmpSeed, bit, prizeCount, poolCount, prizeActualSet)) {
                            tmpLotNumberList2.add(tmpSeed);
                        } else {
                            hasSplit = true;
                            break;
                        }
                    }
                    //有可能是新加，有可能是合并
                    List<Integer> tmpExistList = lotNumberActualMap.get(bit);
                    if (null != tmpExistList) {
                        tmpExistList.addAll(tmpLotNumberList2);
                    } else {
                        lotNumberActualMap.put(bit, tmpLotNumberList2);
                    }
                }
            } while (repeatFlag && !hasSplit);
        }


        return hasSplit;
    }

    /**
     * 检查摇号匹配到配号是否超额：如果超出prizeCount则返回false；未超出添加配号到prizeActualSet并返回true
     *
     * @param seed           签号
     * @param bit            当前位数：末bit位
     * @param prizeCount     需要中签总个数
     * @param poolCount      配号总个数
     * @param prizeActualSet 中签实际配号列表
     * @return
     */
    private static boolean checkAndAdd(final int seed, final int bit, final int prizeCount, final int poolCount, SortedSet<Integer> prizeActualSet) {
        String strSeed = StringUtils.leftPad(Integer.toString(seed), bit, "0");
        SortedSet<Integer> tmpSet = new TreeSet<Integer>();
        for (int i = 1; i <= poolCount; i++) {
            if (StringUtils.leftPad(Integer.toString(i), bit, "0").endsWith(strSeed)) {//摇号匹配到配号
                tmpSet.add(START + i);
            }
        }
        if (prizeActualSet.size() + tmpSet.size() <= prizeCount) {
            prizeActualSet.addAll(tmpSet);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        lotNumber(10001, 1234567); //rate:0.0810005184  rate: 0.0081000059  rate:0.0081000
//        lotNumber(57368, 100000000); //中签率:0.00057368
//        lotNumber(10000, 12345678); //rate:
//        lotNumber(70825, 123456789); //中签率:

        lotNumber(600000, 30494074); //中国人寿中签率:0.01967595408865342164513669114858
    }
}
