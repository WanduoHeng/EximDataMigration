package com.brilliance.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.ibase4j.core.base.BaseModel;
import java.io.Serializable;

/**
 * 描述：租金保理
 *
 * @author xiaoshuiquan
 * @date 2018/6/20
 */

@TableName("BIZ_RENTAL_FACTORING_KEY")
public class BizTheRentFactoring extends BaseModel implements Serializable {

    /**
     * 业务编号
     */
    @TableField("DEBT_CODE")
    private String debtCode;
    /**
     * 产品种类
     */
    @TableField("BUSINESS_TYPES")
    private String businessTypes;
    /**
     * 客户编号
     */
    @TableField("CUST_NO")
    private String custNo;



    public String getDebtCode() {
        return debtCode;
    }

    public void setDebtCode(String debtCode) {
        this.debtCode = debtCode;
    }

    public String getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(String businessTypes) {
        this.businessTypes = businessTypes;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
}
