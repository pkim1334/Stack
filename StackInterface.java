public interface StackInterface<T>{
  public void push(T number);
  public T pop();
  public T peek();
  public boolean isEmpty();
  public boolean isFull();
}
