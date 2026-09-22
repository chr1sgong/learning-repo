// stringbad.cpp -- StringBad class method
#include <cstring>      // c style string
#include "stringbad.h"
using std::cout;

// initializing static class number
int StringBad::num_strings = 0;

// class methods
// construct StringBad from C string
StringBad::StringBad(const char * s)
{
    len = std::strlen(s);
    str = new char[len + 1];        // allot storage
    std::strcpy(str, s);            // initialize pointer
    num_strings++;
    cout << num_strings << ": \"" << str << "\" object created\n";
}

StringBad::StringBad()
{
    len = 4;
    str = new char[4];
    std::strcpy(str, "C++");
    num_strings++;
    cout << num_strings << ": \"" << str << "\" object created\n";
}

StringBad::StringBad(const StringBad & sb)
{
    num_strings++;
    len = sb.len;
    str = new char[len + 1];
    std::strcpy(str, sb.str);
    cout << num_strings << ": \"" << str << "\" object created\n";
}

StringBad & StringBad::operator=(const StringBad & st) {
    if (this == &st)
    {
        return *this;
    }
    delete [] str;
    len = st.len;
    str = new char[len + 1];
    std::strcpy(str, st.str);
    return *this;
}

StringBad::~StringBad()     // necessary destructor
{
    cout << "\"" << str << "\" object deleted, ";
    --num_strings;
    cout << num_strings << " left\n";
    delete [] str;          // required
}

std::ostream & operator<<(std::ostream & os, const StringBad & st)
{
    os << st.str;
    return os;
}