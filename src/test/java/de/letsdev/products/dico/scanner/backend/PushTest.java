package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.db.LocationRepository;
import de.letsdev.products.dico.scanner.backend.push.PushService;
import de.letsdev.products.dico.scanner.backend.push.PushServiceException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class, PushService.class, LocationRepository.class},loader = AnnotationConfigContextLoader.class)
@SpringBootTest
@ComponentScan(basePackages = {"de.letsdev"})
@AutoConfigureMockMvc
public class PushTest {

    @Autowired
    PushService pushService;

    @Test
   //@Ignore
    public void pushMessageTest() throws PushServiceException {
        pushService.sendPushToDevice("umläut", "umläöut", "asdsa");
    }
}
