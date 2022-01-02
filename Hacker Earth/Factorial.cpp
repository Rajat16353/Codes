#include <iostream>
 
using namespace std;
 
int main() {
	int num,ans=1;
	cin >> num;										// Reading input from STDIN
	for(int i=1;i<=num;i++)
	{
	    ans*=i;
	}
	cout<<ans;
}
 