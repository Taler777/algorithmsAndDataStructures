package HomeWork5;

public class Stack<E> {
    private E number[] = (E[]) new Object[100];
    ;
    private int pointer = -1; // указатель на вершину стека

    public E getNumber() {
        try{
        E temp;
        temp = number[this.pointer];
        number[pointer] = null; // подчищаю стек
        pointer--;
        return temp;}
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Стек либо пуст, либо мы вышли за его пределы");
        }
        return null;
    }

    public void printStack() {
        while (pointer > -1) {
            System.out.print(getNumber());
        }
    }

    public void setNumber(E num) {
        pointer++;
        number[pointer] = num;
    }
}

