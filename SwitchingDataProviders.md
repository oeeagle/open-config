# How to use different DataProviders #

When developing locally, it's preferred to create a local configuration file that can be quickly modified in order to change the values while developing. In such a situation, OpenConfig allows for switching the DataProvider. Out of the box, OpenConfig supports the following DataProviders:

  * XmlDataProvider
  * PropertiesDataProvider

Depending on the preference of the developer, either of these DataProviders can be used or a custom DataProvider written. In the following section, we're going to examine how to swap out different DataProviders.

## How to swap out DataProviders ##

All customization of OpenConfig is done by modifying the open-config.properties file. This file is a simple key/value file that defines the relationship between the pluggable 'component' with the implementation. The pluggable component that needs to be replace when working with DataProviders is the DataProvider component. The below example shows how to enable the usage of the XmlDataProvider packaged with OpenConfig.

```
DataProvider=org.openconfig.providers.XmlDataProvider
```

For detail information about the open-config.properties file, please see [open-config.properties](http://code.google.com/p/open-config).

## Understanding how the 'default' DataProviders locate the configuration files ##

When creating a configuration file that'll back a custom configuration interface, you must name the configuration file exactly the same as the configuration interface when using one of the default DataProviders (_XmlDataProvider and PropertiesDataProvider_).

Let's take for a moment where we want to create a DatabaseConfiguration class/interface that we will used to represent the configuration information for our database access. The first thing we will do is save a DatabaseConfiguration.properties or DatabaseConfiguration.xml at the root level of the class-path. Note: the extension of the DataConfiguration will be dependent on the DataProvider selected.

## How to use the XmlDataProvider ##

When working with hierarchical data structures, it's preferred to use the XmlDataProvider over the PropertiesDataProvider for obvious reasons. The configuration file passed to the XmlDataProvider doesn't have any DTD or Schema. Each node within the file maps to an object on the configuration interface. Let's example a simple configuration file.

**ConfiguratorInterface.xml**

```
<?xml version='1.0'?>
<configuration>
   <person id="100" name="Richard Burton">
       <friend id="101" name="Dushyanth Inguva"/>
   </person>
</configuration>
```

**The domain model for the above configuration looks as follows:**

```
public class Person {
   private int id;
   private String name;
   private Person friend;
   // Define the getter methods..
}
```

**ConfiguratorInterface.java**

```
public interface ConfiguratorInterface {
   Person getPerson();
}
```

**Usage example:**

```
ConfiguratoryFactory cf = new ConfiguratorFactoryBuilder().build();
ConfiguratorInterface ci = cf.newInstance(ConfiguratorInterface.class);
Person person = ci.getPerson();
System.out.println("The person id is " + person.getId());
System.out.println("The person name is " + person.getName());
```

## How to use the PropertiesDataProvider ##

It's very common for a project to use a properties file to store configuration information since it's easy to manage when dealing with a small number of configuration values. When developing locally, developers may want to use a properties file to quickly get moving without having to deal with configuring the administrative service for OpenConfig. This is where the PropertiesDataProvider comes into the picture. We're going to take the above example and create a properties file that the PropertiesDataProvider can use to back the custom interface ConfiguratorInterface used in the prior example.

In order to represent the above example in a properties file, we will have to use dot notation to express the invocation path done on the ConfiguratorInterface. When methods are invoked on the custom Configurator, the invocation path is passed down to the DataProvider to uniquely identify the value accessed in the configuration file. Let's take a simple example and break it down.

```
configuratorInterface.getPerson().getFriend().getName()
```

The above example would translate into the following dot notation:

```
person.friend.name
```

You can see how the method names are normalized into a single key that can be to locate the value within the properties file. So let's convert the prior XML file into a properties file!

**ConfiguratorInterface.properties**

```
person.id=100
person.name=Richard Burton
person.friend.id=101
person.friend.name=Dushyanth Inguva
```

The PropertiesDataProvider searches for the ConfiguratorInterface.properties by looking in the root of the classpath.

Take notice how the 'person' namespace is repeated for each attribute and child node from the prior example to reflect the hierarchy. The last thing we need to do is tell OpenConfig to use the PropertiesDataProvider by editing the open-config.properties file.

```
DataProvider=org.openconfig.providers.PropertiesDataProvider
```

That's it! The code example that uses the ConfiguratorInterface doesn't have to be touched.