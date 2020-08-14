#include<stdio.h>
#include<stdlib.h>

int main()
{
    int n,i;
    
    long long int a[10],sum=0;

    scanf("%d",&n);
    for(i=0;i<n;i++)
        scanf("%ld",&a[i]);

    for(i=0;i<n;i++)
        sum=sum+a[i];

       printf("%lld",sum);   
       return 0;  
}