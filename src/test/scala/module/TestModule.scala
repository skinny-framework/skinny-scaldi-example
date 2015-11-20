package module
import model._

// must be a class
class TestModule extends scaldi.Module {
  bind[AWSClient] to new AWSClientMockImpl
}
