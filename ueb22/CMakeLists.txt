cmake_minimum_required(VERSION 2.8.9)

set(ProjectName "ueb22")
project("${ProjectName}")
#Bring the headers into the project
include_directories(include)
 
#Add all C sources
file(GLOB SOURCES "src/*.c")
 
add_executable("${ProjectName}.exe" ${SOURCES})
