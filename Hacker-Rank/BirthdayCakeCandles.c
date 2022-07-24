#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

int main()
{
    long int n,a[100000],i,max=0,count=0;
    scanf("%ld",&n);
    for(i=0;i<n;i++)
        scanf("%ld",&a[i]);
    for(i=0;i<n;i++)
        if(max<a[i])
            max=a[i];
    for(i=0;i<n;i++)
        if(a[i]==max)
            count++;
    printf("%ld",count);                        
    return 0;
}