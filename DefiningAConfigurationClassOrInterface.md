# Introduction #

A typical application configuration can be specified as a properties file/xml file which can be

# A user specified Configuration class or interface
# The Configuration interface that belongs to OpenConfig.

# A user specified Configuration class or interface
> This is the recommended way of configuring applications. Java is a strongly typed language. Yet, many OpenSource and custom configuration APIs provide a weakly typed interface to underlying configuration data.

A typical configuration look up looks like
```
public String configuration.getValue(String lookupKey);
```

Some of the disadvantages of this approach are:

**Since the lookupKey is a String, if there is a typo, the configuration cannot locate the correct value from the file. A common way of avoiding this problem is to use a static final variable as the key. Still, the responsiblity of making sure the same variable is used at multiple places falls on the programmer. As such, it is common to find multiple 'static final' keys denoting the same configuration properties which causes confusion.** Refactoring is a good practise. Often, change becomes essential. If, due to one of these reasons a property key has to be changed in the properties file, or if we have to find if a property is at all being used, trying to find which parts of the code use this property becomes a grep task. If the property keys are hierarchial, and the hierarchial keys are being assembled in code like:
```
Example:
String lookupKey = DATABASE_LOOKUP_PREFIX + "password";
```

It becomes nearly impossible to find all the usages of the property with sufficient confidence.

For these and more reasons, OpenConfig recommends using a strongly typed interface for Configuration.

# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages