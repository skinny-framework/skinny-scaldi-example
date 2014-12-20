import org.scalatest._

class ScalatraBootstrapSpec extends FunSpec with Matchers {

  describe("ScalatraBootstrap") {
    it("should be created") {
      val bootstrap = new ScalatraBootstrap
      bootstrap should not equal (null)
    }
  }

}

