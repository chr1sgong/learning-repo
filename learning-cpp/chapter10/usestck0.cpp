// usestck0.cpp -- the client program
// compile with stock00.cpp
#include <iostream>
#include "stock00.h"

int main()
{
    Stock fluffy_the_cast;
    fluffy_the_cast.acquire("NanoSmart", 20, 12.50);
    fluffy_the_cast.show();
    fluffy_the_cast.buy(15, 18.125);
    fluffy_the_cast.show();
    fluffy_the_cast.sell(400, 20.00);
    fluffy_the_cast.show();
    fluffy_the_cast.buy(300000, 40.125);
    fluffy_the_cast.show();
    fluffy_the_cast.sell(300000, 0.125);
    fluffy_the_cast.show();

    return 0;
}