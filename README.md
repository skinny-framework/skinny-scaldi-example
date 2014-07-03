## Scaldi integration example

This is a sample application which shows how to integrate Scaldi with Skinny Framework.

Scaldi is a simple and elegant DI library in Scala.

http://scaldi.org/

See also the blog article:

http://blog.seratch.net/post/90627493988/scaldi-dependency-injection-in-skinny-apps

### How to run app

Before trying this example, set AWS credentials.

See also: https://github.com/seratch/AWScala

    export AWS_ACCESS_KEY_ID=xxx
    export AWS_SECRET_KEY=yyy

And then, run Skinny app.

    ./skinny run

Access http://localhost:8080/ from your browser.

### How to run tests

    ./skinny test

