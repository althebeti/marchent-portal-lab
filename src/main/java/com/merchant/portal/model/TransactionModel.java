package com.merchant.portal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {

    private String transactionId;
    private Date date;
    private String status;
    private String amount;
    private String approvalCode;
    private String verificationMethod;
    private String tid;

}
