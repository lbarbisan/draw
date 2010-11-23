@echo off
::Find the current (most recent) Java version
start /w regedit /e reg1.txt "HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Java Runtime Environment"
type reg1.txt | find "CurrentVersion" > reg2.txt
if errorlevel 1 goto ERROR
for /f "tokens=2 delims==" %%x in (reg2.txt) do set JavaTemp=%%~x
if errorlevel 1 goto ERROR
echo Java Version = %JavaTemp%
del reg1.txt
del reg2.txt
 
::Get the home directory of the most recent Java
start /w regedit /e reg1.txt "HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Java Runtime Environment\%JavaTemp%"
type reg1.txt | find "JavaHome" > reg2.txt
if errorlevel 1 goto ERROR
for /f "tokens=2 delims==" %%x in (reg2.txt) do set JavaTemp=%%~x
if errorlevel 1 goto ERROR
echo Java home path (per registry) = %JavaTemp%
del reg1.txt
del reg2.txt
 