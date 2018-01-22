object Main extends App {

  val moreKafkaConsumer = new MoreKafkaConsumer
  val records = moreKafkaConsumer.getNextRecords

  for (record <- records) {
    NuxeoGateway.createDocument(record.value())
  }
}
