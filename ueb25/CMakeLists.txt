cmake_minimum_required(VERSION 2.8.9)

set(ProjectName "ueb25")
project("${ProjectName}" "C")
#Bring the headers into the project
include_directories(include)

#target_link_libraries("${ProjectName}" m)
 
#Add all C sources
file(GLOB SOURCES "src/*.c")
 
add_executable("${ProjectName}.exe" ${SOURCES})
target_link_libraries("${ProjectName}.exe" m)
