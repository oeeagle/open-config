# Understanding the prebuilt DataProviders

# Introduction #

When developing an application locally, it's important to provide the developers with the right tools to make their job easy and fast. Out of the box, OpenConfig Client comes packaged with four DataProviders, each handles a different file format. Each of these providers are backed by a file which **must** (TODO: Code change to support custom location of these files) be at in root of the class-path. The name of the file by convention is the configuration interface provided by the developer.

First thing needed is to define a class or interface which will model the configuration information, commonly referred to as "Configuration Interfaces". Configuration Interfaces don't need to implement any special interfaces or required any annotations for them to work.

There are a few constraints currently placed on the return types of Configuration Interfaces are listed below:

  * Instance of Collection
  * java.util.Array
  * java.util.Map
  * Annotations
  * Final classes - Except for String and the primitive wrappers.

To give you an understanding of what can be returned, here's a sample list:

  * Custom Classes (Including Abstract classes)
  * Enumerations
  * Primitives
  * Primitive wrappers
  * Interfaces

TODO: Explain the below section..

```
public class DatabaseConfiguration {
   String getJdbcUrl();
   int getPort();
   User getAdmin();
}

public class User{
    private long id;
    private String email;

    public long getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

}
```

In the definition above, one interface is defined which models the configuration interface and a domain class. Neither of the two classes above implement any special API or require anything special such as annotations.


At this point the developer has his or her choice in the format of the configuration file. In our example, we're going to use a simple properties file since there's no hierarchy to the configuration.

```
<?xml version="1.0"?>
<configurator jdbcUrl="http://www.aol.com" 
                          port="8080">
    <admin id="100" email="guy@somesite.com"/>
<configurator/>
```


Note: When selecting the format of your configuration file, we recommend using XML or YAML for hierarchy structures and for simple key/value configurations we recommend the PropertiesDataProvider. So why do we have a JsonDataProvider? Because it was an easy win and there maybe a JavaScript fanboy out there. ;)


## XmlDataProvider ##


## YamlDataProvider ##


## JsonDataProvider ##

## PropertiesDataProvider ##