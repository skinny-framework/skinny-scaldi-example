package module
import model._
// must be a class
class AppModule extends scaldi.Module {
  bind[AWSClient] to new AWSClientImpl
}

