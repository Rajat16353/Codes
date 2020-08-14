n = int(input())
out = []
for i in range(n):
    n = int(input())
    n = ((1 << 32) - 1) ^ n
    print(n)
