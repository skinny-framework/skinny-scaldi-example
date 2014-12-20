package model
import org.scalatest._

class AWSClientImplSpec extends FunSpec with Matchers {

  describe("AWSClientImpl") {
    it("should be created") {
      val aws = new AWSClientImpl
      aws should not equal (null)
    }
  }

}

