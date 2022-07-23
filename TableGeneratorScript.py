path_to_read_file = "C:/Users/gupta/Documents/LeetCode Problems.txt"
path_to_write_file = "Leet Code/README.md"
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


for i in range (r):
    if i == r-1 and len(line)%2 != 0:
        data_to_write = data_to_write + "| " + line[i].split(' ', maxsplit=1)[0] + " | " + line[i].split(' ', maxsplit=1)[1] + " |"
    else:
        data_to_write = data_to_write + "| "+line[i].split(' ', maxsplit=1)[0] + " | " + line[i].split(' ', maxsplit=1)[1] + " | " + line[r+i].split(' ', maxsplit=1)[0] + " | " + line[r+i].split(' ', maxsplit=1)[1] + " |\n"
    
print(data_to_write)
f.close()

with open(path_to_write_file, 'w') as f:
    f.write(data_to_write)
f.close()

import subprocess

# This is our shell command, executed by Popen.

p = subprocess.Popen("git add .", stdout=subprocess.PIPE, shell=True)

print(p.communicate())

p = subprocess.Popen('git commit -m "Add solutions"', stdout=subprocess.PIPE, shell=True)

print(p.communicate())

p = subprocess.Popen("git push", stdout=subprocess.PIPE, shell=True)

print(p.communicate())