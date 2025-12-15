package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Domain.Customer;


public class CustomerRepository {
    private final Map<String, Customer> customerById = new HashMap<>();

    public List<Customer> findAll() {

        return new ArrayList<>(customerById.values());
    }

    public void save(Customer c) {
        customerById.put(c.getId(), c);


    }



}