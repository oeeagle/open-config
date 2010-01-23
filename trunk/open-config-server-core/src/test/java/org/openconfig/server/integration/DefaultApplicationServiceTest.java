package org.openconfig.server.integration;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openconfig.server.domain.Application;
import org.openconfig.server.domain.Configuration;
import org.openconfig.server.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Creates an entire Application along with a Configuration, persists it and tests a round trip.
 *
 * @author Dushyanth (Dee) Inguva - SmartCode LLC
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test-config.xml")
public class DefaultApplicationServiceTest extends AbstractDatabaseIntegrationTest {

    @Autowired
    private ApplicationService applicationService;

    @Test
    public void verifySaveApplication() {
        Configuration configuration = DefaultConfigurationServiceTest.createConfiguration();
        Application application = new Application();
        String applicationName = "D'OH";
        String configurationAlias = "AYE KARAMBA";
        application.setName(applicationName);
        application.addConfiguration(configurationAlias, configuration);
        applicationService.saveApplication(application);

        Application persistedApplication = applicationService.findApplication(applicationName);
        assertEquals(applicationName, persistedApplication.getName());

        assertEquals(configuration, persistedApplication.getConfiguration(configurationAlias));
    }
}