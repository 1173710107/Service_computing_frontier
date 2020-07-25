package lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author RYP
 *
 */
public class Main2 {
	public static void main(String[] args) {
		
		start();
			
	}

	public static List<BigDecimal> dataanalysis(String temp) {
		List<BigDecimal> list = new ArrayList<BigDecimal>();
		String dataString[] = temp.split(",");
		if (dataString.length == 10) {
			for (int i = 0; i < 10; i++) {
				BigDecimal bigDecimal = new BigDecimal(dataString[i].toString());
				list.add(bigDecimal);
			}
		} else {
			System.out.println("read data error!!!");
			System.exit(0);
		}
		return list;
	}

	public static void start() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		List<Integer> indexlist = new ArrayList<Integer>();
		int indexcount = 0;
		List<List<BigDecimal>> allList = new ArrayList<List<BigDecimal>>();
		// StringBuffer stringBuffer = new StringBuffer();
		String fileSrc = "./txt/test1_10000_10_seed_1236.txt";
		try {
			FileReader fileReader = new FileReader(fileSrc);
			BufferedReader br = new BufferedReader(fileReader);
			String temp = null;
			while ((temp = br.readLine()) != null) {
				List<BigDecimal> list = dataanalysis(temp);
				if (allList.size() == 0) {
					allList.add(list);
					indexlist.add(indexcount);
				} else {
					List<Integer> indexIntegers = new ArrayList<Integer>();
					boolean flagpoint = false;// false表示新点不被内存点支配
					int allcount = 0;// 表示新点和内存中的点相互不支配的数目
					for (int i = 0; i < allList.size(); i++) {
						int count = 10;
						int count1 = 0;
						for (int j = 0; j < 10; j++) {
							int flag = list.get(j).compareTo(allList.get(i).get(j));// flag 1 前面大 -1 后面大 0 相等
							if (flag == -1)// 新点有一个比旧点小
							{
								count--;// 大于等于属性个数
							} else if (flag == 0) {
								count1++;// 等于个数
							}
						}
						if (count == 10)// 新进来点支配内存中的点，把内存中的点索引记录下来删除掉
						{
							indexIntegers.add(i);
						} else if (count == 0 || (10 - count + count1) == 10)// 新进来的点被内存里面支配，新进来点扔掉,读文件下一个点
						{
							flagpoint = true;
							break;
						} else {// 相互不支配，直到新进来的点和所有内存中的点都是相互不支配的，那么就新点加入
							allcount++;
						}

					}
					if (flagpoint == true)// 读下一个文件结点,不做操作
					{
					} else if (indexIntegers.size() != 0) {// 支配内存中的点，删除内存点，加新点
						for (int k = indexIntegers.size() - 1; k >= 0; k--) {
							int num = indexIntegers.get(k);
							allList.remove(num);
							indexlist.remove(num);
						}
						allList.add(list);
						indexlist.add(indexcount);
					} else if (allcount == allList.size()) {
						allList.add(list);
						indexlist.add(indexcount);
					}
				}
				indexcount++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); // 输出程序运行时间
		System.out.println("BNL算法计算 " + fileSrc);
		System.out.println("总共输出元组个数：" + allList.size());
		System.out.println("总共输出索引个数：" + indexlist.size());
		System.out.println("具体服务号为：");

		for (int p = 0; p < indexlist.size(); p++) {
			if (p % 100 == 0) {
				System.out.println(" ");
			}
			System.out.print(indexlist.get(p) + " ");
		}

	}
}
