// ourfunc.cpp -- defining your own function
#include <iostream>

void simo(int n); // function prototype for simo()

int main()
{
    using namespace std;
    simo(3);    // call the simo function
    cout << "Pick an integer: ";
    int count;
    cin >> count;
    simo(count);        // call simo again
    cout << "Done!" << endl;
    return 0;
}

void simo(int n)    // define the simo function
{
    using namespace std;
    cout << "Simon says touch your toe " << n << " times." << endl; // void function doesn't need return statements.
}