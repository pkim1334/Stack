public class Stack<Type> implements StackInterface<Type>{

  Node top;

  class Node{

    Node next;
    Type node_value;

    public Node(Type val){
      node_value = val;
      next = null;
    }

  }


  public void push(Type num){
    Node temp = new Node(num);

    if(isEmpty()){
      top = temp;
    }else {
      temp.next = top;
      top = temp;
    }
  }

  public Type pop(){
    Type temp;

    if(isEmpty()){
      return null;
    }else if(top.next == null){
      temp = top.node_value;
      top = null;
      return temp;
    }else {
      temp = top.node_value;
      top = top.next;
      return temp;
    }
  }

  public Type peek(){
    if(isEmpty()){
      return null;
    }else{
      return top.node_value;
    }
  }

  public boolean isEmpty(){
    if (top == null) {
      return true;
    }
    else {
      return false;
    }
  }

  public boolean isFull(){
    return false;
  }


}
