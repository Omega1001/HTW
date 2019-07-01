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

template<typename T>
class DequeTemplate {
private:
	struct dequeElement;

	dequeElement * head = nullptr;
	dequeElement * tail = nullptr;
	int size = 0;

	void unchainElement(dequeElement * element);
	void pushBefore(dequeElement * parent, T);
	void pushAfter(dequeElement * parent, T);
public:
	class iterator;

	DequeTemplate();
	DequeTemplate(DequeTemplate &original);
	virtual ~DequeTemplate();

	iterator begin() {
		iterator res(head);
		return res;
	}

	iterator end() {
		iterator res;
		return res;
	}

	void pushBack(T element);
	void pushFront(T element);
	T popBack();
	T popFront();
	int getSize() {
		return size;
	}
	;
	bool isEmpty() {
		return head == nullptr && tail == nullptr;
	}

	void operator=(DequeTemplate toSet);
	void operator+=(DequeTemplate toAdd);
	T &operator[](int index);
	friend bool operator==(DequeTemplate& a, DequeTemplate& b);
	friend bool operator!=(DequeTemplate& a, DequeTemplate& b);
	friend void operator+(DequeTemplate& a, DequeTemplate& b);

};

template<typename T>
struct DequeTemplate<T>::dequeElement {
	DequeTemplate<T>::dequeElement * next = nullptr;
	DequeTemplate<T>::dequeElement * prev = nullptr;
	T * element = nullptr;
};

template<typename T>
class DequeTemplate<T>::iterator {
private:
	dequeElement * curent;
public:
	iterator() :
			curent(nullptr) {
	}
	;
	iterator(dequeElement * el) :
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

	T * operator*() {
		return curent->element;
	}

};

#endif /* DEQUE_HPP_ */
