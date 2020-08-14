#!/bin/python3

import math
import os
import random
import re
import sys

def swap(q, i, j):
    temp = q[i]
    q[i] = q[j]
    q[j] = temp

def minimumBribes(q):
    b = 0
    cn = [0]*len(q)
    i = 0
    temp = 0
    flag = 0
    while(True):
        if i == n:
          break
        if q[i]-1 == i:
            i += 1
            #print(i)
        else:
            if q[i] > q[i+1] :
                cn[q[i]-1] += 1
                if cn[q[i]-1] > 2:
                    print("Too chaotic")
                    return
                swap(q,i,i+1)
                #print(q, i)
                #print(cn)
                b += 1
                i = temp
                flag = 0
            else:
                if flag == 0:
                  temp = i
                  flag = 1
                i += 1
    print(b)

if __name__ == '__main__':
    t = int(input())

    for t_itr in range(t):
        n = int(input())

        q = list(map(int, input().rstrip().split()))

        minimumBribes(q)
