import java.util.Scanner;

class node {

  node left, right;
  int data;

  // красный ==> true, черный ==> false
  boolean color;

  node(int data) {
    this.data = data;
    left = null;
    right = null;

// Новый узел, который создается, является всегда красного цвета.
    color = true;
  }
}  


public class RedBlackTree {
  private static node root = null;

  // Метод для поворота узла против часовой стрелки.
  node rotateLeft(node myNode) {
    System.out.printf("Поворот влево!!\n");
    node child = myNode.right;
    node childLeft = child.left;

    child.left = myNode;
    myNode.right = childLeft;

    return child;
  }

  // Метод для поворота узла по часовой стрелке.
  node rotateRight(node myNode) {
    System.out.printf("Вращение вправо\n");
    node child = myNode.left;
    node childRight = child.right;

    child.right = myNode;
    myNode.left = childRight;

    return child;
  }

  // Метод для проверки того, является ли узел красного цвета или нет.
  boolean isRed(node myNode) {
    if (myNode == null) {
      return false;
    }
    return (myNode.color == true);
  }

  // Метод для изменения цвета двух узлы.
  void swapColors(node node1, node node2) {
    boolean temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
  }

  // вставка в левостороннее Красно-черное дерево.
  node insert(node myNode, int data) {
// Обычный код вставки для любого двоичного файла
    if (myNode == null) {
      return new node(data);
    }
    
    if (data < myNode.data) {
      myNode.left = insert(myNode.left, data);
    } else if (data > myNode.data) {
      myNode.right = insert(myNode.right, data);
    } else {
      return myNode;
    }

    // когда правый дочерний элемент красный, а левый дочерний элемент черный или не существует.
    if (isRed(myNode.right) && !isRed(myNode.left)) {
// Повернуть узел  влево
      myNode = rotateLeft(myNode);

// Поменять местами цвета дочернего узла всегда должен быть красным
      swapColors(myNode, myNode.left);
    }

    // когда левый ребенок, а также левый внук выделены красным цветом
    if (isRed(myNode.left) && isRed(myNode.left.left)) {
// Повернуть узел в право
      myNode = rotateRight(myNode);
      swapColors(myNode, myNode.right);
    }

    // когда и левый, и правый дочерние элементы окрашены в красный цвет.
    if (isRed(myNode.left) && isRed(myNode.right)) {
// Инвертировать цвет узла это левый и правый дети.
      myNode.color = !myNode.color;

// Изменить цвет на черный.
      myNode.left.color = false;
      myNode.right.color = false;
    }

    return myNode;
  }

  // Обход по порядку
  void inorder(node node) {
    if (node != null)
    {
      inorder(node.left);
      char c = 'b';
      if (node.color == false)
        c = 'r';
      System.out.print(node.data + ""+c+" ");
      inorder(node.right);
    }
  }

  public static void main(String[] args) {

    RedBlackTree node = new RedBlackTree();
    Scanner scanner = new Scanner(System.in);
    char ch;
      do {
        System.out.println("Введите целое число:");

        int num = scanner.nextInt();
        root = node.insert(root, num);

        node.inorder(root);
        System.out.println("\n Вы хотите продолжить? (Да - 1 ; Нет - 2)");
        ch = scanner.next().charAt(0);
      } while (ch == '1' || ch == 'y'); 
    }
  
} 