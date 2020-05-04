@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  javafx-test-canvas startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and JAVAFX_TEST_CANVAS_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\javafx-test-canvas-1.0-SNAPSHOT.jar;%APP_HOME%\lib\javafx-swing-13-linux.jar;%APP_HOME%\lib\javafx-controls-13-linux.jar;%APP_HOME%\lib\javafx-graphics-13-linux.jar;%APP_HOME%\lib\javafx-graphics-13.jar;%APP_HOME%\lib\javafx-base-13-linux.jar;%APP_HOME%\lib\javafx-base-13.jar;%APP_HOME%\lib\javafx-plugin-0.0.8.jar;%APP_HOME%\lib\twitch4j-1.0.0-alpha.19.jar;%APP_HOME%\lib\osdetector-gradle-plugin-1.6.1.jar;%APP_HOME%\lib\moduleplugin-1.5.0.jar;%APP_HOME%\lib\twitch4j-chat-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-helix-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-kraken-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-messaginginterface-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-pubsub-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-graphql-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-common-1.0.0-alpha.19.jar;%APP_HOME%\lib\twitch4j-auth-1.0.0-alpha.19.jar;%APP_HOME%\lib\credentialmanager-0.1.0.jar;%APP_HOME%\lib\commons-lang3-3.9.jar;%APP_HOME%\lib\commons-io-2.6.jar;%APP_HOME%\lib\events4j-core-0.7.1.jar;%APP_HOME%\lib\events4j-handler-simple-0.7.1.jar;%APP_HOME%\lib\feign-slf4j-10.7.4.jar;%APP_HOME%\lib\feign-hystrix-10.7.4.jar;%APP_HOME%\lib\hystrix-core-1.5.18.jar;%APP_HOME%\lib\events4j-api-0.7.1.jar;%APP_HOME%\lib\archaius-core-0.7.6.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\caffeine-2.7.0.jar;%APP_HOME%\lib\os-maven-plugin-1.6.0.jar;%APP_HOME%\lib\javaparser-symbol-solver-core-3.13.5.jar;%APP_HOME%\lib\nv-websocket-client-2.9.jar;%APP_HOME%\lib\bucket4j-core-4.7.0.jar;%APP_HOME%\lib\commons-collections4-4.4.jar;%APP_HOME%\lib\feign-okhttp-10.7.4.jar;%APP_HOME%\lib\feign-jackson-10.7.4.jar;%APP_HOME%\lib\commons-configuration-1.10.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.10.2.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.10.2.jar;%APP_HOME%\lib\jackson-databind-2.10.2.jar;%APP_HOME%\lib\apollo-runtime-1.2.2.jar;%APP_HOME%\lib\apollo-api-1.2.2.jar;%APP_HOME%\lib\okhttp-4.3.1.jar;%APP_HOME%\lib\okio-jvm-2.4.1.jar;%APP_HOME%\lib\kotlin-stdlib-1.3.61.jar;%APP_HOME%\lib\annotations-18.0.0.jar;%APP_HOME%\lib\javaparser-symbol-solver-logic-3.13.5.jar;%APP_HOME%\lib\javaparser-symbol-solver-model-3.13.5.jar;%APP_HOME%\lib\guava-27.0-jre.jar;%APP_HOME%\lib\checker-qual-2.6.0.jar;%APP_HOME%\lib\error_prone_annotations-2.3.3.jar;%APP_HOME%\lib\cache-2.0.2.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\javassist-3.24.0-GA.jar;%APP_HOME%\lib\micrometer-core-1.3.2.jar;%APP_HOME%\lib\feign-core-10.7.4.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\commons-logging-1.1.1.jar;%APP_HOME%\lib\jackson-annotations-2.10.2.jar;%APP_HOME%\lib\jackson-core-2.10.2.jar;%APP_HOME%\lib\rxjava-1.2.0.jar;%APP_HOME%\lib\HdrHistogram-2.1.11.jar;%APP_HOME%\lib\javaparser-core-3.13.5.jar;%APP_HOME%\lib\failureaccess-1.0.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.17.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\kotlin-stdlib-common-1.3.61.jar

@rem Execute javafx-test-canvas
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %JAVAFX_TEST_CANVAS_OPTS%  -classpath "%CLASSPATH%" de.calitobundo.CanvasApp %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable JAVAFX_TEST_CANVAS_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%JAVAFX_TEST_CANVAS_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
