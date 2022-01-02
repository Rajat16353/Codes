#include <iostream>
 
using namespace std;
 
int main() {
	int n,l;
	cin>>l>>n;
	int d[n][2];
	for(int i=0;i<n;i++)
	{
	    for(int j=0;j<2;j++)
	        cin>>d[i][j];
	}
	for(int i=0;i<n;i++)
	{
	    if(d[i][0]==d[i][1] && d[i][0]>=l)
	        cout<<"ACCEPTED\n";
	    else if(d[i][0]<l || d[i][1]<l)
	        cout<<"UPLOAD ANOTHER\n";
	    else
	        cout<<"CROP IT\n";
	}
}