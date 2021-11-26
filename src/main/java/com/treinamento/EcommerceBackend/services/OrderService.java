package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.BankSlipPaymentEntity;
import com.treinamento.EcommerceBackend.entities.CategoryEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import com.treinamento.EcommerceBackend.entities.ProductEntity;
import com.treinamento.EcommerceBackend.entities.enums.StatusPaymentEnum;
import com.treinamento.EcommerceBackend.repositories.CategoryRepository;
import com.treinamento.EcommerceBackend.repositories.OrderItemRepository;
import com.treinamento.EcommerceBackend.repositories.OrderRepository;
import com.treinamento.EcommerceBackend.repositories.PaymentRepository;
import com.treinamento.EcommerceBackend.repositories.ProductRepository;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BankSlipPaymentService bankSlipPaymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ClientService clientService;

    public List<OrderEntity> findAll(){
        return orderRepository.findAll();
    }

    public OrderEntity findById(Integer id){
        Optional<OrderEntity> user = orderRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

    @Transactional
    public OrderEntity insert(OrderEntity order) {
        order.setId(null);
        order.setInstant(new Date());
        order.setClient(clientService.findById(order.getClient().getId()));
        order.getPayment().setStatus(StatusPaymentEnum.PENDENTE);
        order.getPayment().setOrder(order);
        //payment
        if(order.getPayment() instanceof BankSlipPaymentEntity){
            BankSlipPaymentEntity bankSlipPayment = (BankSlipPaymentEntity) order.getPayment();
            bankSlipPaymentService.generatePaymentDate(bankSlipPayment, order.getInstant());
        }
        order = orderRepository.save(order);
        paymentRepository.save(order.getPayment());
        //order list itens
        for(var item : order.getOrderItemList()){
            item.setDiscount(0.0);
            item.setProduct(productService.findById(item.getProduct().getId()));
            item.setPrice(item.getProduct().getPrice());
            item.setOrder(order);
        }
        orderItemRepository.saveAll(order.getOrderItemList());
        emailService.sendOrderConfirmationMail(order);
        return order;
    }

}
