# Introduction #

## How to create a custom DataProvider? ##

> Out of the box, OpenConfig comes with **four prebuilt DataProviders** which can be used for **local development**. These DataProviders are **backed by files stored** on the developers computer and allows for **real-time changes** without the need to restart the application. In the case that none of these suit you're local development needs, then a custom DataProvider can be built.

> A DataProvider must implement the interface org.openconfig.providers.DataProvider which defines the contract of a DataProvider. To make creating a custom DataProvider easier, there are a few abstract classes provided, each provides a solution to different problems.

  * AbstractDataProvider - Provides a partial implementation that handles event notifications when the underlying data changes and obtaining the value of an invocation.
  * AbstractReloadableDataProvider - Extends the AbstractDataProvider and provides a hook for reloading detecting if the DataProvider should reload the data backing it.
  * FileDataProvider - An abstract DataProvider which extends AbstractReloadableDataProvider and detects when the file backing the provider changes.

> When a DataProvider is created, the **initialize** is called to allow the provider to initialize anything needed by the provider. In the case of the out-of-box DataProviders, this means eagerly creating the internal Abstract Syntax Tree (The internal Object Graph). In some cases, context sensitive information maybe obtained using the OpenConfigContext object.

> There are two lifecycle events on the DataProvider interface which will be refactored out, since they logically don't belong in the DataProvider interface. Those methods are:

  * boolean requiresReloading()
  * void reload()

> When a DataProvider is backed by a resource which may change, then the requiredReloading method must return true which will trigger a reloading of the data. **TODO: Add more on this subject**

> The hardest part about creating a DataProvider is constructing the Object Graph using the internal AST. Objects are represented using a ComplexNode, which essentially acts as a Map of AbstractNodes. **TODO** Should this be a Map of Nodes? For more information on working with the AST, consult the section **TODO Write the AST section**.

void registerEventListeners(String configurator, EventListener... eventListeners)