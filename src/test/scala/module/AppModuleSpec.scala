package module
import org.scalatest._

class AppModuleSpec extends FunSpec with Matchers {

  describe("AppModule") {
    it("should be created") {
      val module = new AppModule
      module should not equal (null)
    }
  }

}

