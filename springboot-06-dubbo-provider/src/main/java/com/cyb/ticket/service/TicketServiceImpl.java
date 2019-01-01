package com.cyb.ticket.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author cyb
 * @date 2019/1/1 - 19:29
 */
@Service    //将服务发布出去
@Component
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket () {
        return "<<厉害了，我的国>>";
    }
}
