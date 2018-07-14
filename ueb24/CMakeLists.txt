cmake_minimum_required(VERSION 2.8.9)

set(ProjectName "ueb24")
project("${ProjectName}" "C")
#Bring the headers into the project
include_directories(include)
 
#Add all C sources
file(GLOB SOURCES "src/*.c")
 
add_executable("${ProjectName}.exe" ${SOURCES})