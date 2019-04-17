package com.yiban.yiban_application;
import com.yiban.yiban_application.system.dao.AdminInterface;
import com.yiban.yiban_application.system.dao.RendsInterface;
import com.yiban.yiban_application.system.dao.ResourceInterface;
import com.yiban.yiban_application.web.service.AssessInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YibanApplicationTests {

    @Autowired
    private AdminInterface adminInterface;
    @Autowired
    private RendsInterface rendsInterface;

    @Autowired
    private AssessInterface assessInterface;

    @Test
    public void contextLoads() {
//        Sys_admin admin = new Sys_admin();
//        admin.setAdmin_id(String.valueOf(9240766));
//        admin.setAdmin_name("董天贵");
//        admin.setAdmin_pass(Sha256.getSHA256("1056976753"));
//        admin.setAdmin_role(2);
//        adminInterface.insertAdmin(admin);

        System.out.println(rendsInterface.getAllByUser("9240766"));
    }
}
