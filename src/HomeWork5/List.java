package HomeWork5;
class List<E> {
    private Node head;       // указатель на первый элемент
    private Node tail;       // указатель последний элемент

    void addBack(E data) {          //добавление в конец списка
        Node a = new Node();  //создаём новый элемент
        a.data = data;
        if (tail == null)           //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            head = a;               //т.е. список теперь состоит из одного элемента
            tail = a;
        } else {
            tail.next = a;          //иначе "старый" последний элемент теперь ссылается на новый
// закомментировал проверки
//            System.out.println("Проход "+a.data);
//            System.out.println("tail = "+tail);
//            System.out.println("tail.next = "+tail.next);
            tail = a;               //а в указатель на последний элемент записываем адрес нового элемента
//            System.out.println("После смены указателя в tail:____________");
//            System.out.println("tail = "+tail);
//            System.out.println("tail.next = "+tail.next);
        }
    }

    void printList()                //печать списка
    {
        Node t = head;       //получаем ссылку на первый элемент
        while (t != null)           //пока элемент существуе
        {
            System.out.print(t.
                    data + " "); //печатаем его данные
            t = t.next;                     //и переключаемся на следующий
        }
    }
}