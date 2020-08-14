#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

int main()
{
    long long int a[100],i,max,sum=0,sum1=0,min;
    for(i=0;i<5;i++)
        scanf("%lld",&a[i]);
    max=a[0];
    min=a[4];
    for(i=0;i<5;i++)    
    {
        if(max<a[i])
            max=a[i];
        if(min>a[i])
            min=a[i];        
    }
    
    for(i=0;i<5;i++)
    {
        sum+=a[i];
        
    }
    
    sum1=sum-max;
    sum-=min;
    
    printf("%lld %lld",sum1,sum);    
    return 0;
}