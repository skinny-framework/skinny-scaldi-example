package controller

import skinny._
import model._

class RootController extends ApplicationController {

  def index = {
    set("buckets" -> inject[AWSClient].s3Buckets)
    render("/root/index")
  }

}

