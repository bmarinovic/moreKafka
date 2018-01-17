import org.apache.kafka.clients.consumer.ConsumerRecord

object KafkaRecordToMoreDocumentMapper {

  //TODO Parse JSON instead of plain String in future
  def recordToMoreDocument(consumerRecord: ConsumerRecord[String, String]): MoreDocument = {

    val json = consumerRecord.value()
    println(s"json: $json")
    val props = json.split(';') map (s => s.split('=')(0) -> s.split('=')(1)) toMap

    println(props)
    new MoreDocument(props("name"), props("title"), props("path"))
  }
}
