/*************************************************************************
* Assignment 6 for CSCI 271 Spring 2026
*
* Author: Mason Snyder
* OS: Windows/GitBash
* Compiler: javac
* Date: 4/29/2026
*
* Purpose
* This program implements a Generic Binary Search Tree (BinaryTree).
* The program allows the user to insert, delete, search, display,
* and compute properties of the tree such as size, height, minimum,
* and maximum values.
*
* Algorithm
* Binary Search Tree recursive algorithms are used for insertion,
* searching, deletion, and traversal.
*
* Data Structures
* Node<T> linked structure representing the Binary Search Tree.
*************************************************************************/

/*******************************************************************
* I declare and confirm the following:
* - I have not discussed this program code with anyone other than my
* instructor or the teaching assistants assigned to this course.
* - I have not used programming code obtained from someone else,
* or any unauthorised sources, including the Internet, either
* modified or unmodified.
* - If any source code or documentation used in my program was
* obtained from other sources, like a text book or course notes,
* I have clearly indicated that with a proper citation in the
* comments of my program.
* - I have not designed this program in such a way as to defeat or
* interfere with the normal operation of the supplied grading code.
*
* Mason Snyder
********************************************************************/

// Java Program to Implement a Generic Binary Tree

// Importing all input output classes
import java.io.*;
import java.util.Scanner;
import java.lang.Math; // we need this for max and min functions.

// BinaryTree Class: ( Generic BinaryTree class)
//                   T extends Comparable<T> to allow the use of compareTo() to compare element generically !!
class BinaryTree<T extends Comparable<T>> { 

  // Encapsulation: Node is a private nested class to hide implementation details.
  private static class Node<T> {
    T element;     // Data element to sore an item
    Node<T> left;  // a reference to the left subtree
    Node<T> right; // a reference to the right subtree

    // Parameterized constructor to assign a value
    Node(T item) {
      this.element = item; // "this" refers to current object itself
      this.right = null;
      this.left = null;
    }
  }

  // Generic Node instance in the Generic BinaryTree Class
  private Node<T> root;

  BinaryTree(){ // Default constructor
    this.root = null; // a reference to the root node
  } 

  // ********************************** isEmpty() **********************************
  public boolean isEmpty(){ // a method to check if the tree is empty
    return (this.root == null);
  }

  // ********************************** size() **********************************
  //
  // STUDENTS MUST IMPLEMENT THIS METHOD!!!!

  /***************************** size *****************************
  * Description: Returns the total number of nodes currently
  *              stored in the binary tree.
  *
  * Parameters: none
  *
  * Pre: The tree may be empty or contain elements.
  *
  * Post: The total number of nodes in the tree is returned.
  *
  * Returns: int representing the number of nodes in the tree.
  *
  * Called by: displayAll()
  *
  * Calls: sizeRecursively()
  ********************************************************************/
  public int size(){ 
    return sizeRecursively(this.root);
  }

  // size recursively to help
  private int sizeRecursively(Node<T> root){
    if(root == null)
      return 0;
    return 1 + sizeRecursively(root.left) + sizeRecursively(root.right);
  }

  // ********************************** height() **********************************
  //
  // this is a public method that delegates the height calculation to a private, recursive method
  public int height(){
    return heightRecursively(this.root);
  }
 
  // a private method to recursively calculate the height 
  private int heightRecursively(Node<T> root){
    int h = -1;
    if(root!=null)
      h = Math.max(heightRecursively(root.left),heightRecursively(root.right)) + 1;
    return h;
  }

  // ********************************** insert() **********************************
  //
  // this is a public method that delegates the insertion process to a private, recursive method
  public void insert(T item){ 

    this.root = insertRecursively(this.root, item);
  }

  // a private method to recursively place the item in the tree
  private Node<T> insertRecursively(Node<T> root, T item){

    if(root == null){
      root = new Node<T>(item);
    }else{
      int r = item.compareTo(root.element);
      if(r < 0){
        root.left = insertRecursively(root.left, item);
      }else if(r > 0){
        root.right = insertRecursively(root.right, item);
      }else{
        System.out.print(item+" already exists in the tree, no duplicates allowed!!\n");
      }
    }
    return root;
  }

  // ********************************** displayAll() **********************************

  // this is a public method that delegates the display process to a private, recursive method
  public void displayAll(){ 
    System.out.println("displayAll():");
    if (this.isEmpty()){
      System.out.println("The tree is empty ...! ");
    }else{
      System.out.println("____________Tree of "+this.size()+" items________________");
      showInorder(this.root);
    }
    System.out.println();
  }

  // a private method to recursively display items in the tree inOrder
  private void showInorder(Node<T> root){
    if(root!=null){
      showInorder(root.left);
      System.out.print(root.element+" ");
      showInorder(root.right);
    }  
  }

  // ********************************** Find(T item) **********************************
  //
  // STUDENTS MUST IMPLEMENT THIS METHOD!!!!

  /***************************** find *****************************
  * Description: Searches the binary tree for a specific item.
  *
  * Parameters:
  *   item (input) – the value being searched in the tree.
  *
  * Pre: The tree may be empty or contain elements.
  *
  * Post: Returns true if the item exists in the tree,
  *       otherwise returns false.
  *
  * Returns: boolean
  *
  * Called by: main()
  *
  * Calls: findRecursively()
  ********************************************************************/
  public boolean find(T item){
    return findRecursively(this.root, item);
  }

  // help recursive method
  private boolean findRecursively(Node<T> root, T item){

    if(root == null)
      return false;

    int r = item.compareTo(root.element);

    if(r == 0)
      return true;
    else if(r < 0)
      return findRecursively(root.left, item);
    else
      return findRecursively(root.right, item);
  }

  // ********************************** getMax() **********************************

  /***************************** getMax *****************************
  * Description: Returns the largest value currently stored in the tree.
  *
  * Parameters: none
  *
  * Pre: Tree must not be empty.
  *
  * Post: The largest element in the tree is returned.
  *
  * Returns: T (maximum element)
  *
  * Called by: main()
  *
  * Calls: getMaxRecursively()
  ********************************************************************/
  public T getMax(){
    return getMaxRecursively(this.root);
  }

  private T getMaxRecursively(Node<T> root){
    if(root.right == null)
      return root.element;
    return getMaxRecursively(root.right);
  }

  // ********************************** getMin() **********************************

  /***************************** getMin *****************************
  * Description: Returns the smallest value currently stored in the tree.
  *
  * Parameters: none
  *
  * Pre: Tree must not be empty.
  *
  * Post: The smallest element in the tree is returned.
  *
  * Returns: T (minimum element)
  *
  * Called by: main()
  *
  * Calls: getMinRecursively()
  ********************************************************************/
  public T getMin(){
    return getMinRecursively(this.root);
  }

  // ********************************** delete(T item) **********************************

  // this is a public method that delegates the deletion process to a private, recursive method
  public void delete(T item){
    this.root = deleteRecursively(this.root, item);
  }

  // a private method to recursively delete item from the tree
  private Node<T> deleteRecursively(Node<T> root, T item){

    if(root!=null){
      int r = item.compareTo(root.element);

      if(r < 0)
        root.left = deleteRecursively(root.left, item);
      else if(r > 0)
        root.right = deleteRecursively(root.right, item);
      else{

        //case 1: the root node is a leaf node
        if(root.left==null && root.right==null){
          root = null; // delete the current node safely

        // case 2: the root node has one child (on left then on right)
        }else if(root.right==null)
          root = root.left;
        else if(root.left==null)
          root = root.right;
   
        // case 3: the root node has two children
        else {
          T minItem = getMinRecursively(root.right);
          root.element = minItem;
          root.right = deleteRecursively(root.right,minItem);
        }
        System.out.println(item+" was deleted form the tree.");
      }
    }
    return root;
  }

  private T getMinRecursively(Node<T> root){
    T item = null;
    if(root.left==null)
      item = root.element;
    else
      item = getMinRecursively(root.left);
    return item;
  }
}

// The class for the Main Program
public class assignment6 {

  public static void main(String[] args) { // The main() method

    BinaryTree<Integer> tree = new BinaryTree<>();
    Integer ch, item, index;
    boolean quit = false;

    Scanner sc = new Scanner(System.in);

    do{
  
      System.out.println( "____________Main Menu_____________________");
      System.out.println( "select option :");
      System.out.println( "1: insert item");
      System.out.println( "2: find item");
      System.out.println( "3: tree height");
      System.out.println( "4: display items");
      System.out.println( "5: delete item");
      System.out.println( "6: get maximum value");
      System.out.println( "7: get minimum value");
      System.out.println( "8: tree size");
      System.out.println( "9: exit");
      ch = sc.nextInt();

      switch (ch) {
        case 1:
          System.out.println( "enter item to insert:");
          item = sc.nextInt(); // read in an integer
          tree.insert(item);
          break;
        case 2:
          System.out.println( "enter item to find:");
          item = sc.nextInt(); // read in an integer
          if(tree.find(item))
            System.out.println(item+" is found in the tree!");
          else
            System.out.println("Sorry... "+item+" is NOT found in the tree!");
          break;
        case 3:
          System.out.println("The height of the tree is: "+tree.height());
          break;
        case 4:
          tree.displayAll();
          break;
        case 5:
          System.out.println( "enter item to delete:");
          item = sc.nextInt(); 
          tree.delete(item);
          break;
        case 6:
          System.out.println("Maximum value: "+tree.getMax());
          break;
        case 7:
          System.out.println("Minimum value: "+tree.getMin());
          break;
        case 8:
          System.out.println("Tree size: " + tree.size());
          break;
        case 9:
          quit = true;
          System.out.println( "Goodbye!!");
          break;
        default:
          System.out.println( "invalid selection");
      }
    } while(!quit);
  }
}