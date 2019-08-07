OWNER
=====

OWNER, an API to ease Java property files usage. This is a fork: please consider 
a documentation to the [original project](https://github.com/lviggiano/owner/releases).


ABOUT THIS FORK
---------------

This is a fork from the original repository that was created because, when used 
in a natively compiled application, it won't work due to the usage of dynamic
proxies. Because of that, this version introduces the ability to provide the 
factory with an implementation, which itself can use some semi-magic methods
to collect the values respecting the conventions of the library as defined in 
the interface.

The implementation is found in the branch [1.0.10.METERIAN](https://github.com/MeterianHQ/owner/tree/1.0.10.METERIAN), it packages to version 1.0.10.METERIAN. To understand how this works please [see the tests](https://github.com/MeterianHQ/owner/tree/1.0.10.METERIAN/owner/src/test/java/org/aeonbits/owner/reconfigure)


INTRODUCTION
------------

The goal of OWNER API is to minimize the code required to handle
application configuration through Java properties files.

Full documentation available on [project website][website].

BASIC USAGE
-----------

The approach used by OWNER APIs, is to define a Java interface
associated to a properties file.

Suppose your properties file is defined
as `ServerConfig.properties`:

```properties
port=80
hostname=foobar.com
maxThreads=100
```

To access this property you need to define a convenient Java
interface in `ServerConfig.java`:

```java
public interface ServerConfig extends Config {
    int port();
    String hostname();
    int maxThreads();
}
```

We'll call this interface the *Properties Mapping Interface* or
just *Mapping Interface* since its goal is to map Properties into
a an easy to use piece of code.

Then, you can use it from inside your code:

```java
public class MyApp {
    public static void main(String[] args) {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        System.out.println("Server " + cfg.hostname() + ":" + cfg.port() +
                           " will run " + cfg.maxThreads());
    }
}
```

But this is just the tip of the iceberg.

Continue reading here: [Basic usage](http://owner.aeonbits.org/docs/usage/).

DOWNLOAD
--------

Public Releases can be downloaded from [GitHub Releases](https://github.com/lviggiano/owner/releases) page or
[Maven Central Repository](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.aeonbits.owner%22%20AND%20a%3A%22owner%22).


DOCUMENTATION
-------------

Make sure to have a look at the documentation on [project website][website]
to learn how flexible and powerful OWNER is, and why you may need it!

Chinese documentation is provided by [Yunfeng Cheng](https://github.com/cyfonly) via a GitHub independent project at
[this address][chinese-docs].

  [website]: http://owner.aeonbits.org
  [chinese-docs]: https://github.com/cyfonly/owner-doc


LICENSE
-------

OWNER is released under the BSD license.
See [LICENSE][] file included for the details.

  [LICENSE]: https://raw.github.com/lviggiano/owner/master/LICENSE
