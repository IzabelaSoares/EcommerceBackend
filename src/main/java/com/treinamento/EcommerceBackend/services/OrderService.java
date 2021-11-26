package com.treinamento.EcommerceBackend.services;

import com.treinamento.EcommerceBackend.entities.BankSlipPaymentEntity;
import com.treinamento.EcommerceBackend.entities.ClientEntity;
import com.treinamento.EcommerceBackend.entities.OrderEntity;
import com.treinamento.EcommerceBackend.entities.enums.StatusPaymentEnum;
import com.treinamento.EcommerceBackend.repositories.OrderItemRepository;
import com.treinamento.EcommerceBackend.repositories.OrderRepository;
import com.treinamento.EcommerceBackend.repositories.PaymentRepository;
import com.treinamento.EcommerceBackend.security.UserSS;
import com.treinamento.EcommerceBackend.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
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

    public OrderEntity findById(Integer id){
        Optional<OrderEntity> user = orderRepository.findById(id);
        return user.orElseThrow(() -> new DatabaseException(id));
    }

    @Transactional
    public OrderEntity insert(OrderEntity order) {
        order.setId(null);
        order.setInstant(new Date());
        order.setClient(clientService.findById(order.getClient().getId()));
        order.getPayment().setStatus(StatusPaymentEnum.PENDING);
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
        //emailService.sendOrderConfirmationHtmlEmail(order);
        //emailService.sendOrderConfirmationMail(order);
        return order;
    }

    public Page<OrderEntity> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationServiceException("Access Denied!");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        ClientEntity client =  clientService.findById(user.getId());
        return orderRepository.findByClient(client, pageRequest);
    }

}
