path_to_read_file = "C:/Users/gupta/Documents/450 problems.txt"
path_to_write_file = "450-Solved-questions/README.md"
with open(path_to_read_file, 'r') as f:
    lines = f.readlines();
    # print(line)
data_to_write = "450 Problems sheet by Love Babar DSA sheet\n\n"
data_to_write = data_to_write + "| Sr. No | Name of the problem | Sr. No | Name of the problem |\n"
data_to_write = data_to_write + "| ------ | ------------------- | ------ | ------------------- |\n"

for i in range(len(lines)):
    lines[i] = lines[i].replace('.', '')
    lines[i] = lines[i].replace('\n', '')

for line in lines:
    index = line.split(' ', maxsplit=1)[0]
    name = line.split(' ', maxsplit=1)[1]
    link = '450-Solved-questions/'+index+'-'+name
    if int(index) % 2 == 1:
        data_to_write += '| '+index+' | ['+name+'](https://github.com/Rajat16353/Codes/blob/master/' + link + ') '
    else:
        data_to_write += '| '+index+' | ['+name+'](https://github.com/Rajat16353/Codes/blob/master/' + link + ') |\n'

# print(data_to_write)
f.close()

with open(path_to_write_file, 'w') as f:
    f.write(data_to_write)
f.close()