#include <student.hpp>
#include <vector>
#include <iostream>
#include <iomanip>
#include <algorithm>
#include <sstream>
#include <fstream>

using namespace std;
/**
 * Method to format output as table
 *
 * @param key first column attribute
 * @param val second column attribute
 */
void inline println(string key, string val) {
	cout << setw(15) << right << key << " : " << left << val
			<< endl;
}

/**
 * Method to autput a single student
 * @param pointer to student to output
 */
void outputStudent(student * s) {
	println("Student", s->matrikelnummer);
	println("First Name", s->firstName);
	println("Last Name", s->lastName);
	println("Birthday", s->birthDate);
	println("Average Grade", s->averageGrade);
	cout << endl;
}

/**
 * Output all students in a vector
 * @param containing vector
 */
void outputStudents(vector<student> * target) {
	for (student s : *target) {
		outputStudent(&s);
	}

}

/**
 * Sort students by lastName, firstName in ascending order
 */
bool sortByName(student first, student secod) {
	int res = first.lastName.compare(secod.lastName);
	if (res == 0){
		res = first.firstName.compare(secod.firstName);
	}
	return 0 > res;
}
/**
 * Sort students by averageGrade in ascending order
 */
bool sortByGrade(student first, student secod) {
	return 0 > first.averageGrade.compare(secod.averageGrade);
}

/**
 * Read Students from file and put them in the supplied vector
 * @param File to read from
 * @param Vector to put read data into
 */
void readStudents(ifstream * in, vector<student> * target) {
	string lineBuffer;
	string entryBuffer;
	student entry;
	while (getline(*in, lineBuffer)) {
		cout << "DEBUG: Line = " << lineBuffer;
		istringstream lineStream(lineBuffer);
		if (getline(lineStream, entryBuffer, ';')) {
			entry.matrikelnummer = string(entryBuffer);
		}
		if (getline(lineStream, entryBuffer, ';')) {
			entry.lastName = string(entryBuffer);
		}
		if (getline(lineStream, entryBuffer, ';')) {
			entry.firstName = string(entryBuffer);
		}
		if (getline(lineStream, entryBuffer, ';')) {
			entry.birthDate = string(entryBuffer);
		}
		if (getline(lineStream, entryBuffer, ';')) {
			entry.averageGrade = string(entryBuffer);
		}
		target->push_back(entry);
	}
}

int main(int argc, char **argv) {
	if (argc<=1){
		cout << "Missing File parameter, exiting"<<endl;
		return 1;
	}
	ifstream inFile(argv[1]);
	if (inFile.is_open()) {
		vector<student> * target = new vector<student>;

		readStudents(&inFile, target);

		sort(target->begin(), target->end(), sortByName);
		outputStudents(target);

		cout << endl << endl;

		sort(target->begin(), target->end(), sortByGrade);
		outputStudents(target);

		delete target;
		inFile.close();
	}else{
		cout << "Unable to open File \""<<argv[1]<<"\"";
		return 2;
	}

	return 0;
}
