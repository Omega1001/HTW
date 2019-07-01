/*
 * strlib.cpp
 *
 *  Created on: 03.06.2019
 *      Author: jannik
 */

#include <strlib.hpp>

bool isBlank(const char c) {
	switch (c) {
	case ' ':
	case '\t':
	case '\n':
		return true;
	default:
		return false;
	}
}

int wc(const char * inp) {
	int res = 0;
	bool hasChar = false;
	while ((*inp) != '\0') {
		if (isBlank(*inp)) {
			if (hasChar) {
				res++;
			}
			hasChar = false;
		} else {
			hasChar = true;
		}
		inp++;
	}
	if (hasChar) {
		res++;
	}
	return res;
}

int compairIgnoresWhithspaces(const char * first, const char * second) {
	int res = 0;
	while (res == 0 && *first != '\0' && *second != '\0') {
		if ((*first) == ' ') {
			first++;
		} else if ((*second) == ' ') {
			second++;
		} else {
			res = (*first) - (*second);
			first++;
			second++;
		}
	}

	if(res == 0 && ((*first != '\0') ^ (*second != '\0'))){
		if (*first != '\0'){
			res = -1;
		}else{
			res = 1;
		}
	}

	return res;
}

bool empty (const char * str){
	bool res = true;
	while (res &&(*str) != '\0'){
		res = isBlank(*str);
		str++;
	}
	return res;
}

