package com.example.langoal.web;

import com.example.langoal.entities.Order;
import com.example.langoal.entities.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {
    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "pay/Success";
    public static final String CANCEL_URL = "pay/Cancel";

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String payment() {
        return "Payment";
    }
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String PaymentProcess(@ModelAttribute("order") Order order) {
       try {
           Payment payment =  service.createPayment(order.getPrice(),order.getCurrency(),order.getMethod(), order.getIntent(), order.getDescription(), "http://localhost:8080/" + CANCEL_URL,"http://localhost:8080/" + SUCCESS_URL);

           for(Links link:payment.getLinks()) {
               if(link.getRel().equals("approval_url")) {
                   return "redirect:" + link.getHref();
               }
           }

       } catch (PayPalRESTException e) {
           e.printStackTrace();
       }
        return "Cancel";
    }

    @RequestMapping(value = CANCEL_URL, method = RequestMethod.GET)
    public String CancelPayment() {
        return "Cancel";
    }

    @RequestMapping(value = SUCCESS_URL, method = RequestMethod.GET)
    public String successPayment(@RequestParam("paymentId") String paymentId, @RequestParam("PayerId") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if(payment.getState().equals("approved")) {
                return "Success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }
}
