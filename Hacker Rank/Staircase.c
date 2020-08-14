#include <assert.h>
#include <limits.h>
#include <math.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    int n,i,j,k;
    scanf("%d",&n);

    for(i=n;i>0;i--)
    {
        k=n-(i-1);
        for(j=1;j<i;j++)
        {
            printf(" ");
        }
    
        while(k)
        {
            printf("#");
            k--;
        }    
        printf("\n");
    }
    return 0;
}
