/*
* File: BST.java
* Author: John Kucera
* Date: February 19, 2020
* Purpose: This Java program is meant to accompany P3GUI.java and is
* responsible for holding a Binary Search Tree constructor. In addition,
* it holds/uses a constructor for a BST node. The program also holds methods for
* inserting nodes into the BST, in-order traversal in the BST, and returning 
* ascending OR descending order of given values.
*/

// BST class
public class BST<T extends Comparable<T>> {
    
    // Instance Variables
    private StringBuilder sorted = new StringBuilder();
    private BSTnode rootNode = null;
    
    // BSTnode class
    public class BSTnode {
        
        // BSTnode variables
        private T value;
        private BSTnode left;
        private BSTnode right;
        
        // BSTnode constructor
        BSTnode(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
        // BSTnode methods
        BSTnode getLeft() {
            return left;
        }
        BSTnode getRight() {
            return right;
        } // end of method
    } // end of class

    // Default BST Constructor
    public BST() {
        rootNode = null;
        sorted.setLength(0);
    } // end of constructor
    
    // insertNode method: first node becomes root, else insert node into BST
    public void insertNode(T value) {
        if (rootNode == null) {
            rootNode = new BSTnode(value);
        }
        else {
            insertNodeRecursive(value, rootNode);
        } // end of else
    } // end of method
    
    // insertNodeRecursive method: comparing current value to previous node
    //                             to determine placement
    private void insertNodeRecursive(T currentValue, BSTnode previousNode) {
        // IF this value is <= previous node, then recurse
        if (currentValue.compareTo(previousNode.value) <= 0) {
            if (previousNode.left != null) {
                insertNodeRecursive(currentValue, previousNode.left);
            }
            // place node on left once left node is null
            else {
                previousNode.left = new BSTnode(currentValue);
            }
        }
        
        // ELSE IF this value is > previous node, then recurse
        else if (currentValue.compareTo(previousNode.value) > 0) {
            if (previousNode.right != null) {
                insertNodeRecursive(currentValue, previousNode.right);
            }
            // place node on right once right node is null
            else {
                previousNode.right = new BSTnode(currentValue);
            } // end of inner else
        } // end of else if
    } // end of method
    
    // inOrderTraversal: create string from inOrder traversal through BST
    public void inOrderTraversal(BSTnode currentRoot) {
        // initiate in-order traversal below root of current subtree
        if (currentRoot.value != null) {
            // start with left child
            if (currentRoot.getLeft() != null) {
                inOrderTraversal(currentRoot.getLeft());
            }
            // append string of parent node
            String parent = (currentRoot.value).toString();
            sorted.append(parent).append(" ");
            // continue with right child
            if (currentRoot.getRight() != null) {
                inOrderTraversal(currentRoot.getRight());
            } // end of inner if
        } // end of outer if
    } // end of method
    
    // Method to return string of values in ascending order
    public String getAscend() {
        inOrderTraversal(rootNode);
        String ascendString = sorted.toString();
        return ascendString;
    } // end of method
    
    // Method to return string of values in descending order
    public String getDescend() {
        getAscend();
        // turn into array
        String[] numbers = sorted.toString().split(" ");
        sorted.setLength(0);

        // reverse ascending order of values into descending order
        for (int i = numbers.length - 1; i >= 0; i--) {
            sorted.append(numbers[i]).append(" ");
        }
        String descendString = sorted.toString();
        return descendString;
    } // end of method
} // end of class
