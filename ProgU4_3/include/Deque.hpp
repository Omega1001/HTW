/*
 * Deque.hpp
 *
 *  Created on: 28.06.2019
 *      Author: jannik
 */

#ifndef DEQUE_HPP_
#define DEQUE_HPP_

#include <string>
#include <memory>


class Deque {
private:
	struct dequeElement;

	dequeElement * head = nullptr;
	dequeElement * tail = nullptr;
	int size = 0;

	void unchainElement(dequeElement * element);
	void pushBefore(dequeElement * parent, std::string);
	void pushAfter(dequeElement * parent, std::string);
public:
	class iterator;

	Deque();
	Deque(char ** & initialElements, int count);
	Deque(Deque &original);
	virtual ~Deque();

	void pushBack(std::string element);
	void pushFront(std::string element);
	std::string popBack();
	std::string popFront();
	int getSize() {
		return size;
	}
	;
	bool isEmpty() {
		return head == nullptr && tail == nullptr;
	}

	iterator begin();
	iterator end();

	void operator=(Deque toSet);
	void operator+=(Deque toAdd);
	std::string &operator[](int index);
	friend bool operator==(Deque& a, Deque& b);
	friend bool operator!=(Deque& a, Deque& b);
	friend void operator+(Deque& a, Deque& b);

};


struct Deque::dequeElement {
	Deque::dequeElement * next = nullptr;
	Deque::dequeElement * prev = nullptr;
	std::string * element = nullptr;
};


class Deque::iterator {
private:
	Deque::dequeElement * curent;
public:
	iterator() :
			curent(nullptr) {
	}
	;
	iterator(Deque::dequeElement * el) :
			curent(el) {

	}
	iterator(iterator * org) {
		this->curent = org->curent;
	}
	iterator * operator++() {
		curent = curent->next;
		return this;
	}

	void operator=(iterator toSet) {
		this->curent = toSet.curent;
	}

	bool operator!=(const iterator& it) {
		return curent != it.curent;
	}

	std::string * operator*() {
		return curent->element;
	}

};

#endif /* DEQUE_HPP_ */
