package com.ari.structures;

import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public void add(T value) {
        if (value == null) return;

        if (root == null || root.value == null) {
            root = new Node(value);
            return;
        }

        add(value, root);
    }

    private void add(T value, Node currNode) {
        if (value.compareTo(currNode.value) == 0) {
            currNode.value = value;
        }

        if (value.compareTo(currNode.value) < 0) {
            if (currNode.left == null) {
                currNode.left = new Node(value);
                return;
            }

            add(value, currNode.left);
            return;
        }

        if (currNode.right == null) {
            currNode.right = new Node(value);
            return;
        }

        add(value, currNode.right);
    }

    public boolean has(T value) {
        return has(value, root);
    }

    private boolean has(T value, Node currNode) {
        if (currNode == null)
            return false;

        if (value.compareTo(currNode.value) == 0)
            return true;

        if (value.compareTo(currNode.value) < 0) {
            return has(value, currNode.left);
        }

        return has(value, currNode.right);
    }

    /**
     * Pass through the tree in an orderly fashion. Use case: sorting
     */
    public void inOrderTraverse(Consumer<T> consumer) {
        inOrderTraverse(consumer, root);
    }

    private void inOrderTraverse(Consumer<T> consumer, Node node) {
        if (node == null) return;

        inOrderTraverse(consumer, node.left);
        consumer.accept(node.value);
        inOrderTraverse(consumer, node.right);
    }

    /**
     * Execute the callback before visiting the node's descendants. Use case: displaying a structured document
     */
    public void preOrderTraverse(Consumer<T> consumer) {
        preOrderTraverse(consumer, root);
    }

    private void preOrderTraverse(Consumer<T> consumer, Node node) {
        if (node == null) return;

        consumer.accept(node.value);
        preOrderTraverse(consumer, node.left);
        preOrderTraverse(consumer, node.right);
    }

    /**
     * Execute the callback after visiting the node's descendents. Usage: calculating folders and subfolders
     * space on disc
     */
    public void postOrderTraverse(Consumer<T> consumer) {
        postOrderTraverse(consumer, root);
    }

    private void postOrderTraverse(Consumer<T> consumer, Node node) {
        if (node == null) return;

        postOrderTraverse(consumer, node.left);
        postOrderTraverse(consumer, node.right);
        consumer.accept(node.value);
    }

    public T min() {
        Node min = min(root);
        return min == null ? null : min.value;
    }

    private Node min(Node node) {
        Node current = node;

        while (current != null && current.left != null) {
            current = current.left;
        }

        return current;
    }

    public T max() {
        Node max = max(root);
        return max == null ? null : max.value;
    }

    private Node max(Node node) {
        Node current = node;

        while (current != null && current.right != null) {
            current = current.right;
        }

        return current;
    }

    public void remove(T value) {
        root = remove(value, root);
    }

    private Node remove(T value, Node node) {
        if (node == null || value == null) return null;

        if (value.compareTo(node.value) < 0) {
            node.left = remove(value, node.left);
            return node;
        }

        if (value.compareTo(node.value) > 0) {
            node.right = remove(value, node.right);
            return node;
        }

        // Found the node to be removed. Now we have 3 cases:

        // Case 1
        if (node.left == null && node.right == null)
            return null;

        // Case 2
        if (node.left == null)
            return node.right;

        if (node.right == null)
            return node.left;

        // Case 3
        Node aux = min(node.right);
        node.value = aux == null ? null : aux.value;
        node.right = remove(node.value, node.right);

        return node;
    }

    @Override
    public String toString() {
        return "{" +
                "\"root\":" + root +
                '}';
    }

    private class Node {
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("{\"value\":" + value);

            if (left != null)
                sb.append(", \"left\":").append(left);

            if (right != null)
                sb.append(", \"right\":").append(right);

            sb.append('}');

            return sb.toString();
        }
    }

}
