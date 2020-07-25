# Service_computing_frontier
服务计算前沿实验
主要使用了BNL+SFS算法
天际线算法可以通过优化来加快运行速度
randdataset 可以产生不同要求的数据集
在linux解压后，在src文件夹，使用./randdataset -h 查看手册
使用nohup ./randdataset -i -d 12 -n 50000 -s 1236 > file/test_50000_10_seed_1236.txt 2>&1 &
将生成的数据集输出到文件