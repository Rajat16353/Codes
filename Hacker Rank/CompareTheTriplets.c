#include <assert.h>
#include <ctype.h>
#include <limits.h>
#include <math.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int comp(int a[3],int b[3],int r[2])
{
    int i,m=0,n=0;
    for(i=0;i<3;i++)
    {   
        if(a[i]>b[i])
            m++;
        else if(a[i]<b[i])
            n++;
    }
    r[0]=m;
    r[1]=n;
    return r[2];
}

int main()
{
    int i,a[3],b[3],r[2];

    for(i=0;i<3;i++)
        scanf("%d",&a[i]);
    for(i=0;i<3;i++)
        scanf("%d",&b[i]);
    r[2]=comp(a,b,r);
    printf("%d %d",r[0],r[1]);
    return 0;
}