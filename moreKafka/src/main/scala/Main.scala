object Main extends App {

  val moreDocument = new MoreDocument("badeDoc2", "Moj Kafka Doc",
    "/default-domain/workspaces/More/kafkaDocuments/")

  val moreKafkaProducer = new MoreKafkaProducer
  moreKafkaProducer.sendCreateDocumentMessage(moreDocument)
}
