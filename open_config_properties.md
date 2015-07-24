# open-config.properties #

OpenConfig was designed to be pluggable from the start. Internally, OpenCong uses [Guice](http://code.google.com/p/google-guice/) for dependency injection. This allows hard-core developers to customize the internals of OpenConfig. In order to tell OpenConfig to use a custom implementation of an internal component, an open-config.properties must be created and dropped at the root level of the class-path.

The open-config.properties file is a key/value file where the key defines the component alias in question and the value being the implementation to be used. There are currently several pluggable components within OpenConfig which are explained below:

| **Name** | **Default** |
|:---------|:------------|
| ConfiguratorFactory |  org.openconfig.factory.impl.DefaultConfiguratorFactory|
| EnvironmentResolver |  org.openconfig.core.SystemPropertyEnvironmentResolver|
| DataProvider |  org.openconfig.providers.PropertiesDataProvider|
| EventPublisher |  org.openconfig.event.DefaultEventPublisher|

## ConfiguratorFactory ##

This class is used to construct a new instance of the Configurator class or configuration proxies. In cases in which the ConfiguratorFactory needs to be customized, this entry point can used to customize the construction of new Configurators or proxy classes. For more details on the ConfiguratorFactory, please consult the Javadocs.

## EnvironmentResolver ##

The resolution of the current environment varies from company to company, so this component is used to detect what environment OpenConfig is currently running in. By default, OpenConfig comes equipped with a default implementation that checks the System properties for a 'environment' variable. This can be set using the JVM arguments (-D) or by calling System#setProperty(String, String). If your companies has a different way to detect the environment, this will be the entry point.

## DataProvider ##

The DataProvider provides a means of looking up values for each invocation. OpenConfig comes with two ready to go implementations. XmlDataProvder and PropertiesDataProvider. For more information on using DataProviders please see [DataProviders](http://code.google.com/p/open-config/wiki/SwitchingDataProviders).

The _default_ implementation uses the PropertiesDataProvider.

## EventPublisher ##

The EventPublisher is used to notify event listeners of lifecycle or state changes such as the reloading of a configuration file in development or an update of a property from the administrative console in real-time. The purpose behind exposing the EventPublisher is because some companies may want provide server level auditing of changes or customization of how events are published.