package com.ari;

import com.ari.structures.BinarySearchTree;
import com.ari.structures.HashTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Bynary search tree: ");
        binarySearchTreeExample();

        System.out.println();
        System.out.println("Hash table: ");
        hashTableExample();
    }

    private static void binarySearchTreeExample() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(1);
        tree.add(2);
        tree.add(10);
        tree.add(14);
        tree.add(18);
        tree.add(7);
        tree.add(19);

        System.out.println(tree);
        System.out.println("19: " + tree.has(19));
        System.out.println("34: " + tree.has(34));
        System.out.println("7: " + tree.has(7));
        System.out.println("1: " + tree.has(1));

        System.out.println("100: " + tree.has(100));
        tree.add(100);
        System.out.println("100: " + tree.has(100));

        // In order traverse
        System.out.println("\nIn order traverse: ");

        List<Integer> list = new ArrayList<>();
        tree.inOrderTraverse(list::add);
        System.out.println("[ " + list.stream().map(Object::toString).collect(Collectors.joining(", ")) + " ]");

        // Pre order traverse
        System.out.println("\nPre order traverse: ");

        list = new ArrayList<>();
        tree.preOrderTraverse(list::add);
        System.out.println("[ " + list.stream().map(Object::toString).collect(Collectors.joining(", ")) + " ]");

        // Post order traverse
        System.out.println("\nPost order traverse: ");

        list = new ArrayList<>();
        tree.postOrderTraverse(list::add);
        System.out.println("[ " + list.stream().map(Object::toString).collect(Collectors.joining(", ")) + " ]");

        System.out.println();
        System.out.println("Min value: " + tree.min());
        System.out.println("Max value: " + tree.max());

        System.out.println();
        System.out.println("Remove value 10");
        System.out.println("Has 10? " + tree.has(10));

        tree.remove(10);

        list = new ArrayList<>();
        tree.inOrderTraverse(list::add);
        System.out.println("[ " + list.stream().map(Object::toString).collect(Collectors.joining(", ")) + " ]");
        System.out.println("Has 10? " + tree.has(10));
    }

    private static void hashTableExample() {
        HashTable<String, String> table = new HashTable<>();
        table.put("game", "The Last of Us 2");
        table.put("movie", "Back to the Future");

        System.out.println("Favorite game: " + table.get("game"));
        System.out.println("Favorite movie: " + table.get("movie"));

        System.out.println("\nReplace key");
        table.put("game", "Red Dead Redemption 2");
        System.out.println("Favorite game: " + table.get("game"));

        System.out.println("\nInteger key table");
        HashTable<Integer, String> intTable = new HashTable<>();
        intTable.put(1, "One");
        intTable.put(2, "Two");
        intTable.put(Integer.MAX_VALUE, "Very long number");

        System.out.println(intTable.get(1));
        System.out.println(intTable.get(Integer.MAX_VALUE));

        intTable.remove(1);
        intTable.remove(Integer.MAX_VALUE);
        System.out.println("Removed key '1': " + intTable.get(1));
        System.out.println("Removed key 'Integer.MAX_VALUE': " + intTable.get(Integer.MAX_VALUE));
    }

}
