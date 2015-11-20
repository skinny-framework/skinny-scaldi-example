package model
import awscala._, s3._

class AWSClientMockImpl extends AWSClient {
  override def s3Buckets: Seq[Bucket] = Seq(Bucket("foo"), Bucket("bar"))
}
