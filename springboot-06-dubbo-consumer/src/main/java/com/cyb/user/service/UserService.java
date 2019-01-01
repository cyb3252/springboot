package com.cyb.user.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cyb.ticket.service.TicketService;
import org.springframework.stereotype.Service;

/**
 * @author cyb
 * @date 2019/1/1 - 20:22
 */
@Service
public class UserService {

    @Reference
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println("买到票了"+ticket);
    }
}
