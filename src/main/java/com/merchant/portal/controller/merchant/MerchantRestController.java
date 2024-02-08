package com.merchant.portal.controller.merchant;

import com.merchant.portal.controller.dto.TransactionRequest;
import com.merchant.portal.model.MessageSuccessResponse;
import com.merchant.portal.service.MerchantService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transaction")
@Log4j2
public class MerchantRestController {

    private MerchantService merchantService;


    @Autowired
    public MerchantRestController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MessageSuccessResponse> sendTransaction(@ModelAttribute TransactionRequest transactionRequest, HttpServletRequest request) {

        log.info("Type controller, status : start , message-log : start send transaction to merchant portal with amount : {}",
                transactionRequest.getAmount());
        return new ResponseEntity<>(new MessageSuccessResponse(String.valueOf(merchantService.sendTransaction(transactionRequest, request)))
                , HttpStatus.CREATED);
    }
}
