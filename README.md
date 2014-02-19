A simple class for configuring a SOCKS proxy to use the Proximo add-on in Heroku.

# Usage
Add the Proximo add-on to your Heroku app.  Pull in this module and initialize it:

    import com.palominolabs.heroku.Proximo
    …
    Proximo.setup()

That's it.  All requests should now be proxied through Proximo.