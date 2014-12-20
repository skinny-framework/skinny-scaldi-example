package integrationtest

import org.scalatra.test.scalatest._
import skinny.test.SkinnyTestSupport
import _root_.controller.Controllers

class BucketsController_IntegrationTestSpec extends ScalatraFlatSpec with SkinnyTestSupport {
  Controllers.buckets.mount(servletContextHandler.getServletContext)

  it should "show top page" in {
    get("/buckets") {
      status should equal(200)
      body should equal("""[{"name":"foo","name":"foo"},{"name":"bar","name":"bar"}]""")
    }
  }

}
