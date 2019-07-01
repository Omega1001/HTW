/*
 * Prog4_2.cpp
 *
 *  Created on: 03.06.2019
 *      Author: jannik
 */

#include <Deque.hpp>
#include <iostream>
//#include "DequeTemplate.cpp"

void testDeque() {
//	DequeTemplate<int> dq;
//
//	dq.pushBack(15);
//	dq.pushBack(16);
//	dq.pushBack(17);
//	dq.pushBack(18);
//	dq.pushFront(14);

	Deque dq;
		dq.pushBack("15S");
		dq.pushBack("16S");
		dq.pushBack("17S");
		dq.pushBack("18S");
		dq.pushFront("14S");

	for (int i = 0; i < dq.getSize(); i++) {
		std::cout << "Array Val : " << dq[i] << "\n";
	}


	auto it = dq.begin();

	while (it != dq.end()){

		auto i = *it;
		std::cout << "IT val : "<<*i<<"\n";
		++it;

	}

	while (!dq.isEmpty()) {
		std::cout << "Val : " << dq.popFront() << "\n";
	}

}

