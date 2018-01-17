import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}

class MoreKafkaProducer {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  private val producer = new KafkaProducer[String, String](props)

  //TODO Add JSON/Avro serialization for MoreDocument
  //  props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
  //  props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer")
  //  props.put("schema.registry.url", "http://localhost:8081")
  //  private val producer = new KafkaProducer[String, MoreDocument](props)

  def sendCreateDocumentMessage(document: MoreDocument): RecordMetadata = {

    val documentRecord = new ProducerRecord[String, String](Config.documentCreationsTopic, document.title, document
      .toString)

    producer.send(documentRecord).get()
  }
}
