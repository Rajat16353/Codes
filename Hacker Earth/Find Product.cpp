#include <iostream>
#include <math.h>
 
using namespace std;
 
int main() {
    int n;
	cin>>n;
	int a[n];
    long long int ans=1;
	for(int i=0;i<n;i++)
	{
	    cin>>a[i];
	}
	for(int i=0;i<n;i++)
	{
	    ans=(ans*a[i])%(int)(pow(10,9)+7);
	   
	}
	cout<<ans;
	
}