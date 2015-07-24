OpenConfigConfiguration handles the pluggability aspect of OpenConfig.
# Introduction #

When OpenConfig is initialized, it consults OpenConfigConfiguration on it's behavior. Each aspect of OpenConfig is fronted by an interface. Users wishing to customize any aspect of OpenConfig have to implement the corresponding interface and specify them during initialization.

# Details #

The default behavior of OpenConfig is specified through a mapping file that ships with OpenConfig called open-config.properties which looks like
```
PropertyNormalizer=org.openconfig.core.bean.LowercasePropertyNormalizer
OpenConfigContext=org.openconfig.core.BasicOpenConfigContext
ConfiguratorFactory=org.openconfig.factory.impl.DefaultConfiguratorFactory
EnvironmentResolver=org.openconfig.core.SystemPropertyEnvironmentResolver
DataProvider=org.openconfig.providers.PropertiesDataProvider
EventPublisher=org.openconfig.event.DefaultEventPublisher
```

All the keys that can be overridden are listed in the javadoc of the class OpenConfigConfiguration. For more details on the contents and structure of open-config.properties, please look at [open-config.properties](open_config_properties.md)

To override one of the aspects of OpenConfig, implement the corresponding interface, declare a properties file
TODO: document how OpenConfigConfiguration can be customized