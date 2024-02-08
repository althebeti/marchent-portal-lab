package com.merchant.portal.data;

import com.merchant.portal.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionView {


    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String transactionId;
    private Date date;
    private String status;
    private String amount;
    private String approvalCode;
    private String verificationMethod;
    private String tid;


    public TransactionView(Date date, String status, String amount, String approvalCode, String verificationMethod, String tid) {
        this.date = date;
        this.status = status;
        this.amount = amount;
        this.approvalCode = approvalCode;
        this.verificationMethod = verificationMethod;
        this.tid = tid;
    }
}

