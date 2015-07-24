#This section is related to identifying classes that are related to a particular task.

# Introduction #

The purpose of this section is to identify classes which need to be documented for a particular task.

# Details #

Developer Development - Client Side
  * [How to create a custom DataProvider](HowtocreateacustomDataProvider.md) - DataProvider, FileDataProvider, AbstractDataProvider, AbstractReloadableDataProvider
  * Understanding the prebuilt DataProvider - XmlDataProvider, YamlDataProvider, JsonDataProvider, JmxDataProvider, PropertiesDataProvider
  * Client to Server Communication - JMX, ComplexNode, SimpleNode, CompositeData, CompositeDataTransformer, OpenMBeanTranformer, SimpleTypeTransformer
  * Internal Object Graph Structure - ComplexNode, SimpleNode, NodeManager
  * How the Configurator works - ConfigurationFactoryBuilder, ConfigurationLocator, ConfiguratorFactory, ConfigurationFactoryBuilder
  * Environment Detection - EnvironmentResolver, Environment, OpenConfigContext
  * How to customize the location of the open-config configuration file - ConfigurationLocator
  * Server to Client Protocol Representation
  * How to use the OpenConfigContext and why?
  * How properties on an object resolved from the AST (Object Graph)
  * Understanding the default SystemPropertyEnvironmentResolver
  * How to customize OpenConfig - PropertyNormalizer, OpenConfigContext, ConfiguratorFactory, EnvironmentResolver, DataProvider, EventPublisher
  * How does the OpenConfig ProxyInvocationHandler convert data types? BeanToNodeTransformer, StringToBooleanTransformer, StringToCharacterTransformer, StringToDoubleTransformer, StringToEnumTransformer, StringToFloatTransformer, StringToIntegerTransformer, StringToLongTransformer
  * How is the open-config configuration processed? OpenConfigConfiguration, MergingOpenConfigConfiguration, PropertiesOpenConfigConfiguration, XmlOpenConfigConfiguration