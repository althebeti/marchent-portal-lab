package com.merchant.portal.controller.dto;

import com.merchant.portal.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private Status status;
    private double amount;
    private String approvalCode;
    private String verificationMethod;
}
