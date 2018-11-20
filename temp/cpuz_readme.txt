
CPU-Z Readme file
------------------

Version 1.86
August 2018
Contact : cpuz@cpuid.com
Web page: https://www.cpuid.com/softwares/cpu-z.html
Validation page : https://valid.x86.fr/
Hall of Fame : https://valid.x86.fr/records.php
CPUID SDK : http://www.cpuid-pro.com/products-services.php


Configuration file (cpuz.ini)
------------------------------

The configuration file must be named cpuz.ini and be present at the same directory level
as cpuz.exe. It contains the following :

[CPU-Z]
VERSION=x.x.x.x
TextFontName=
TextFontSize=14
TextFontColor=000080
LabelFontName=
LabelFontSize=14
ACPI=1
PCI=1
MaxPCIBus=256
DMI=1
Sensor=1
SMBus=1
Display=1
UseDisplayAPI=1
BusClock=1
Chipset=1
SPD=1
XOC=0
CheckUpdates=1


- TextFontName : Font used for the information boxes. 
- TextFontSize : Size of the font used for the information boxes. 
- TextFontColor : Color of the font used for the information boxes. Value is expressed in hexadecimal, and consists in a classic Red/Green/Blu