package hr.test;

import com.hr.GroupTalkApplication;
import com.hr.pojos.Group;
import com.hr.pojos.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = GroupTalkApplication.class)
public class MainTest {

    @Autowired
    User user;

    @Autowired
    User user1;

    @Autowired
    Group group1;

    @Autowired
    Group group2;
    @Test
    public void test(){
        System.out.println(user);
        System.out.println(user1);
        System.out.println(user1==user);

        System.out.println(group1);
        System.out.println(group2);
        System.out.println(group1 == group2);
    }
}
