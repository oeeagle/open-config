# Introduction #
  * What is OpenConfig?
    * Addresses configuration management across development environments
    * Provides a means of strongly typing configuration within an application.
    * Enterprise Worthy features
      * Change Management
      * Security Abstraction
      * Search and reporting
      * Scalable
      * Version Management
      * Exporting
    * Easy API
    * Extremely pluggable
      * DataProvider
      * ConfiguratorFactory
      * EnvironmentResolver
      * OpenConfigContext
      * EventListener
      * OpenConfigConfiguration
    * Local Developing using a custom configuration class
      * Model the configuration
      * Define a Configuration class or interface
      * ConfiguratorFactoryBuilder
      * Create a configuration file (xml or properties)
      * Set the environment variable
    * How to use different DataProviders?
      * Create an open-config.properties file
      * Add DataProvider=class.name.Here
    * How to create a custom DataProvider?
    * How to handle configuration changes on the fly?
      * Create an EventListener
      * Pass in the EventListener into the ConfiguratorFactory#newInstance(..)
    * How to write a custom EnvironmentResolver?
    * How to customize parts of openconfig?
      * penConfig is designed to be pluggable. The pluggability is handled by OpenConfigConfiguration.
    * How to write test cases?
    * Architecture overview
    * An overview of an invocation flow