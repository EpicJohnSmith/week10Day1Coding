package node;

/**
 * A singly linked list implementation in Java
 * This class provides various operations to manipulate a linked list
 * including insertion, deletion, searching, and sorting
 */
public class LinkedList<T extends Comparable<T>> {
    
    /**
     * Private inner class Node
     * Represents a single node in the linked list
     */
    private class Node {
        T data;           // Data stored in the node
        Node next;        // Reference to the next node
        
        /**
         * Constructor to create a new node
         * @param data The data to store in the node
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;    // Reference to the first node in the list
    private int size;     // Keeps track of the number of nodes
    
    /**
     * Constructor to initialize an empty linked list
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Adds a new node containing value to the end of the list
     * @param value The value to append
     */
    public void append(T value) {
        Node newNode = new Node(value);
        
        // If list is empty, make the new node the head
        if (head == null) {
            head = newNode;
        } else {
            // Traverse to the last node
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            // Link the last node to the new node
            current.next = newNode;
        }
        size++;
    }
    
    /**
     * Adds a new node containing value to the beginning of the list
     * @param value The value to prepend
     */
    public void prepend(T value) {
        Node newNode = new Node(value);
        
        // Point the new node's next to the current head
        newNode.next = head;
        // Make the new node the head
        head = newNode;
        size++;
    }
    
    /**
     * Inserts a new node with value after the node containing target
     * @param target The value after which to insert
     * @param value The value to insert
     */
    public void insertAfter(T target, T value) {
        Node current = head;
        
        // Search for the target node
        while (current != null) {
            if (current.data.equals(target)) {
                Node newNode = new Node(value);
                // Insert the new node after the current node
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
        }
        
        System.out.println("Target value '" + target + "' not found in the list.");
    }
    
    /**
     * Inserts a new node with value before the node containing target
     * @param target The value before which to insert
     * @param value The value to insert
     */
    public void insertBefore(T target, T value) {
        // If list is empty
        if (head == null) {
            System.out.println("List is empty. Cannot insert before.");
            return;
        }
        
        // If target is the head, prepend the value
        if (head.data.equals(target)) {
            prepend(value);
            return;
        }
        
        Node current = head;
        
        // Search for the node before the target
        while (current.next != null) {
            if (current.next.data.equals(target)) {
                Node newNode = new Node(value);
                // Insert the new node between current and current.next
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return;
            }
            current = current.next;
        }
        
        System.out.println("Target value '" + target + "' not found in the list.");
    }
    
    /**
     * Deletes the first node containing value
     * @param value The value to delete
     */
    public void delete(T value) {
        // If list is empty
        if (head == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }
        
        // If the head node contains the value to delete
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            return;
        }
        
        Node current = head;
        
        // Search for the node before the one to delete
        while (current.next != null) {
            if (current.next.data.equals(value)) {
                // Skip the node to delete
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
        
        System.out.println("Value '" + value + "' not found in the list.");
    }
    
    /**
     * Removes the last node of the list
     */
    public void remove() {
        // If list is empty
        if (head == null) {
            System.out.println("List is empty. Cannot remove.");
            return;
        }
        
        // If there's only one node
        if (head.next == null) {
            head = null;
            size--;
            return;
        }
        
        Node current = head;
        
        // Traverse to the second-to-last node
        while (current.next.next != null) {
            current = current.next;
        }
        
        // Remove the last node
        current.next = null;
        size--;
    }
    
    /**
     * Searches for a value in the list
     * @param value The value to search for
     * @return true if found, false otherwise
     */
    public boolean search(T value) {
        Node current = head;
        
        // Traverse the list to find the value
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    /**
     * Returns the total number of nodes in the list
     * @return The size of the list
     */
    public int length() {
        return size;
    }
    
    /**
     * Checks if the list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Sorts the linked list using Bubble Sort algorithm
     * This implementation uses Bubble Sort which works as follows:
     * 1. Compare adjacent nodes
     * 2. Swap their data if they are in wrong order
     * 3. Repeat the process for all nodes
     * 4. After each pass, the largest element "bubbles" to the end
     * 5. Continue until no more swaps are needed
     */
    public void sort() {
        // If list is empty or has only one element, it's already sorted
        if (head == null || head.next == null) {
            return;
        }
        
        boolean swapped;
        
        // Outer loop: Continue until no swaps are made in a complete pass
        do {
            swapped = false;
            Node current = head;
            
            // Inner loop: Traverse the list and compare adjacent nodes
            while (current.next != null) {
                // Compare current node's data with next node's data
                if (current.data.compareTo(current.next.data) > 0) {
                    // Swap the data of adjacent nodes if they are in wrong order
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    
                    // Mark that a swap occurred
                    swapped = true;
                }
                // Move to the next node
                current = current.next;
            }
            // If no swaps occurred in this pass, the list is sorted
        } while (swapped);
    }
    
    /**
     * Prints the contents of the linked list to the console
     */
    public void print() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        
        Node current = head;
        System.out.print("List: ");
        
        // Traverse and print each node
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * Main method to demonstrate and test the LinkedList
     */
    public static void main(String[] args) {
        // Create an instance of LinkedList with Integer type
        LinkedList<Integer> list = new LinkedList<>();
        
        System.out.println("=== Test Case 1: Check if list is empty ===");
        System.out.println("Is list empty? " + list.isEmpty());
        System.out.println("List length: " + list.length());
        list.print();
        
        System.out.println("\n=== Test Case 2: Append elements ===");
        list.append(10);
        list.append(20);
        list.append(30);
        list.append(40);
        System.out.println("After appending 10, 20, 30, 40:");
        list.print();
        System.out.println("List length: " + list.length());
        
        System.out.println("\n=== Test Case 3: Prepend element ===");
        list.prepend(5);
        System.out.println("After prepending 5:");
        list.print();
        
        System.out.println("\n=== Test Case 4: Insert after a target ===");
        list.insertAfter(20, 25);
        System.out.println("After inserting 25 after 20:");
        list.print();
        
        System.out.println("\n=== Test Case 5: Insert before a target ===");
        list.insertBefore(10, 8);
        System.out.println("After inserting 8 before 10:");
        list.print();
        
        System.out.println("\n=== Test Case 6: Search for elements ===");
        System.out.println("Search for 25: " + list.search(25));
        System.out.println("Search for 100: " + list.search(100));
        
        System.out.println("\n=== Test Case 7: Delete an element ===");
        list.delete(25);
        System.out.println("After deleting 25:");
        list.print();
        
        System.out.println("\n=== Test Case 8: Remove last element ===");
        list.remove();
        System.out.println("After removing last element:");
        list.print();
        
        System.out.println("\n=== Test Case 9: Add unsorted elements and sort ===");
        list.append(15);
        list.append(3);
        list.append(35);
        System.out.println("After adding 15, 3, 35:");
        list.print();
        
        list.sort();
        System.out.println("After sorting:");
        list.print();
        
        System.out.println("\n=== Test Case 10: Test with strings ===");
        LinkedList<String> stringList = new LinkedList<>();
        stringList.append("apple");
        stringList.append("orange");
        stringList.append("banana");
        System.out.println("String list before sorting:");
        stringList.print();
        
        stringList.sort();
        System.out.println("String list after sorting:");
        stringList.print();
        
        System.out.println("\n=== Test Case 11: Delete from single element list ===");
        LinkedList<Integer> singleList = new LinkedList<>();
        singleList.append(100);
        System.out.println("Single element list:");
        singleList.print();
        singleList.delete(100);
        System.out.println("After deleting the only element:");
        singleList.print();
        System.out.println("Is list empty? " + singleList.isEmpty());
        
        System.out.println("\n=== Test Case 12: Multiple operations ===");
        LinkedList<Integer> testList = new LinkedList<>();
        testList.append(50);
        testList.prepend(25);
        testList.append(75);
        testList.insertAfter(50, 60);
        testList.insertBefore(75, 70);
        System.out.println("After multiple insert operations:");
        testList.print();
        System.out.println("List length: " + testList.length());
    }
}