import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class LinkedListChallengeTest {

    static class Customer {
        private final String name;

        public Customer(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Customer customer = (Customer) o;
            return Objects.equals(name, customer.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public String toString() {
            return "Customer " + this.name;
        }
    }

    LinkedListChallenge<Customer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new LinkedListChallenge<>();
    }

    @Test
    void shouldAddItem() {
        linkedList.add(new Customer("cliente teste"));
        linkedList.add(new Customer("cliente teste2"));
        Assertions.assertEquals(2, linkedList.size());
    }

    @Test
    void shouldNotAllowedAddDuplicateItem() {
        linkedList.add(new Customer("Cliente1"));
        linkedList.add(new Customer("Cliente2"));
        Assertions.assertEquals(2, linkedList.size());
        linkedList.add(new Customer("Cliente1"));
        Assertions.assertEquals(2, linkedList.size());
    }

    @Test
    void shouldRemoveItem() {
        linkedList.add(new Customer("Cliente1"));
        Assertions.assertEquals(1, linkedList.size());
        linkedList.remove(new Customer("Cliente1"));
        Assertions.assertTrue(linkedList.isEmpty());
    }

    @Test
    void shouldNotRemoveItem() {
        linkedList.add(new Customer("Cliente1"));
        Assertions.assertEquals(1, linkedList.size());
        linkedList.remove(new Customer("Cliente2"));
        Assertions.assertEquals(1, linkedList.size());
    }

    @Test
    void shouldCreateAndRemoveItemsUntilEmpty() {
        linkedList.add(new Customer("Cliente1"));
        linkedList.add(new Customer("Cliente2"));
        linkedList.add(new Customer("Cliente3"));
        linkedList.add(new Customer("Cliente4"));
        linkedList.add(new Customer("Cliente5"));
        linkedList.remove(new Customer("Cliente3"));
        Assertions.assertEquals(linkedList.firstValue(), new Customer("Cliente1"));
        linkedList.remove(new Customer("Cliente5"));
        Assertions.assertEquals(linkedList.firstValue(), new Customer("Cliente1"));
        linkedList.remove(new Customer("Cliente1"));
        Assertions.assertEquals(linkedList.firstValue(), new Customer("Cliente2"));
        linkedList.remove(new Customer("Cliente2"));
        Assertions.assertEquals(linkedList.firstValue(), new Customer("Cliente4"));
        linkedList.remove(new Customer("Cliente4"));
        Assertions.assertEquals(linkedList.firstValue(), null);
        Assertions.assertEquals(linkedList.size(), 0);
    }


}
