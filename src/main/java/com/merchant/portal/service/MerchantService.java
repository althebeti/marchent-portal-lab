package com.merchant.portal.service;

import com.merchant.portal.controller.dto.TransactionRequest;
import com.merchant.portal.exception.NoRecordFoundException;
import com.merchant.portal.model.TransactionModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MerchantService {

    String sendTransaction (TransactionRequest transactionRequest , HttpServletRequest request);

    List<TransactionModel> getTransaction ();

    TransactionModel getTransactionID(String transactionId) throws NoRecordFoundException;

}
