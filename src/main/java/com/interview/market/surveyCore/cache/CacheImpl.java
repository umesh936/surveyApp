package com.interview.market.surveyCore.cache;

import java.util.HashMap;

import com.interview.market.surveyCore.dto.QuestionDTO;

public class CacheImpl {
	int capacity;
	HashMap<Long, Node> map = new HashMap<Long, Node>();
	Node head = null;
	Node end = null;

	public CacheImpl(int capacity) {
		this.capacity = capacity;
	}

	public QuestionDTO get(Long key) {
		if (map.containsKey(key)) {
			Node n = map.get(key);
			remove(n);
			setHead(n);
			return n.question;
		}

		return null;
	}

	public void remove(Long id) {
		remove(map.get(id));
	}
	
	public void remove(Node n) {
		if (n.pre != null) {
			n.pre.next = n.next;
		} else {
			head = n.next;
		}

		if (n.next != null) {
			n.next.pre = n.pre;
		} else {
			end = n.pre;
		}

	}

	public void setHead(Node n) {
		n.next = head;
		n.pre = null;

		if (head != null)
			head.pre = n;

		head = n;

		if (end == null)
			end = head;
	}

	public void set(Long key, QuestionDTO value) {
		if (map.containsKey(key)) {
			Node old = map.get(key);
			old.question = value;
			remove(old);
			setHead(old);
		} else {
			Node created = new Node(key, value);
			if (map.size() >= capacity) {
				map.remove(end.key);
				remove(end);
				setHead(created);
			} else {
				setHead(created);
			}
			map.put(key, created);
		}
	}

	class Node {
		Long key;
		QuestionDTO question;
		Node pre;
		Node next;

		public Node(Long key, QuestionDTO question) {
			this.key = key;
			this.question = question;
		}
	}

}
