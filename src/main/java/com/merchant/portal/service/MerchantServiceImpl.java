package com.merchant.portal.service;

import com.merchant.portal.controller.dto.TransactionRequest;
import com.merchant.portal.data.TransactionRepository;
import com.merchant.portal.data.TransactionView;
import com.merchant.portal.exception.NoRecordFoundException;
import com.merchant.portal.model.TransactionModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class MerchantServiceImpl  implements MerchantService{

    private TransactionRepository transactionRepository;

    @Autowired
    public MerchantServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String sendTransaction(TransactionRequest transactionRequest ,HttpServletRequest request) {

        log.info("Type service, status : start , message-log : start send transaction to merchant portal with amount : {}",
                transactionRequest.getAmount());
        String browserType = request.getHeader("User-Agent");
        String tid = getBrowserInfo(browserType);
        String amount = getAmountInSar(transactionRequest.getAmount());
        TransactionView transactionView = transactionRepository.save(new TransactionView(new Date(),transactionRequest.getStatus().name(),
                amount,transactionRequest.getApprovalCode(),transactionRequest.getVerificationMethod(),tid));
        return "transaction created with id :" +transactionView.getTransactionId();
    }

    @Override
    public List<TransactionModel> getTransaction() {
        log.info("Type service, status : start , message-log : start get all transaction to merchant portal");
        List<TransactionView> transactionViewList = transactionRepository.findAll();
        return transactionViewList.stream().map(transactionView -> new TransactionModel(transactionView.getTransactionId(),
                transactionView.getDate(),transactionView.getStatus(),transactionView.getAmount(),
                transactionView.getApprovalCode(),transactionView.getVerificationMethod(),
                transactionView.getTid())).collect(Collectors.toList());
    }

    public TransactionModel getTransactionID(String transactionId) throws NoRecordFoundException {
        log.info("Type service, status : start , message-log : start get all transaction to merchant portal");
        Optional<TransactionView> transactionViewList = transactionRepository.findById(transactionId);
        if (transactionViewList.isPresent()) {
            return new TransactionModel(transactionViewList.get().getTransactionId(),
                    transactionViewList.get().getDate(), transactionViewList.get().getStatus(), transactionViewList.get().getAmount(),
                    transactionViewList.get().getApprovalCode(), transactionViewList.get().getVerificationMethod(),
                    transactionViewList.get().getTid());
        }else{
            log.info("no record found for transaction id : {}", transactionId);
            throw new NoRecordFoundException();
        }
    }

    public String getAmountInSar(double value){

        Locale locale = new Locale("", "SA");
        NumberFormat currencyFormat= NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(value);
    }

    public String  getBrowserInfo( String Information )
    {
        log.info("type : method  , message-log : detecting browser info");
        String browsername = "";
        String browserversion = "";
        if (Information.contains("MSIE"))
        {
            String subsString = Information.substring(Information.indexOf("MSIE"));
            String[] info = (subsString.split(";")[0]).split(" ");
            browsername = info[0];
            browserversion = info[1];
        } else if (Information.contains("Firefox"))
        {

            String subsString = Information.substring(Information.indexOf("Firefox"));
            String[] info = (subsString.split(" ")[0]).split("/");
            browsername = info[0];
            browserversion = info[1];
        } else if (Information.contains("Chrome"))
        {

            String subsString = Information.substring(Information.indexOf("Chrome"));
            String[] info = (subsString.split(" ")[0]).split("/");
            browsername = info[0];
            browserversion = info[1];
        } else if (Information.contains("Opera"))
        {

            String subsString = Information.substring(Information.indexOf("Opera"));
            String[] info = (subsString.split(" ")[0]).split("/");
            browsername = info[0];
            browserversion = info[1];
        } else if (Information.contains("Safari"))
        {

            String subsString = Information.substring(Information.indexOf("Safari"));
            String[] info = (subsString.split(" ")[0]).split("/");
            browsername = info[0];
            browserversion = info[1];
        }
        else if (Information.contains("PostmanRuntime/7.29.0"))
        {

            String subsString = Information.substring(Information.indexOf("PostmanRuntime/7.29.0"));
            String[] info = (subsString.split(" ")[0]).split("/");
            browsername = info[0];
            browserversion = info[1];
        }
        return browsername + "-" + browserversion;
    }
}
