#pragma once

#include<string>
#include<chrono>

class Data
{
    public:
        std::string e_mail;
        //Current time in sec from 1970 Jan 01
        std::chrono::system_clock::time_point time_sec;

        Data(std::string email)
            :e_mail(email),
            time_sec(std::chrono::system_clock::now())
        {}

    
};

