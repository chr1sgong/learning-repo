#ifndef STONEWT_H_
#define STONEWT_H_
class Stonewt
{
private:
    enum {Lbs_per_stn = 14};        // pounds per stone
    int stone;                      // whole stones
    double pds_left;                // fractorial pounds
    double pounds;                  // entire weights in pound

public:
    Stonewt(double lbs);            // constructor for double pounds
    Stonewt(int stn, double lbs);   // constructor for stone, lbs
    Stonewt();                      // default constructor
    ~Stonewt();
    void show_lbs() const;          // show weights in pounds format
    void show_stn() const;          // show weights in stone format

};
#endif