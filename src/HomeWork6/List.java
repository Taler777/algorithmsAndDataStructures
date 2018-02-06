package HomeWork6;
class List<E> {
    private Node head;       // указатель на первый элемент
    private Node tail;       // указатель последний элемент
    int tn = 1;                // начальное значение табельного номера

    void addBack(E fn, E sn, E age) {          //добавление в конец списка
        Node a = new Node();  //создаём новый элемент
        a.fio = fn + " " + sn;
        a.age = age;
        a.tabelNumber = tn++;
        if (tail == null)           //если список пуст
        {                           //то указываем ссылки начала и конца на новый элемент
            head = a;               //т.е. список теперь состоит из одного элемента
            tail = a;
        } else {
            tail.parent = a;          //иначе "старый" последний элемент теперь ссылается на новый
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
            System.out.print(t.tabelNumber + " " + t.fio + " " + t.age); //печатаем его данные
            t = t.parent;                     //и переключаемся на следующий
        }
    }

    public void search(E age) {
        Node t = head;       //получаем ссылку на первый элемент
        while (t != null)           //пока элемент существует
        { // если студенту age лет, тогда
            if (t.age == age) System.out.println(t.tabelNumber + " " + t.fio + " " + t.age); //печатаем его данные
            t = t.parent;                     //и переключаемся на следующий
        }
    }
}