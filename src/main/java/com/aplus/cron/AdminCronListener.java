package com.aplus.cron;

import com.aplus.entity.CompanyEntity;
import com.aplus.services.CompanyService;
import com.aplus.services.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by lifang on 2015/5/17.
 */
@Component
public class AdminCronListener {

    private Logger logger = LoggerFactory.getLogger(AdminCronListener.class);

    @Scheduled(cron = "0/30 * * * * ?")
    public void task(){
        save();
    }

    @Autowired
    private CompanyService companyService;
    @Autowired
    private IdentityService identityService;

    public void save(){
        new SaveThread().start();
    }

    class SaveThread extends Thread{
        @Override
        public void run() {
            logger.info("company identity no {}", identityService.getIdentity(CompanyEntity.class));
        }
    }
}
