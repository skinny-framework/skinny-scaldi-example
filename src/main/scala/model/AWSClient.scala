package model
import awscala.s3._
trait AWSClient {
  def s3Buckets: Seq[Bucket]
}

