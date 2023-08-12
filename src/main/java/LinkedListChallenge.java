/**
 * Implementacao base de lista
 * 1 - Permitir adicionar itens na lista
 * 2 - Não permitir adicionar valores na lista repetidos
 * 3 - Permitir remover itens da lista
 */
public class LinkedListChallenge<V> {
    private ListNode<V> first;

    public static class ListNode<V> {
        private ListNode<V> previous;
        private V value;
        private ListNode<V> next;

        public ListNode(ListNode<V> previous, V value, ListNode<V> next) {
            this.previous = previous;
            this.value = value;
            this.next = next;
        }
    }


    public void add(V value) {
        if (this.isEmpty()) {
            this.first = new ListNode<V>(null, value, null);
        } else {
            var isEqual = false;
            var last = this.first;
            do {
                if (last != null) {
                    if (!(last.value.toString().equals(value.toString()))) {
                        last = last.next;
                    } else {
                        isEqual = true;
                        break;
                    }
                } else {
                    break;
                }
            } while (true);
            last = this.first;
            do {
                if (!isEqual) {
                    if (last.next == null) {
                        last.next = new ListNode<V>(last, value, null);
                        break;
                    } else {
                        last = last.next;
                    }
                } else {
                    break;
                }
            } while (true);

        }
    }


    public int size() {
        if (this.first == null) {
            return 0;
        }
        var cont = 1;
        var last = this.first.next;
        while (last != null) {
            cont++;
            last = last.next;
        }
        return cont;
    }

    public void remove(V value) {
        if (hasValue(value)) {
            //   null<-[1]->null
            if (this.first.next == null) {
                this.first = null;
            }
            var valueToRemove = this.first;
            while (valueToRemove != null) {
                if (valueToRemove.value.toString().equals(value.toString())) {
                    if (valueToRemove.previous == null) {
                        this.first = valueToRemove.next;
                        this.first.previous = null;
                        break;
                    } else if (valueToRemove.next == null) {
                        valueToRemove.previous.next = null;
                        valueToRemove.previous = null;
                        break;
                    } else {
                        valueToRemove.previous.next = valueToRemove.next;
                        valueToRemove.next.previous = valueToRemove.previous;
                        break;
                    }
                }
                valueToRemove = valueToRemove.next;
            }
        }
    }

    private boolean hasValue(Object value) {
        if (!this.isEmpty()) {
            var last = this.first;
            while (last != null) {
                if (last.value.toString().equals(value.toString())) {
                    return true;
                }
                last = last.next;
            }
        }
        return false;
    }

    public V firstValue() {
        V value = null;
        if (this.first != null) {
            value = this.first.value;
        }
        return value;
    }

    public boolean isEmpty() {
        return this.first == null && this.size() == 0;
    }

}
