package lab1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author RYP
 *
 */
public class SFS {
	public static void main(String[] args) {
		long startTime = 0; // 获取开始时间
		int indexcount = 0;
		List<Point> allList = new ArrayList<Point>();
		List<Point> sortList = new ArrayList<Point>();
		// StringBuffer stringBuffer = new StringBuffer();
		String fileSrc = "./txt/test1_10000_10_seed_1236.txt";
		try {
			FileReader fileReader = new FileReader(fileSrc);
			BufferedReader br = new BufferedReader(fileReader);
			String temp = null;
			int countnunber = 0;
			while ((temp = br.readLine()) != null) {
				List<BigDecimal> list = dataanalysis(temp);
				Point point = new Point(countnunber, list);
				countnunber++;
				sortList.add(point);
			}
			Collections.sort(sortList);// 排序
			startTime = System.currentTimeMillis();
			for (int x = 0; x < sortList.size(); x++) {
				if (allList.size() == 0) {
					allList.add(sortList.get(x));
				} else {
					List<Integer> indexIntegers = new ArrayList<Integer>();
					boolean flagpoint = false;// false表示新点不被内存点支配
					int allcount = 0;// 表示新点和内存中的点相互不支配的数目
					for (int i = 0; i < allList.size(); i++) {
						int count = 10;
						int count1 = 0;
						for (int j = 0; j < 10; j++) {
							int flag = sortList.get(x).getList().get(j).compareTo(allList.get(i).getList().get(j));// flag
																													// 1
																													// 前面大
																													// -1
																													// 后面大
																													// 0
																													// 相等
							if (flag == -1)// 新点有一个比旧点小
							{
								count--;// 大于等于属性个数
							} else if (flag == 0) {
								count1++;// 等于个数
							}
						}
						if (count == 0 || (10 - count + count1) == 10)// 新进来的点被内存里面支配，新进来点扔掉,读文件下一个点
						{
							flagpoint = true;
							break;
						} else {// 相互不支配，直到新进来的点和所有内存中的点都是相互不支配的，那么就新点加入
							allcount++;
						}

					}
					if (flagpoint == true)// 读下一个文件结点,不做操作
					{
					} else if (allcount == allList.size()) {
						allList.add(sortList.get(x));
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
		System.out.println("SFS算法计算 " + fileSrc);
		System.out.println("总共输出元组个数：" + allList.size());
		System.out.println("具体服务号为：");

		for (int p = 0; p < allList.size(); p++) {
			if (p % 100 == 0) {
				System.out.println(" ");
			}
			System.out.print(allList.get(p).getId() + " ");
		}

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
	
}
