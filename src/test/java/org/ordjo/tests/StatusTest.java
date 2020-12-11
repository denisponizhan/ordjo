package org.ordjo.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ordjo.model.StatusUpdate;
import org.ordjo.model.StatusUpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class StatusTest {

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    @Test
    public void testSave() {
        StatusUpdate status = new StatusUpdate("This is a test status update.");
        statusUpdateDao.save(status);

        assertNotNull("Non-null ID", status.getId());
        assertNotNull("Non-null Date", status.getAdded());

        StatusUpdate retrieved = statusUpdateDao.findById(status.getId()).get();

        assertEquals("Matching StatusUpdate", status, retrieved);

    }

    @Test
    public void testFindLatest() {
        Calendar calendar = Calendar.getInstance();

        StatusUpdate lastStatusUpdate = null;

        for (int i = 0; i < 10; i++) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            StatusUpdate status = new StatusUpdate("Status Update: " + i, calendar.getTime());
            statusUpdateDao.save(status);

            lastStatusUpdate = status;
        }

        StatusUpdate retrived = statusUpdateDao.findFirstByOrderByAddedDesc();
        assertEquals("Latest status update", lastStatusUpdate, retrived);
    }
}
