package model
import awscala._, s3._
class AWSClientImpl extends AWSClient {
  // https://github.com/seratch/AWScala
  implicit val s3 = S3()
  override def s3Buckets: Seq[awscala.s3.Bucket] = s3.buckets
}

