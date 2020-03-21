package de.letsdev.products.dico.scanner.backend;

import de.letsdev.products.dico.scanner.backend.push.PushService;
import de.letsdev.products.dico.scanner.backend.push.PushServiceException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PushService.class})
public class PushTest {

    @Autowired
    PushService pushService;

    @Test
    @Ignore
    public void pushMessageTest() throws PushServiceException {
        pushService.sendPushToDevice("sdsdsa", "asdsadas", "asdsa");
    }
}
