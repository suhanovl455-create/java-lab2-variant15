@echo off
chcp 65001 >nul
cd /d "%~dp0"
if not exist build mkdir build
javac --release 17 -encoding UTF-8 -d build src\*.java
if errorlevel 1 goto end
java -Dfile.encoding=UTF-8 -cp build Main
:end
pause
