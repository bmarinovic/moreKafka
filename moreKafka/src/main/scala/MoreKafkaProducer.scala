import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}

class MoreKafkaProducer {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer")
  props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
  props.put("schema.registry.url", "http://localhost:8081")

  private val producer = new KafkaProducer[Long, MoreDocument](props)

  def sendCreateDocumentMessage(document: MoreDocument): RecordMetadata = {

    val documentRecord = new ProducerRecord[Long, MoreDocument](SharedConfig.documentCreationsTopic,
      document)

    producer.send(documentRecord).get()
  }
}
