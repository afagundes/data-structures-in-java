package com.ari.structures;

import java.util.LinkedList;

public class HashTable<K, V> {

    private Node root;
    private int size;

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            return;
        }

        put(key, value, root);
        size++;
    }

    private void put(K key, V value, Node node) {
        int hash = key.hashCode();

        if (hash < node.hash) {
            if (node.left == null) {
                node.left = new Node(hash, key, value);
                return;
            }

            put(key, value, node.left);
            return;
        }

        if (hash > node.hash) {
            if (node.right == null) {
                node.right = new Node(hash, key, value);
                return;
            }

            put(key, value, node.right);
            return;
        }

        // Same hash - found it
        // If key already exists, replace its value
        for (Entry entry : node.values) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // If key doesn't exist yet, create a new entry
        node.values.add(new Entry(key, value));
    }

    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node node) {
        if (node == null) return null;

        int hash = key.hashCode();

        if (hash < node.hash) {
            return get(key, node.left);
        }

        if (hash > node.hash) {
            return get(key, node.right);
        }

        for (Entry entry : node.values) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    public void remove(K key) {
        root = remove(key, key.hashCode(), root);
        size = root == null ? 0 : size - 1;
    }

    private Node remove(K key, int hash, Node node) {
        // Many ifs XD

        if (node == null) return null;

        if (hash < node.hash) {
            node.left = remove(key, hash, node.left);
            return node;
        }

        if (hash > node.hash) {
            node.right = remove(key, hash, node.right);
            return node;
        }

        if (key != null)
            node.values.removeIf(entry -> entry.key.equals(key));

        if (!node.values.isEmpty())
            return node;

        // This code is the same as in class BinarySearchTree. It's intentional
        // noinspection DuplicatedCode
        if (node.left == null && node.right == null)
            return null;

        if (node.left == null)
            return node.right;

        if (node.right == null)
            return node.left;

        Node aux = min(node.right);

        if (aux == null)
            return node;

        node.values.addAll(aux.values);
        node.right = remove(null, aux.hash, node.right);
        return node;
    }

    private Node min(Node node) {
        Node current = node;

        while (current != null && current.left != null) {
            current = current.left;
        }

        return current;
    }

    private class Entry {
        final K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Node {
        final int hash;
        final LinkedList<Entry> values = new LinkedList<>();

        Node left;
        Node right;

        public Node(K key, V value) {
            this.hash = key.hashCode();
            values.add(new Entry(key, value));
        }

        public Node(int hash, K key, V value) {
            this.hash = hash;
            values.add(new Entry(key, value));
        }

    }

}
