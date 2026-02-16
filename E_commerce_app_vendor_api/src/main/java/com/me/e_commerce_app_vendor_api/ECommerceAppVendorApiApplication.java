package com.me.e_commerce_app_vendor_api;

import com.me.e_commerce_app_vendor_api.service.tokenService.TokenService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ECommerceAppVendorApiApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(ECommerceAppVendorApiApplication.class, args);
        var tokenService = context.getBean(TokenService.class);
        var token = tokenService.createToken("Example", 31556926, "secretbhjhhghcfdgfdgfxfhdgsdfsxdzfdszsdzsdzddsdxcxdfsxdgrxdxgdxdgxdxdfxfdrdxdfxdfxg");
//        System.out.println(token);
        var payload = tokenService.decodeToken(token);
        System.out.println(payload);
    }

}
