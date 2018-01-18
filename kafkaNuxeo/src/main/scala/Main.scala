object Main extends App {


  val moreKafkaConsumer = new MoreKafkaConsumer
  val records = moreKafkaConsumer.getNextRecords

  for (record <- records) {
    val document = KafkaRecordToMoreDocumentMapper.recordToMoreDocument(record)
    NuxeoGateway.createDocument(document)
  }
}
