package integrationtest

import skinny._, test._
import _root_.controller.Controllers

class RootController_IntegrationTestSpec extends SkinnyFlatSpec with SkinnyTestSupport {
  Controllers.root.mount(servletContextHandler.getServletContext)

  it should "show top page" in {
    get("/") {
      status should equal(200)
      body.contains("{&quot;name&quot;:&quot;foo&quot;,&quot;name&quot;:&quot;foo&quot;}") should equal(true)
    }
  }

}
