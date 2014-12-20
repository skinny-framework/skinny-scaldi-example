package controller
import skinny._
import model._

class BucketsController extends ApiController {
  implicit val format = Format.JSON
  lazy val aws = inject[AWSClient]

  def index = renderWithFormat(aws.s3Buckets)

}
