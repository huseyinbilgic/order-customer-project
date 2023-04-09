package com.ordercustomer.ordercustomerproject.service.impl;

import com.ordercustomer.ordercustomerproject.entities.Customer;
import com.ordercustomer.ordercustomerproject.errors.CustomerException;
import com.ordercustomer.ordercustomerproject.errors.NotFoundException;
import com.ordercustomer.ordercustomerproject.repo.CustomerRepo;
import com.ordercustomer.ordercustomerproject.requests.CustomerRequest;
import com.ordercustomer.ordercustomerproject.responses.CustomerResponse;
import com.ordercustomer.ordercustomerproject.responses.OrderResponse;
import com.ordercustomer.ordercustomerproject.responses.SearchCustomerNameContainingResponse;
import com.ordercustomer.ordercustomerproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerResponse> customerResponses = customers.stream().map(customer -> {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setName(customer.getName());
            customerResponse.setAge(customer.getAge());
            customerResponse.setOrderResponses(customer.getOrders().stream().map(order -> {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setTotalPrice(order.getTotalPrice());
                orderResponse.setCreatedDate(order.getCreatedDate());
                return orderResponse;
            }).toList());
            return customerResponse;
        }).toList();
        return customerResponses;
    }

    @Override
    public CustomerResponse findById(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        CustomerResponse customerResponse;
        if (customer.isPresent()) {
            customerResponse = new CustomerResponse();
            customerResponse.setId(customer.get().getId());
            customerResponse.setName(customer.get().getName());
            customerResponse.setAge(customer.get().getAge());
            customerResponse.setOrderResponses(customer.get().getOrders().stream().map(order -> {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setTotalPrice(order.getTotalPrice());
                orderResponse.setCreatedDate(order.getCreatedDate());
                return orderResponse;
            }).toList());
        } else {
            throw new NotFoundException(id +" idye sahip customer bulunamadı");
        }
        return customerResponse;
    }

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) { //hata yazılacak
        if ((customerRequest.getName()==null|| customerRequest.getName().trim()=="")
        ||(customerRequest.getAge()==null|| customerRequest.getAge()<1)){
            throw new CustomerException("name ve age alanı boş geçilemez ve age alanı 0'dan büyük olmalıdır");
        }
        Customer customer = new Customer();
        customer.setName(customerRequest.getName().trim());
        customer.setAge(customerRequest.getAge());
        customerRepo.save(customer);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setName(customer.getName());
        customerResponse.setAge(customer.getAge());
        customerResponse.setOrderResponses(customer.getOrders()==null?
                new ArrayList<>():
                customer.getOrders().stream().map(order -> {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setTotalPrice(order.getTotalPrice());
            orderResponse.setCreatedDate(order.getCreatedDate());
            return orderResponse;
        }).toList());
        return customerResponse;
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerOpt = customerRepo.findById(id);
        CustomerResponse customerResponse;
        if (customerOpt.isPresent()) {
            if ((customerRequest.getName()!=null&& customerRequest.getName().trim()=="")
                    ||(customerRequest.getAge()!=null&& customerRequest.getAge()<1)){
                throw new CustomerException("name ve age alanı boş geçilemez ve age alanı 0'dan büyük olmalıdır");
            }

            Customer customer = customerOpt.get();
            customer.setName(customerRequest.getName()==null?customer.getName():customerRequest.getName().trim());
            customer.setAge(customerRequest.getAge()==null?customer.getAge():customerRequest.getAge());
            customerRepo.save(customer);

            customerResponse = new CustomerResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setName(customer.getName());
            customerResponse.setAge(customer.getAge());
            customerResponse.setOrderResponses(customer.getOrders().stream().map(order -> {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setTotalPrice(order.getTotalPrice());
                orderResponse.setCreatedDate(order.getCreatedDate());
                return orderResponse;
            }).toList());

        } else {
            throw new NotFoundException(id+" idye sahip customer bulunamadı");
        }
        return customerResponse;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Customer> customer=customerRepo.findById(id);
        if (customer.isPresent()){
            customerRepo.deleteById(id);
        }
        else {
            throw new NotFoundException(id+" idye sahip customer bulunamadı");
        }

    }

    @Override
    public List<SearchCustomerNameContainingResponse> searchByContainsName(String name) {
        List<Customer> customers=customerRepo.findByNameContaining(name);
        List<SearchCustomerNameContainingResponse> customerResponses = customers.stream().map(customer -> {
            SearchCustomerNameContainingResponse customerResponse = new SearchCustomerNameContainingResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setName(customer.getName());
            customerResponse.setAge(customer.getAge());
            customerResponse.setOrderIds(customer.getOrders().stream().map(order -> order.getId()).toList());
            return customerResponse;
        }).toList();
        return customerResponses;
    }

    @Override
    public List<CustomerResponse> findAllOrdersIsEmpty() {
        List<Customer> customers=customerRepo.findAllByOrdersIsEmpty();
        List<CustomerResponse> customerResponses = customers.stream().map(customer -> {
            CustomerResponse customerResponse = new CustomerResponse();
            customerResponse.setId(customer.getId());
            customerResponse.setName(customer.getName());
            customerResponse.setAge(customer.getAge());
            customerResponse.setOrderResponses(customer.getOrders().stream().map(order -> {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setTotalPrice(order.getTotalPrice());
                orderResponse.setCreatedDate(order.getCreatedDate());
                return orderResponse;
            }).toList());
            return customerResponse;
        }).toList();
        return customerResponses;
    }
}
