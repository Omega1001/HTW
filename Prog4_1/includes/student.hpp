/*
 * student.hpp
 *
 *  Created on: 09.05.2019
 *      Author: jannik
 */

#ifndef STUDENT_HPP_
#define STUDENT_HPP_
#define student struct _student
#define studentCmp struct _studentComp
#include <string>

struct _student {

		std::string matrikelnummer;
		std::string firstName;
		std::string lastName;
		std::string birthDate;
		std::string averageGrade;
};

#endif /* STUDENT_HPP_ */
