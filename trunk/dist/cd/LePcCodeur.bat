@echo off
cd bin
start javaw.exe -jar LPCDraw.jar
echo veuillez patienter pendant l'initialisation de l'application...
ping localhost -n 16 > NUL