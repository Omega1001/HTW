/*
 * Deque.cpp
 *
 *  Created on: 28.06.2019
 *      Author: jannik
 */

#include <Deque.hpp>
#include <memory>
#include <string>

using namespace std;

Deque::Deque() {
}

Deque::Deque(Deque & original) {
	for (int i = 0; i < original.size; i++) {
		this->pushBack(original[i]);
	}
}

Deque::Deque(char ** &initialElements, int count) {
	for (int i = 0; i < count; i++) {
		std::string * s = new std::string(initialElements[i]);
		this->pushBack(*s);
	}
}

Deque::~Deque() {
	while (head != nullptr) {
		dequeElement * work = tail;
		unchainElement(work);
		delete work->element;
		delete work;
	}
}

void Deque::unchainElement(Deque::dequeElement * element) {
	if (element->prev == nullptr) {
		head = element->next;
	} else {
		element->prev->next = element->next;
	}
	if (element->next == nullptr) {
		tail = element->prev;
	} else {
		element->next->prev = element->prev;
	}
}

void Deque::pushBack(std::string element) {
	pushAfter(tail, element);
}

void Deque::pushFront(std::string element) {
	pushBefore(head, element);
}

void Deque::pushBefore(dequeElement * parent, std::string element) {
	dequeElement * el = new dequeElement;
	el->element = new std::string(element);

	if (parent == nullptr) {
		if (isEmpty()) {
			head = el;
			tail = el;
		} else {
			throw logic_error("Invalid target");
		}
	} else {
		if (parent->prev == nullptr) {
			head = el;
		} else {
			parent->prev->next = el;
		}
		el->prev = parent->prev;
		el->next = parent;
		parent->prev = el;
	}
	size++;
}

void Deque::pushAfter(dequeElement * parent, std::string element) {
	dequeElement * el = new dequeElement;
	el->element = new std::string(element);

	if (parent == nullptr) {
		if (isEmpty()) {
			head = el;
			tail = el;
		} else {
			throw logic_error("Invalid target");
		}
	} else {
		if (parent->next == nullptr) {
			tail = el;
		} else {
			parent->next->prev = el;
		}
		el->prev = parent;
		el->next = parent->next;
		parent->next = el;
	}
	size++;
}

void Deque::operator=(Deque toSet) {
	swap(this->head, toSet.head);
	swap(this->tail, toSet.tail);
	swap(this->size, toSet.size);
}

void Deque::operator+=(Deque toAdd) {
	if (toAdd.isEmpty()) {
		//Do nothing
	} else {
		this->tail->next = toAdd.head;
		toAdd.head->prev = tail;
		this->tail = toAdd.tail;
		toAdd.head = nullptr;
		toAdd.tail = nullptr;
	}

}

std::string & Deque::operator [](int index) {
	dequeElement * elm = head;
	for (int i = 0; (i < index && elm != nullptr); i++) {
		elm = elm->next;
	}
	if (elm == nullptr) {
		throw logic_error("No such element");
	}
	return *(elm->element);
}

std::string Deque::popBack() {
	dequeElement * el = tail;
	unchainElement(el);
	std::string res = *el->element;
	delete el;
	size--;
	return res;
}

std::string Deque::popFront() {
	dequeElement * el = head;
	unchainElement(el);
	std::string res = *el->element;
	delete el;
	size--;
	return res;
}

bool operator==(Deque& a, Deque& b) {
	if (a.size != b.size) {
		return false;
	} else {
		for (int i = 0; i < a.size; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
	}
	return true;
}

bool operator!=(Deque& a, Deque& b) {
	return !(a == b);
}

void operator+(Deque& a, Deque& b) {
	(a += b);
}

Deque::iterator Deque::begin() {
	Deque::iterator res(head);
	return res;
}

Deque::iterator Deque::end() {
	iterator res;
	return res;
}
