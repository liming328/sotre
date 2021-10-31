package com.yaorange.jk.job;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.DateUtils;
import com.yaorange.jk.utils.JavaMailUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 〈到期邮件提醒〉
 * 〈坚持灵活 灵活坚持〉
 */
public class MailJob {
    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailUtil javaMailUtils;

    public void sendMail(){
        System.out.println("send mail..."+new Date());
        //根据交期查询出购销合同列表
        List<Contract> contractList = contractService.findListByDeliveryPeriod(DateUtils.formatDate(new Date(),"yyyy-MM-hh"));
        //依次给每个购销合同的创建人发提醒邮件
        if (null!=contractList&&contractList.size()>0){
            for (Contract contract : contractList) {
                //取到每个购销合同的创建人的邮箱
                String inputBy = contract.getInputBy();
                User user = userService.findByUsername(inputBy);
                String email = user.getUserInfo().getEmail();

                try {
                    javaMailUtils.sendMail(email,"交货日期到期提醒",
                            "您好！"+user.getUserInfo().getName()+"今天您有一个购销合同到期了，合同号："+contract.getContractNo()+",请尽快处理！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


