package org.openconfig;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.openconfig.core.*;
import org.openconfig.core.bean.ConfiguratorProxyInvocationHandler;
import org.openconfig.core.bean.PropertyNormalizer;
import org.openconfig.core.bean.ProxyInvocationHandler;
import org.openconfig.event.EventListener;
import org.openconfig.event.EventPublisher;
import org.openconfig.factory.ConfiguratorFactory;
import org.openconfig.ioc.OpenConfigModule;
import org.openconfig.providers.CompositeDataProvider;
import org.openconfig.providers.DataProvider;
import org.openconfig.providers.PropertiesDataProvider;

import static java.util.Collections.singletonMap;

/**
 * Currently, there is no IoC container being used, so this ObjectFactory
 * will be used to abstract the construction specific concert types of classes.
 *
 * @author Richard L. Burton III
 * @author Dushyanth (Dee) Inguva
 * @depreciated
 */
public class ObjectFactory {

    private static final ObjectFactory INSTANCE = new ObjectFactory();

    private Injector injector;

    private ObjectFactory() {
        injector = Guice.createInjector(new OpenConfigModule());
    }

    public static ObjectFactory getInstance() {
        return INSTANCE;
    }

    public Configurator newDefaultConfigurator(EventListener... eventListeners) {
        DataProvider dataProvider = getDataProvider(Configurator.class.getSimpleName());
        EventPublisher eventPublisher = createEventPublisher(eventListeners);
        Configurator returnValue = new DataProviderToConfiguratorAdapter(dataProvider);
        return returnValue;
    }

    /**
     * @param configuratorInterface
     * @param alias
     * @param eventListeners
     * @return
     * @todo refactor to improve the object creation by Guice.
     */
    public ConfiguratorProxy newConfiguratorProxy(Class configuratorInterface, boolean alias, EventListener... eventListeners) {

        DataProvider dataProvider = getDataProvider(configuratorInterface.getSimpleName());

        PropertyNormalizer propertyNormalizer = injector.getInstance(PropertyNormalizer.class);
        EventPublisher eventPublisher = createEventPublisher(eventListeners);

        ConfiguratorProxy proxy = new ConfiguratorProxy(configuratorInterface, alias, eventPublisher);
        ProxyInvocationHandler returnHandler = new ConfiguratorProxyInvocationHandler(proxy);
        proxy.setDataProvider(dataProvider);
        proxy.setPropertyNormalizer(propertyNormalizer);
        proxy.setProxyInvocationHandler(returnHandler);
        return proxy;
    }

    private EventPublisher createEventPublisher(EventListener[] eventListeners) {
        EventPublisher eventPublisher = injector.getInstance(EventPublisher.class);
        eventPublisher.addListeners(eventListeners);
        return eventPublisher;
    }

    private DataProvider getDataProvider(String configuratorName) {
        DataProvider dataProvider = injector.getInstance(DataProvider.class);

        if (dataProvider instanceof CompositeDataProvider) {
            CompositeDataProvider cdp = (CompositeDataProvider) dataProvider;
            if (cdp.missingDataProvider(configuratorName)) {
                DataProvider propertiesDataProvider = new PropertiesDataProvider(); // TODO: Remove this out somehow.
                OpenConfigContext context = new BasicOpenConfigContext(singletonMap("interface", configuratorName));
                propertiesDataProvider.initialize(context);
                cdp.addDataProvider(configuratorName, propertiesDataProvider);
            }
        }
        return dataProvider;
    }

    public ConfiguratorFactory newConfiguratorFactory() {
        return injector.getInstance(ConfiguratorFactory.class);
    }

    public <T> T construct(String clazzName) {
        try {
            Class clazz = Class.forName(clazzName);
            return (T) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public EnvironmentResolver getDefaultEnvironmentResolver() {
        return injector.getInstance(EnvironmentResolver.class);
    }
}