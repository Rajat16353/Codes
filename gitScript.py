import subprocess
import re

# This is our shell command, executed by Popen.
p = subprocess.Popen("git status", stdout=subprocess.PIPE, shell=True)

status = p.communicate()[0].decode('UTF-8').split('\n')

message = ''

i = 0
while i < len(status):
    if 'Changes not staged for commit:' in status[i]:
        print("inside")
        i += 3
        while i < len(status):
            if status[i] == '':
                break
            message = re.sub(r"\s+", ' ', status[i][1:])
            commitCommand = 'git commit -m "'+message.capitalize()+'"'

            p = subprocess.Popen('git add "' + re.sub(r'.+:\s+', '', message) + '"', stdout=subprocess.PIPE, shell=True)
            p.wait()
            print(p.communicate())

            p = subprocess.Popen(commitCommand, stdout=subprocess.PIPE, shell=True)
            p.wait()
            print(p.communicate(input=message)[0].decode('UTF-8'))
            i += 1
    elif 'Untracked files:' in status[i]:
        for stat in status[i+2:len(status)-3]:
            file = re.sub(r"\s+", ' ', stat)
            message = 'Add solutions for'+ file
            commitCommand = 'git commit -m "'+message+'"'

            p = subprocess.Popen('git add "' + file[1:] + '"', stdout=subprocess.PIPE, shell=True)
            p.wait()
            # print(p.communicate())

            p = subprocess.Popen(commitCommand, stdout=subprocess.PIPE, shell=True)
            p.wait()
            # print(p.communicate(input=message)[0].decode('UTF-8'))
        break
    i += 1

p = subprocess.Popen("git push", stdout=subprocess.PIPE, shell=True)
print(p.communicate())