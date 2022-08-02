import os
import subprocess

files = {}
directory = './450-Solved-questions'

for folder in os.listdir(directory):
    subfolder = directory + '/'+ folder
    if os.path.isdir(subfolder):
        for filename in os.listdir(subfolder):
            # print(filename)
            index, name = filename.split('-', maxsplit=1)
            name, file_extension = name.split('.')
            link = subfolder[2:]+'/'+filename.replace(' ', '%20')
            files[int(index)] = {'filename': name, 'extension': file_extension, 'link': link}

        
    

print(len(files))

path_to_write_file = "450-Solved-questions/README.md"


data_to_write = "# 450 Problems from Love Babar DSA sheet"+"\n"+"\n"
data_to_write = data_to_write + "| Sr. No | Name of the problem | Sr. No | Name of the problem |\n"
data_to_write = data_to_write + "| ------ | ------------------- | ------ | ------------------- |\n"


for i in range(1, len(files)+1):
    name = files[i]['filename']
    link = files[i]['link']
    if i % 2 == 1:
        data_to_write = data_to_write + '| '+str(i)+' | ['+name+'](https://github.com/Rajat16353/Codes/blob/master/' + link + ') '
    else:
        data_to_write = data_to_write + '| '+str(i)+' | ['+name+'](https://github.com/Rajat16353/Codes/blob/master/' + link + ') |\n'

# print(data_to_write)

with open(path_to_write_file, 'w') as f:
    f.write(data_to_write)
f.close()

p = subprocess.Popen("python leetcodeScript.py", stdout=subprocess.PIPE, shell=True)

status = p.communicate()
print(status)

p = subprocess.Popen("python gitScript.py", stdout=subprocess.PIPE, shell=True)

status = p.communicate()
print(status)