package model
import org.scalatest._

class AWSClientImplSpec extends FunSpec with Matchers {

  describe("AWSClientImpl") {
    it("should be created") {
      System.setProperty("aws.accessKeyId", "aaa")
      System.setProperty("aws.secretKey", "bbb")
      val aws = new AWSClientImpl
      aws should not equal (null)
    }
  }

}
