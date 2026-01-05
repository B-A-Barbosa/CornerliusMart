@echo off
setlocal
cd /d "%~dp0"
set "JAVA_HOME=%~dp0jre"

if not exist "%JAVA_HOME%\bin\java.exe" (
  echo [ERROR] Java runtime not found: "%JAVA_HOME%\bin\java.exe"
  pause
  exit /b 1
)

REM Classpath includes your app jar and gson dependency
"%JAVA_HOME%\bin\java.exe" -cp "%~dp0app\CornerliusMart.jar;%~dp0lib\gson-2.13.2.jar" Main
echo [INFO] Exit code: %ERRORLEVEL%
pause
endlocal
