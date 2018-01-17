object Main extends App {

  val moreDocument = new MoreDocument("badeDoc", "Bade Kafka Doc",
    "/default-domain/workspaces/More/kafkaDocuments/")
  val moreKafkaProducer = new MoreKafkaProducer
  moreKafkaProducer.sendCreateDocumentMessage(moreDocument)

  // Apparently, this have to be here in order for Consumer to succeed in poll
  Thread.sleep(1000)

  val moreKafkaConsumer = new MoreKafkaConsumer
  val records = moreKafkaConsumer.getNextRecords

  for (record <- records) {
    val document = KafkaRecordToMoreDocumentMapper.recordToMoreDocument(record)
    NuxeoGateway.createDocument(document)
  }
}
