package controller

import skinny._
import skinny.filter._

/**
 * The base controller for API endpoints.
 */
trait ApiController extends SkinnyApiController
    with skinny.controller.feature.ScaldiFeature {

}

