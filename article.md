### Scaldi
<hr/>

Scaldi is an elegant DI library in Scala. 

http://scaldi.org/

>Scaldi provides simple and elegant way to do dependency injection in Scala. By using expressive power of the Scala language it defines intuitive and idiomatic DSL for binding and injecting dependencies.

Since Skinny Framework 1.1.5, you can use it seamlessly with Skinny apps. This article shows you a small but pragmatic example.

If you'd like to know about Scaldi integration in detail, visit the official docmentation:

http://skinny-framework.org/documentation/dependency-injection.html

### Don't use in Scala 2.10.x
<hr/>

This is an important notice. Due to [SI-6240](https://issues.scala-lang.org/browse/SI-6240), Scaldi doesn't work fine in Scala 2.10.x. Use Scala 2.11.

https://github.com/scaldi/scaldi/issues/16

### Getting started with Skinny
<hr/>

Let's get the latest skinny-blank-app-with-deps.zip from the official website.

http://skinny-framework.org/#try-it-right-now

    unzip skinny-blank-app-with-deps.zip
    cd skinny-blank-app
    ./skinny run

Access localhost:8080 from your browser. Now your first Skinny app works!

### Add skinny-scaldi library
<hr/>

Skinny's Scaldi integration adaptor is an optional library. Add the following to libraryDependencies. 

    "org.skinny-framework"    %% "skinny-scaldi"       % skinnyVersion,
    "com.github.seratch"      %% "awscala"             % "0.2.+",

The example in this article also uses AWScala which is a simple wrapper of AWS Java SDK.

https://github.com/seratch/AWScala

Before trying this example, set AWS credentials.

    export AWS_ACCESS_KEY_ID=xxx
    export AWS_SECRET_KEY=yyy

### For SkinnyEnv.Development
<hr/>

First, you need to do the following things.

 - Create a trait as an interface and the default implmentation class (or just a class is also fine)
 - Create a class which extends scaldi.Module trait
 - Add module definitions to application.conf
 - Use injected components in controllers

#### src/main/scala/model/AWSClient.scala

    package model
    import awscala.s3._
    trait AWSClient {
      def s3Buckets: Seq[Bucket]
    }

#### src/main/scala/model/AWSClientImpl.scala

    package model
    import awscala._, s3._
    class AWSClientImpl extends AWSClient {
      // https://github.com/seratch/AWScala
      implicit val s3 = S3()
      override def s3Buckets: Seq[awscala.s3.Bucket] = s3.buckets
    }

#### src/main/scala/module/AppModule.scala

    package module
    import model._
    // must be a class
    class AppModule extends scaldi.Module {
      bind[AWSClient] to new AWSClientImpl
    }

#### src/main/resources/application.conf

    development {
      scaldi {
        modules: ["module.AppModule"]
      }
    }

#### src/main/scala/controller/ApplicationController.scala

    package controller
    
    import skinny._
    import skinny.filter._
    import skinny.controller.feature.ScaldiFeature
    
    trait ApplicationController extends SkinnyController
      with ScaldiFeature // <- Added!
      with ErrorPageFilter {
    } 

#### src/main/scala/controller/RootController.scala

    package controller
    
    import skinny._
    import model._
    
    class RootController extends ApplicationController {
    
      def index = {
        set("buckets" -> inject[AWSClient].s3Buckets)
        render("/root/index")
      }
    }

#### src/main/webapp/WEB-INF/views/root/index.html.ssp

    <%@val buckets: Seq[awscala.s3.Bucket] = Nil %>
    <%import skinny.util.JSONStringOps._ %>
    <h3>Hello World!</h3>
    <hr/>
    <p>
    Your first Skinny app works!
    
    #for (bucket <- buckets)
      <p>${toJSONString(bucket)}</p>
    #end
    </p>

Your S3 buckets should be shown at localhost:8080.

### For SkinnyEnv.Test
<hr/>

To replace components when testing, you need to prepare as follows.

 - Add Scaldi module for testing
 - Add mock implementation and bind it to the Scaldi module
 - Add the module to application.conf in test env

#### src/test/scala/model/AWSClientMockImpl.scala

Be aware that this class is placed under “src/test/scala”.

    package model
    import awscala._, s3._
    class AWSClientMockImpl extends AWSClient {
      override def s3Buckets: Seq[Bucket] = Seq(Bucket("foo"), Bucket("bar"))
    }

#### src/test/scala/module/TestModule.scala

Be aware that this class is placed under “src/test/scala”.

    package module
    import model._
    // must be a class
    class TestModule extends scaldi.Module {
      bind[AWSClient] to new AWSClientMockImpl
    }

#### src/main/resources/application.conf

Add scaldi modulces to test env settings.

    development {
      scaldi {
        modules: ["module.AppModule"]
      }
    }
    test {
      scaldi {
        modules: ["module.TestModule"]
      }
    }

OK, let's run `./skinny test`. 

You don't need real AWS configuration and payment for each test execution :-)

### Conclusion
<hr/>

Scaldi is a very useful library. If you're familiar with dependency injection pattern, try it out with Skinny Framework.

The example in the article is here:

https://github.com/skinny-framework/skinny-scaldi-example

Enjoy!


