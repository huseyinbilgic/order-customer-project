package com.ordercustomer.ordercustomerproject.service.impl;

import com.ordercustomer.ordercustomerproject.entities.Customer;
import com.ordercustomer.ordercustomerproject.entities.Order;
import com.ordercustomer.ordercustomerproject.errors.CustomerException;
import com.ordercustomer.ordercustomerproject.errors.NotFoundException;
import com.ordercustomer.ordercustomerproject.errors.OrderException;
import com.ordercustomer.ordercustomerproject.repo.CustomerRepo;
import com.ordercustomer.ordercustomerproject.repo.OrderRepo;
import com.ordercustomer.ordercustomerproject.requests.OrderAfterCreationDateRequest;
import com.ordercustomer.ordercustomerproject.requests.OrderRequest;
import com.ordercustomer.ordercustomerproject.responses.CustomerResponse;
import com.ordercustomer.ordercustomerproject.responses.OrderResponse;
import com.ordercustomer.ordercustomerproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<OrderResponse> findAll() {
        List<Order> orders=orderRepo.findAll();
        List<OrderResponse> orderResponses=orders.stream().map(order -> {
            OrderResponse orderResponse=new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setTotalPrice(order.getTotalPrice());
            orderResponse.setCreatedDate(order.getCreatedDate());
            orderResponse.setCustomerResponse(new CustomerResponse(
                    order.getCustomer().getId(),
                    order.getCustomer().getName(),
                    order.getCustomer().getAge(),
                    new ArrayList<>() //tüm orderları getirdiğimiz için orderlarını getirmeyi gerek duymadım.
            ));
            return orderResponse;
        }).toList();
        return orderResponses;
    }

    @Override
    public OrderResponse findById(Long id) {
        Optional<Order> orderOpt=orderRepo.findById(id);
        OrderResponse orderResponse;
        if (orderOpt.isPresent()){
            orderResponse=new OrderResponse();
            orderResponse.setId(orderOpt.get().getId());
            orderResponse.setTotalPrice(orderOpt.get().getTotalPrice());
            orderResponse.setCreatedDate(orderOpt.get().getCreatedDate());
            orderResponse.setCustomerResponse(new CustomerResponse(
                    orderOpt.get().getCustomer().getId(),
                    orderOpt.get().getCustomer().getName(),
                    orderOpt.get().getCustomer().getAge(),
                    new ArrayList<>()
            ));
        }
        else{
            throw new NotFoundException(id+" idye sahip order bulunamadı");
        }
        return orderResponse;
    }

    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        OrderResponse orderResponse;
        if (orderRequest.getCustomerId()!=null){
            Optional<Customer> customer=customerRepo.findById(orderRequest.getCustomerId());
            if (customer.isPresent()){
                if (orderRequest.getTotalPrice()==null|| orderRequest.getTotalPrice().intValue()<0){
                    throw new CustomerException("totalPrice alanı boş veya negatif olamaz");
                }
                Order order=new Order();
                order.setTotalPrice(orderRequest.getTotalPrice());
                order.setCustomer(customer.get());
                orderRepo.save(order);

                orderResponse=new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setTotalPrice(order.getTotalPrice());
                orderResponse.setCreatedDate(order.getCreatedDate());
                orderResponse.setCustomerResponse(new CustomerResponse(
                        order.getCustomer().getId(),
                        order.getCustomer().getName(),
                        order.getCustomer().getAge(),
                        new ArrayList<>()
                ));
            }
            else{
                throw new NotFoundException(orderRequest.getCustomerId()+" idye ait customer bulunamadı");
            }
        }
        else{
            throw  new OrderException("customer id verilmesi gerekir.");
        }
        return orderResponse;
    }

    @Override
    public OrderResponse update(Long id, OrderRequest orderRequest) {
        Optional<Order> order=orderRepo.findById(id);
        OrderResponse orderResponse;
        if (order.isPresent()){
            if (orderRequest.getTotalPrice()!=null&& orderRequest.getTotalPrice().intValue()<0){
                throw new CustomerException("totalPrice alanı negatif değer olamaz");
            }
            Order orderData=order.get();
            orderData.setTotalPrice(orderRequest.getTotalPrice()!=null?orderRequest.getTotalPrice():orderData.getTotalPrice());
            orderRepo.save(orderData);

            orderResponse=new OrderResponse();
            orderResponse.setId(orderData.getId());
            orderResponse.setTotalPrice(orderData.getTotalPrice());
            orderResponse.setCreatedDate(orderData.getCreatedDate());
            orderResponse.setCustomerResponse(new CustomerResponse(
                    orderData.getCustomer().getId(),
                    orderData.getCustomer().getName(),
                    orderData.getCustomer().getAge(),
                    new ArrayList<>()
            ));
        }
        else{
            throw new NotFoundException(id+" idye sahip order bulunamadı");
        }
        return orderResponse;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Order> order=orderRepo.findById(id);
        if (order.isPresent()){
            orderRepo.deleteById(id);
        }
        else{
            throw new NotFoundException(id+" idye sahip order bulunamadı");
        }
    }
    @Override
    public List<OrderResponse> findAllAfterCreationDate(OrderAfterCreationDateRequest dateRequest) {
        List<Order> orders=orderRepo.findAllWithCreatedDateAfter(dateRequest.getDate());
        List<OrderResponse> orderResponses=orders.stream().map(order -> {
            OrderResponse orderResponse=new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setTotalPrice(order.getTotalPrice());
            orderResponse.setCreatedDate(order.getCreatedDate());
            orderResponse.setCustomerResponse(new CustomerResponse(
                    order.getCustomer().getId(),
                    order.getCustomer().getName(),
                    order.getCustomer().getAge(),
                    new ArrayList<>()
            ));
            return orderResponse;
        }).toList();
        return orderResponses;
    }
}
