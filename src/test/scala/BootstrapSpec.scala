import org.scalatest._

class BootstrapSpec extends FunSpec with Matchers {

  describe("Bootstrap") {
    it("should be created") {
      val bootstrap = new Bootstrap
      bootstrap should not equal (null)
    }
  }

}

