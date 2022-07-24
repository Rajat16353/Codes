#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

int main()
{
    int n,a[60],i,b,c;
    scanf("%d",&n);
    for(i=0;i<n;i++)
    {
        scanf("%d",&a[i]);
    }
    
    for(i=0;i<n;i++)
    {
        if(a[i]>=38)
        {
            b=a[i]/5;
            c=a[i]%5;
            c=5-c;
            if(c<3)
            {
                a[i]=(b+1)*5;
            }
        }
        
    }
    for(i=0;i<n;i++)
        printf("%d\n",a[i]);
    return 0;
}