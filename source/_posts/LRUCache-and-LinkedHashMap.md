---
title: LRUCache and LinkedHashMap
date: 2020-03-21 16:22:50
tags: [coding,Java]
---

LRU(Least Recently Used)Cache算法实现和LinkedHashMap源码分析

<!--more-->

#### LRUCache

- 双向链表实现

```java
import java.util.HashMap;

public class LRUCache {
    class Node {
        int key;
        int value;
        Node pre;
        Node next;
    }

    private HashMap<Integer, Node> cache = new HashMap<>();

    private int capacity;

    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.pre = null;
        tail.next = null;
        head.next = tail;
        tail.pre = head;
    }

    private void addNodeToHead(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void delOneNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    private void moveToHead(Node node) {
        delOneNode(node);
        addNodeToHead(node);
    }

    private Node popTail() {
        Node last = tail.pre;
        this.delOneNode(last);
        return last;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (!cache.containsKey(key)) {
            if (cache.size() == capacity) {
                Node last = this.popTail();
                this.cache.remove(last.key);
            }
            Node toAddNode = new Node();
            toAddNode.key = key;
            toAddNode.value = value;
            cache.put(key, toAddNode);
            addNodeToHead(toAddNode);
        } else {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        }
    }
}

```



- 通过LinkedHashMap实现

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return (int) super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}
```



#### LinkedHashMap