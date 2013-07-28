# voter

[![Build Status](https://travis-ci.org/pgr0ss/voter.png)](https://travis-ci.org/pgr0ss/voter)

A simple app which let's a group add and vote on topics.

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Running

Create and migrate the database:

    createdb voter
    lein migrate

To start a web server for the application, run:

    lein ring server

By default, the app requires Google login with a gmail.com email address. You can override the allowed email domains with an environment variable:

    ALLOWED_EMAIL_DOMAINS=foo.com,bar.com lein ring server

## License

Copyright © 2013 Paul Gross. Distributed under the Eclipse Public License, the same as Clojure.
