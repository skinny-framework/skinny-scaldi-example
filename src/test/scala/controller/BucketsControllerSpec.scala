package controller

import org.scalatest._
import skinny.test.MockApiController

class BucketsControllerSpec extends FunSpec with Matchers {

  describe("BucketsController") {
    it("shows top page") {
      val controller = new BucketsController with MockApiController
      controller.index
      controller.contentType should equal("application/json; charset=utf-8")
    }
  }

}
