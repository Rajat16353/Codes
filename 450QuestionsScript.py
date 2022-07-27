import os
import re

files = {}

for filename in os.listdir('./450-Solved-questions'):
    index, name = filename.split('-')
    files[int(index)] = name

print(len(files))

path_to_write_file = "450-Solved-questions/README.md"


data_to_write = "# 450 Problems sheet by Love Babar DSA sheet"+"\n"+"\n"
data_to_write = data_to_write + "| Sr. No | Name of the problem | Sr. No | Name of the problem |\n"
data_to_write = data_to_write + "| ------ | ------------------- | ------ | ------------------- |\n"


for i in range(1, len(files)+1):
    name = files[i].split('.')[0]
    link = '450-Solved-questions/'+str(i)+'-'+files[i].replace(' ', '%20')
    if i % 2 == 1:
        data_to_write = data_to_write + '| '+str(i)+' | ['+name+'](https://github.com/Rajat16353/Codes/blob/master/' + link + ') '
    else:
        data_to_write = data_to_write + '| '+str(i)+' | ['+name+'](https://github.com/Rajat16353/Codes/blob/master/' + link + ') |\n'

# print(data_to_write)

with open(path_to_write_file, 'w') as f:
    f.write(data_to_write)
f.close()