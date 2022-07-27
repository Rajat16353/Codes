path_to_read_file = "C:/Users/gupta/Documents/LeetCode Problems.txt"
path_to_write_file = "Leet-Code/README.md"
with open(path_to_read_file, 'r') as f:
    line = f.readlines();
    # print(line)
data_to_write = line[0] + line[1] + line[2] + line[3]
data_to_write = data_to_write + "| Sr. No | Name of the problem | Sr. No | Name of the problem |\n"
data_to_write = data_to_write + "| ------ | ------------------- | ------ | ------------------- |\n"

line = line[4:]

for i in range(len(line)):
    line[i] = line[i].replace('.', '')
    line[i] = line[i].replace('\n', '')

r = 0
if len(line)%2 != 0:
    r = len(line)//2+1
else:
    r = len(line)//2

count = 51
pathMap = {}
while count < len(line):
    pathMap[count] = str(count-50) + '-' + str(count-1)
    count += 50
pathMap[count] = str(count-50) + '-' + str(count-1)
print(pathMap)

for i in range (r):
    link1 = ''
    link2 = ''
    index1 = line[i].split(' ', maxsplit=1)[0]
    name1 = line[i].split(' ', maxsplit=1)[1]
    if r+i < len(line):
        index2 = line[r+i].split(' ', maxsplit=1)[0]
        name2 = line[r+i].split(' ', maxsplit=1)[1]
    for key,value in pathMap.items():
        if (i < key):
            link1 = "Leet-Code/"+ value+"/"+ index1+"-"+ name1.replace(' ', '%20') +".java"
            break

    for key, value in pathMap.items():
        if (r+i < key):
            link2 = "Leet-Code/"+ value+"/"+ index2+"-"+ name2.replace(' ', '%20') +".java"
            break

    if i == r-1 and len(line)%2 != 0:
        data_to_write = data_to_write + "| " + index1 + " | [" + name1 + "](https://github.com/Rajat16353/Codes/blob/master/" + link1 + ") |"
    else:
        data_to_write = data_to_write + "| "+ index1 + " | [" + name1 + "](https://github.com/Rajat16353/Codes/blob/master/" + link1+") | " + index2 + " | [" + name2 + "](https://github.com/Rajat16353/Codes/blob/master/"+link2+") |\n"
    
# print(data_to_write)
f.close()

with open(path_to_write_file, 'w') as f:
    f.write(data_to_write)
f.close()