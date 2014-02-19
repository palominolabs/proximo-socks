A simple class for configuring a SOCKS proxy to use the Proximo add-on in Heroku.

# Usage
## Add the [Proximo add-on](https://addons.heroku.com/proximo) to your Heroku app

    heroku addons:add proximo

## Add this module to your Java project

Maven

    <dependency>
        <groupId>com.palominolabs.heroku</groupId>
        <artifactId>proximo-socks</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

Gradle

    compile 'com.palominolabs.heroku:proximo-socks:1.0.0-SNAPSHOT'

## Use the module

    import com.palominolabs.heroku.Proximo
    …
    Proximo.setup()

That's it.  All requests should now be proxied through Proximo.