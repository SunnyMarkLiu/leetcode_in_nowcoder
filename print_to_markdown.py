#!/Users/sunnymarkliu/softwares/miniconda3/bin/python
# _*_ coding: utf-8 _*_

"""
@author: SunnyMarkLiu
@time  : 2018/7/29 下午7:43
"""
import os

files = [f for f in os.listdir('./') if os.path.isdir(f) and not f.startswith('.')]

file_dicts = {}
for f in files:
    code_file = './{}/Solution.java'.format(f)
    create_time = os.path.getctime(code_file)
    file_dicts[f] = create_time

file_dicts=sorted(file_dicts.items(), key=lambda x: x[1])

# 读取最近的创建时间，对应上次最新的算法题
# 读取最近做过的算法题的数目
if os.path.exists('print_to_markdown.log'):
    with open('print_to_markdown.log') as f:
        datas = f.readline().strip().split('\t')
        latest_count = int(datas[0])
        latest_time = float(datas[1])
else:
    latest_count = 0
    latest_time = 0

cur_count = 0
cur_latest_time = 0
output = open('tmp.md', 'w', encoding='utf-8')
for f in file_dicts:
    f = f[0]
    # 获取源程序创建的时间
    code_file = './{}/Solution.java'.format(f)
    create_time = os.path.getctime(code_file)

    # 忽略之前的题目
    if create_time <= latest_time:
        continue

    if create_time > cur_latest_time:
        cur_latest_time = create_time

    # 读取源代码写入 markdown 文件
    code_file = open(code_file, encoding='utf-8')
    codes = code_file.readlines()

    output.write("## {}. {}\n".format(cur_count + latest_count + 1, f))
    output.write("```java\n")
    output.writelines(codes)
    output.write("\n")
    output.write("```\n\n")

    cur_count += 1

print("新增 {} 道算法题".format(cur_count))

if cur_count > 0:
    # 保存本轮写过的程序数和最新的时间
    cur_count = latest_count + cur_count
    with open('print_to_markdown.log', 'w') as f:
        f.write('{}\t{}'.format(cur_count, cur_latest_time))

    # 重命名文件
    os.rename('tmp.md', 'LeetCode in NowCoder part {}-{}.md'.format(latest_count, cur_count))
else:
    os.remove('tmp.md')
