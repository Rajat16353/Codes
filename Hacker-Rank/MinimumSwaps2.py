#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the minimumSwaps function below.
def minimumSwaps(arr):
    cnt = 0
    #print(len(arr))
    i = 0
    while(True):
        if arr[i]-1 == i:
            i += 1
            if i == n:
                break
        else:
            j = arr[i]-1
            #print(j, arr[i])
            temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
            #print(arr)
            cnt += 1
    return cnt

if __name__ == '__main__':

    n = int(input())

    arr = list(map(int, input().split()))
    #print("1 :",arr[0],arr[1],arr[694])
    res = minimumSwaps(arr)

    print(res)
