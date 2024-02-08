package com.merchant.portal.controller.merchant;

import com.merchant.portal.exception.NoRecordFoundException;
import com.merchant.portal.model.TransactionModel;
import com.merchant.portal.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/portal")
public class MerchantController {

    public static final String merchant = "portal/merchant";
    public static final String merchantdetails = "portal/merchantdetails";

    private MerchantService merchantService;

    @Autowired
    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }


    @GetMapping("/merchant")
    public ModelAndView getMerchantTransaction(Model model) throws NoRecordFoundException {

        List<TransactionModel> transactionModel = merchantService.getTransaction();
        if (!transactionModel.isEmpty()) {
            model.addAttribute("transactions", transactionModel);
            return new ModelAndView(merchant);
        }else{
            throw new NoRecordFoundException();
        }

    }

    @GetMapping("/merchantdetails")
    public ModelAndView getMerchantDetails(Model model , @RequestParam String transactionId) throws NoRecordFoundException {
        TransactionModel transactionModel = merchantService.getTransactionID(transactionId);
        if (transactionModel != null) {
            model.addAttribute("transactions", transactionModel);
            return new ModelAndView(merchantdetails);
        }else{
            throw new NoRecordFoundException();
        }
    }
}

