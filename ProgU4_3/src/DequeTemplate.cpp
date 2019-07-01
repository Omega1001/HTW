/*
 * Deque.cpp
 *
 *  Created on: 28.06.2019
 *      Author: jannik
 */

#include <DequeTemplate.hpp>
#include <memory>

using namespace std;

template<typename T>
DequeTemplate<T>::DequeTemplate() {
}
template<typename T>
DequeTemplate<T>::DequeTemplate(DequeTemplate<T> & original) {
	for (int i = 0; i < original.size; i++) {
		this->pushBack(original[i]);
	}
}

template<typename T>
DequeTemplate<T>::~DequeTemplate() {
	while (head != nullptr) {
		dequeElement * work = tail;
		unchainElement(work);
		delete work->element;
		delete work;
	}
}
template<typename T>
void DequeTemplate<T>::unchainElement(
		DequeTemplate<T>::dequeElement * element) {
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
template<typename T>
void DequeTemplate<T>::pushBack(T element) {
	pushAfter(tail, element);
}
template<typename T>
void DequeTemplate<T>::pushFront(T element) {
	pushBefore(head, element);
}
template<typename T>
void DequeTemplate<T>::pushBefore(dequeElement * parent, T element) {
	dequeElement * el = new dequeElement;
	el->element = new T(element);

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
template<typename T>
void DequeTemplate<T>::pushAfter(dequeElement * parent, T element) {
	dequeElement * el = new dequeElement;
	el->element = new T(element);

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
template<typename T>
void DequeTemplate<T>::operator=(DequeTemplate<T> toSet) {
	swap(this->head, toSet.head);
	swap(this->tail, toSet.tail);
	swap(this->size, toSet.size);
}
template<typename T>
void DequeTemplate<T>::operator+=(DequeTemplate<T> toAdd) {
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
template<typename T>
T & DequeTemplate<T>::operator [](int index) {
	dequeElement * elm = head;
	for (int i = 0; (i < index && elm != nullptr); i++) {
		elm = elm->next;
	}
	if (elm == nullptr) {
		throw logic_error("No such element");
	}
	return *(elm->element);
}
template<typename T>
T DequeTemplate<T>::popBack() {
	dequeElement * el = tail;
	unchainElement(el);
	T res = *el->element;
	delete el;
	size--;
	return res;
}
template<typename T>
T DequeTemplate<T>::popFront() {
	dequeElement * el = head;
	unchainElement(el);
	T res = *el->element;
	delete el;
	size--;
	return res;
}
template<typename T>
bool operator==(DequeTemplate<T>& a, DequeTemplate<T>& b) {
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
template<typename T>
bool operator!=(DequeTemplate<T>& a, DequeTemplate<T>& b) {
	return !(a == b);
}
template<typename T>
void operator+(DequeTemplate<T>& a, DequeTemplate<T>& b) {
	(a += b);
}

